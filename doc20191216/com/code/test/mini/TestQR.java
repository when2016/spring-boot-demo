package com.code.test.mini;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class TestQR {

    public static void main(String[] args) throws Exception {

        //获取token
        String result1 = get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Constant.NATIVE_APP_ID + "&secret=" + Constant.NATIVE_APP_SECRET);
        String access_token = JSONObject.parseObject(result1).getString("access_token");
        //String access_token = "13_gvlqIPiY5ilaX_rQYIsU15_4RKYe6W0GtyOQGkRWhbV77kbh4f2-pQaTolHuaixl4rUaO2LUxo5Tbzq5XkA2ozkWfFCeFEDNyciOhu0pxDnK49L3CFj2roaCPQpLcc1ToE333fvxorg_LhuzNCAiAAADSQ";
       // access_token="13_fpX3rWJ6-2nWobobsgOwJmgZ8WRArjV7_0iYwqj8B23ibTQnQvMlJO-7GO09g-99fdDAso35Y7fnsk-s9wCjq81czOcCQ5gA9tfYgZcT30Fi3MwwZ-jqmUpUAaMCAPiAAAYWL";
        System.out.println(access_token);
        //if (StringUtils.iCheckTool.isString(access_token)) {
        System.out.println("token为");
        System.out.println(access_token);
        Map<String, Object> params = new HashMap<>();
        params.put("scene", "123456" + "_" + "789");
        params.put("page", "pages/home/modules/task_details/index");
        params.put("width", 430);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        String body = JSON.toJSONString(params);
        System.out.println("--------");
        System.out.println(body);
        System.out.println("--------");
        StringEntity entity;
        entity = new StringEntity(body);
        entity.setContentType("image/png");

        httpPost.setEntity(entity);
        HttpResponse response;

        response = httpClient.execute(httpPost);
        InputStream inputStream = response.getEntity().getContent();

        File targetFile = new File("D:\\upload");
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream("D:\\upload\\" + System.currentTimeMillis() + ".png");

        byte[] buffer = new byte[8192];
        int bytesRead = 0;
        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        out.flush();
        out.close();
//        } else {
//            System.out.println("获取access_token错误");
//        }
    }

    /**
     * 发送get请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(url, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }


}
