package com.longmaosoft.server.task.modules.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.longmaosoft.data.dao.totoro.agent.TotoroAgentComponentDao;
import com.longmaosoft.data.dao.totoro.agent.TotoroAgentTaskDao;
import com.longmaosoft.lib.utils.StringUtils;
import com.longmaosoft.lib.utils.entity.SimpleMap;
import com.longmaosoft.server.task.modules.service.TaskAudioProcessService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.sun.jmx.remote.internal.ArrayQueue;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author Feng Congyue
 * @date 2018年01月02日 上午11:33
 */

@Service
public class TaskAudioProcessServiceImpl implements TaskAudioProcessService{

    private static final Logger logger      = LoggerFactory.getLogger(TaskAudioProcessServiceImpl.class);

    private static final String basePath    = "/home/work/feng/audio/";
    private static final String processPath = basePath + "process/";
    private static final String shellPath   = basePath + "shell/silk-v3-decoder/converter.sh";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    TotoroAgentComponentDao totoroAgentComponentDao;

    @Autowired
    TotoroAgentTaskDao totoroAgentTaskDao;

    @Override
    // 数据库时间倒序找到需要处理的任务凭证，并标记为 process_status=1
    public String markToProcessTaskCert() throws Exception {
        try {
            Integer count = 0;
            // 所有音频 component
            Set<Long> componentIds = totoroAgentComponentDao.queryAudioComponentIds();

            // 数据库 id 倒叙查询， device in(3,4) and process_status is null
            List<SimpleMap> certList = totoroAgentTaskDao.queryRecentMiniAppCert();

            // 检查对应的凭证
            SimpleMap param = new SimpleMap();
            for (SimpleMap cert : certList) {
                param.clear();
                List<JSONObject> list = JSONObject.parseObject(cert.getString("data"), List.class);
                param.put("id", cert.getLong("id"));

                Integer type = 12;   // 12：非音频 ， 11：app 提交  1：小程序音频待处理(两种格式silk,aac)
                for (JSONObject item : list) {
                    if (componentIds.contains(item.getLong("id"))) {
                        // 检查音频是否小程序提交
                        if(item.getString("value").endsWith(".mp3")) {
                            type = 11;
                        }else if (item.getString("value").endsWith(".silk") || item.getString("value").endsWith(".aac")) {
                            type = 1;
                        } else {
                            type = 11;
                        }
                        break;
                    }
                }
                param.put("process_status", type);
                if (totoroAgentTaskDao.updateProcessStatusById(param) > 0) {
                    count++;
                }
            }
            return "标记数量：" + count + "/" + certList.size();
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @Override
    public String processTaskCert() throws Exception {
        Long    lastId      = Long.MAX_VALUE;
        Long    nowTime     = System.currentTimeMillis();
        Integer count       = 0;
        String  key         = "miniApp:data:process:audio:download:lastId";
        String  lastIdStr   = redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(lastIdStr)){
            lastId = Long.parseLong(lastIdStr);
        }
        SimpleMap param     = new SimpleMap();
        // 下载转码
        try{
            // 所有音频 component
            Set<Long> componentIds = totoroAgentComponentDao.queryAudioComponentIds();
            // 可处理的 taskCert
            List<SimpleMap> certList = totoroAgentTaskDao.queryToProcessCerts(lastId);
            if(certList.size()==0){
                redisTemplate.expire(key, -1L, TimeUnit.MILLISECONDS);
                return "暂无可处理音频";
            }

            // 缓存 lastKey
            redisTemplate.opsForValue().set(key, certList.get(certList.size()-1).getLong("id").toString());
            // 处理
            for (SimpleMap cert : certList) {

                // 若已存在，则进行删除 【服务重启中断造成的无效数据】
                String  cPath         = processPath+cert.getLong("id");
                File    cPathDir      = new File(cPath);
                if(cPathDir.exists()){
                    FileUtil.deleteContents(cPathDir);
                    cPathDir.delete();
                }
                cPathDir.mkdir();
                List<JSONObject> list = JSONObject.parseObject(cert.getString("data"), List.class);
                Integer process_status= 2;

                for (JSONObject item : list) {
                    if (componentIds.contains(item.getLong("id"))) {
                        // 检查音频是否小程序提交
                        String url      = item.getString("value");
                        String filePath = cPath + "/" + url.replaceAll("^.+/([^/]+)$","$1");

                        // 从 七牛 下载文件 重试 3次
                        for(int i=0; i<3; i++){
                            try{
                                if(downloadFile(filePath, url)){
                                    break;
                                }
                            }catch(Exception e){}
                        }

                        if(!new File(filePath).exists()){
                            logger.error(cert.getLong("id") + " : " + url + " : 下载失败！");
                            process_status = 10;
                            break;
                        }

                        // 调用 转码脚本转码 重试 3次
//                        StringBuffer processLog = new StringBuffer();
//                        Process process = null;
//                        for(int i=0; i<10; i++){
//                            try{
//                                process = Runtime.getRuntime().exec(String.format("sh %s %s wav", shellPath, filePath));
//                                BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));
//                                String line=null;
//                                while((line=br.readLine())!=null){
//                                    processLog.append(br.readLine());
//                                }
//                                break;
//                            }catch(Exception e){}
//                        }

                        try {
                            String command = String.format("sh %s %s wav", shellPath, filePath);
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
                            CommandLine commandline = CommandLine.parse(command);
                            DefaultExecutor exec = new DefaultExecutor();
                            exec.setExitValues(null);

                            ExecuteWatchdog watchdog = new ExecuteWatchdog(10*60*1000);
                            exec.setWatchdog(watchdog);
                            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
                            exec.setStreamHandler(streamHandler);
                            exec.execute(commandline);

                            Thread.sleep(100L);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String proFilePath=filePath;
                        if(filePath.endsWith(".silk")) {
                            proFilePath=filePath.replace(".silk",".wav");
                        }else if(filePath.endsWith(".aac")) {
                            proFilePath=filePath.replace(".aac",".wav");
                        }

                        if(!new File(proFilePath).exists()){
                            logger.error(cert.getLong("id") + " : " + url + "处理失败！");
                            process_status = 10;
                            break;
                        }
                    }
                }

                if(process_status.equals(2)){
                    count++;
                }

                // 修改数据库此凭证状态
                param.put("id", cert.getLong("id"));
                param.put("process_status", process_status);
                totoroAgentTaskDao.updateProcessStatusById(param);
            }

        }catch(Exception e){
            logger.error("处理文件失败：" , e);
            return "处理失败！";
        }
        return "成功处理凭证数量 : " + count;
    }

    @Override
    public String uploadProcessedFiles() throws Exception {
        final int FILE_QUEUE_SIZE = 10;
        final int PROCESS_THREADS = 30;// 线程个数

        String [] dirs = new File(processPath).list();
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(dirs.length);
        queue.addAll(Arrays.asList(dirs));

        for (int i = 0; i < PROCESS_THREADS; i++) {
            new Thread(new UploadThread(
                    processPath,
                    queue,
                    redisTemplate,
                    totoroAgentTaskDao
            )).start();
        }
        // 上传处理后的文件
//        String  key     = "miniApp:data:process:audio:upload:";
//        Integer cnt     = 0;
//        Integer fCnt    = 0;
//        Pattern wavPattern = Pattern.compile("wav$");
//        SimpleMap param = new SimpleMap();
//
//        // 监控 process 下的文件夹
//        String[] dirs = new File(processPath).list();
//
//        for(String dir : dirs){
//            if(dir.matches("^\\d+$")){
//                if(StringUtils.isEmpty(redisTemplate.opsForValue().get(key + dir))){
//                    redisTemplate.opsForValue().set(key + dir, "ing");
//                    // 上传操作 重试 3次
//                    if(!new File(processPath + dir).exists()){ // 对于已删除的情况
//                        redisTemplate.expire(key + dir, -1, TimeUnit.SECONDS);
//                        continue;
//                    }
//                    // 校验凭证完整性（状态为2），不完整的凭证不进行操作
//                    SimpleMap cert = totoroAgentTaskDao.getTaskCertById(Long.parseLong(dir));
//                    if(!cert.getInteger("process_status").equals(2)){
//                        redisTemplate.expire(key + dir, -1, TimeUnit.SECONDS);
//                        continue;
//                    }
//
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                        }
////                    }).start();
//
//
//                    logger.info("03--processPath-dir="+processPath+dir);
//                    String[] files = new File(processPath + dir).list();
//                    Integer failCnt = 0;
//                    for(String file : files){
//                        logger.info("03--file="+file);
//                        if(file.matches("^.+\\.wav$")){
//                            Boolean uploaded = false;
//                            for(int i=0; i<3; i++){
//                                try{
//                                    if(uploadFile(processPath + dir + "/" + file, dir + "/" + file)){
//                                        uploaded = true;
//                                    }
//                                }catch(Exception e){
//                                    logger.warn("上传失败：", e);
//                                }
//                            }
//                            if(!uploaded){
//                                failCnt++;
//                            }
//                        }
//                    }
//
//                    // 修改凭证状态
//                    param.put("id", Long.parseLong(dir));
//                    if(failCnt>0){
//                        param.put("process_status", 13);
//                        fCnt++;
//                    }else{
//                        cnt++;
//                        param.put("process_status", 3);
//                    }
//                    totoroAgentTaskDao.updateProcessStatusById(param);
//
//                    // 删除文件夹 & 删除 redis-key
//                    File file = new File(processPath + dir);
//                    FileUtil.deleteContents(file);
//                    file.delete();
//                    redisTemplate.expire(key + dir, -1, TimeUnit.SECONDS);
//                    break;
//                }
//            }
//        }

        return String.format("成功：%s, 失败：%s", "1", "1");
    }

    @Override
    public String modifyTaskCertDataContent() throws Exception {
        //TODO 修正任务凭证数据为修改后的音频链接
        List<SimpleMap> certList = totoroAgentTaskDao.queryProcessSuccessedCert();
        SimpleMap param = new SimpleMap();
        Integer     count   = 0, failCount  = 0;

        for(SimpleMap cert : certList){
            String data = cert.getString("data");
            Long   id   = cert.getLong("id");
            if(data != null){
                List<Map> list = JSONObject.parseArray(data, Map.class);
                for(Map item : list){
                    Object valueObj = item.get("value");
                    if(valueObj == null){
                        continue;
                    }
                    String value = valueObj.toString();
                    if(value.endsWith(".silk")){
                        item.put("value", value.replaceAll("^(http://[^/]+/).+/([^/]+).silk$", "$1" + id + "/$2.wav"));
                    }else if(value.endsWith(".aac")) {
                        item.put("value", value.replaceAll("^(http://[^/]+/).+/([^/]+).aac$", "$1" + id + "/$2.wav"));
                    }
                }
                param.put("id", id);
                param.put("data", JSONObject.toJSON(list).toString());
                if(totoroAgentTaskDao.updateDataAndProcessStatusById(param) > 0){
                    count ++;
                }else{
                    failCount ++;
                }
            }
        }
        return String.format("成功：%s, 失败：%s", count, failCount);
    }

    private boolean downloadFile(String filePath, String url) {


        File f = new File(filePath);
        if(f.exists()) {
            return true;
        }
        ///////////////////////////////////////////////////////////////////////
        ///// 下载文件
        ///////////////////////////////////////////////////////////////////////

        URL request = null;
        try {
            request = new URL(url);
        } catch (MalformedURLException e) {
            logger.warn(String.format("打开文件地址失败: %s", url));
            return false;
        }
        HttpURLConnection connection = null;
        //重试
        for(int i = 0; i < 10; i++) {
            try {
                connection = (HttpURLConnection) request.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(10000);
                if (connection.getResponseCode() == 200) {
                    break;
                }
                Thread.sleep(500);
            }catch (SocketTimeoutException e) {
                break;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(connection == null) {
            logger.warn(String.format("下载文件失败: %s", url));
            return false;
        }

        int size = 0;
        InputStream inputStream = null;
        try{
            inputStream = connection.getInputStream();
            size = connection.getContentLength();
        } catch (Exception e) {
            logger.warn(String.format("获取文件流失败: %s", url));
            return false;
        }

        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            logger.warn(String.format("创建文件失败: %s", filePath));
            return false;
        }

        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(f));
        } catch (FileNotFoundException e) {
            logger.warn(String.format("打开输出文件流失败: %s", filePath));
        }

        byte[] cache = new byte[1024];
        int num = 0, total = 0;
        try {
            while((num = inputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, num);
                total += num;
            }
            outputStream.flush();
        } catch (Exception e) {
            try {
                outputStream.close();
            } catch (IOException e1) {}
            f.delete();
            return false;
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {}

            connection.disconnect();
        }

        if(total != size) {
            logger.warn(String.format("09-文件损坏: %s", filePath));
            return false;
        }

        return true;
    }

    private boolean uploadFile(String localFilePath, String key){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "";
        String secretKey = "";
        String bucket = "https-download";

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            try {
                if(ex.response.error.equals("file exists")){
                    logger.error("文件已存在！");
                    return true;
                }
            }catch(Exception e){}
            logger.error("上传失败！{}", localFilePath, ex);
            return false;
        }
        return true;
    }

}


class UploadThread implements Runnable {

    private static final Logger logger      = LoggerFactory.getLogger(UploadThread.class);
    // 上传处理后的文件
    String  key     = "miniApp:data:process:audio:upload:";
    Integer cnt     = 0;
    Integer fCnt    = 0;
    Pattern wavPattern = Pattern.compile("wav$");
    SimpleMap param = new SimpleMap();

    private String processPath;
    private BlockingQueue<String> queue;
    private RedisTemplate<String, String> redisTemplate;
    private TotoroAgentTaskDao totoroAgentTaskDao;

    public UploadThread(
            String processPath,
            BlockingQueue<String> queue,
            RedisTemplate<String, String> redisTemplate,
            TotoroAgentTaskDao totoroAgentTaskDao
    ) {
        this.processPath = processPath;
        this.queue = queue;
        this.redisTemplate = redisTemplate;
        this.totoroAgentTaskDao = totoroAgentTaskDao;
    }

    @Override
    public void run() {
        // 监控 process 下的文件夹
        try {
            boolean done = false;
            while (!done) {
                //取出队首元素，如果队列为空，则阻塞
                String dir = queue.take();
                if(dir.matches("^\\d+$")){
                    if(StringUtils.isEmpty(redisTemplate.opsForValue().get(key + dir))){
                        redisTemplate.opsForValue().set(key + dir, "ing");
                        // 上传操作 重试 3次
                        if(!new File(processPath + dir).exists()){ // 对于已删除的情况
                            redisTemplate.expire(key + dir, -1, TimeUnit.SECONDS);
                            done = false;
                            continue;
                        }
                        // 校验凭证完整性（状态为2），不完整的凭证不进行操作
                        SimpleMap cert = totoroAgentTaskDao.getTaskCertById(Long.parseLong(dir));
                        if(!cert.getInteger("process_status").equals(2)){
                            redisTemplate.expire(key + dir, -1, TimeUnit.SECONDS);
                            done = false;
                            continue;
                        }

                        String[] files = new File(processPath + dir).list();
                        Integer failCnt = 0;
                        for(String file : files){
                            if(file.matches("^.+\\.wav$")){
                                Boolean uploaded = false;
                                for(int i=0; i<3; i++){
                                    try{
                                        if(uploadFile(processPath + dir + "/" + file, dir + "/" + file)){
                                            uploaded = true;
                                        }
                                    }catch(Exception e){
                                        logger.warn("上传失败：", e);
                                    }
                                }
                                if(!uploaded){
                                    failCnt++;
                                }
                            }
                        }

                        // 修改凭证状态
                        param.put("id", Long.parseLong(dir));
                        if(failCnt>0){
                            param.put("process_status", 13);
                            fCnt++;
                        }else{
                            cnt++;
                            param.put("process_status", 3);
                        }
                        totoroAgentTaskDao.updateProcessStatusById(param);

                        // 删除文件夹 & 删除 redis-key
                        File file = new File(processPath + dir);
                        FileUtil.deleteContents(file);
                        file.delete();
                        redisTemplate.expire(key + dir, -1, TimeUnit.SECONDS);

                        //done=true;
                        //break;
                    }
                }
            }
        } catch (InterruptedException e) {
        }

//        for(String dir : dirs){
//            if(dir.matches("^\\d+$")){
//                if(StringUtils.isEmpty(redisTemplate.opsForValue().get(key + dir))){
//                    redisTemplate.opsForValue().set(key + dir, "ing");
//                    // 上传操作 重试 3次
//                    if(!new File(processPath + dir).exists()){ // 对于已删除的情况
//                        redisTemplate.expire(key + dir, -1, TimeUnit.SECONDS);
//                        continue;
//                    }
//                    // 校验凭证完整性（状态为2），不完整的凭证不进行操作
//                    SimpleMap cert = totoroAgentTaskDao.getTaskCertById(Long.parseLong(dir));
//                    if(!cert.getInteger("process_status").equals(2)){
//                        redisTemplate.expire(key + dir, -1, TimeUnit.SECONDS);
//                        continue;
//                    }
//
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                        }
////                    }).start();
//
//
//                    String[] files = new File(processPath + dir).list();
//                    Integer failCnt = 0;
//                    for(String file : files){
//                        if(file.matches("^.+\\.wav$")){
//                            Boolean uploaded = false;
//                            for(int i=0; i<3; i++){
//                                try{
//                                    if(uploadFile(processPath + dir + "/" + file, dir + "/" + file)){
//                                        uploaded = true;
//                                    }
//                                }catch(Exception e){
//                                    e.printStackTrace();
//                                }
//                            }
//                            if(!uploaded){
//                                failCnt++;
//                            }
//                        }
//                    }
//
//                    // 修改凭证状态
//                    param.put("id", Long.parseLong(dir));
//                    if(failCnt>0){
//                        param.put("process_status", 13);
//                        fCnt++;
//                    }else{
//                        cnt++;
//                        param.put("process_status", 3);
//                    }
//                    totoroAgentTaskDao.updateProcessStatusById(param);
//
//                    // 删除文件夹 & 删除 redis-key
//                    File file = new File(processPath + dir);
//                    FileUtil.deleteContents(file);
//                    file.delete();
//                    redisTemplate.expire(key + dir, -1, TimeUnit.SECONDS);
//                    break;
//                }
//            }
//        }
    }

    private boolean uploadFile(String localFilePath, String key){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "";
        String secretKey = "";
        String bucket = "https-download";

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            try {
                if(ex.response.error.equals("file exists")){
                    logger.error("文件已存在！");
                    return true;
                }
            }catch(Exception e){}
            logger.error("上传失败！{}", localFilePath, ex);
            return false;
        }
        return true;
    }
}