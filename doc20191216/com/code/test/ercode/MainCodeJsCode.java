package com.code.test.ercode;

import com.code.test.mini.Constant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;



//https://blog.csdn.net/qq_33157666/article/details/80158519
public class MainCodeJsCode {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenUtils.class);

    public static void main(String[] args) {
        try {
            //1、获取AccessToken jsapi_ticket
            String accessToken = "";
            String jsapiTicket = "";
            Map<String, String> tokenMap = AccessTokenUtils.getAccessToken();////AccessTokenUtils见第二步
            if (tokenMap != null) {
                accessToken = tokenMap.get("access_token");
                jsapiTicket = tokenMap.get("jsapi_ticket");
            }
            //3、时间戳和随机字符串  
            String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串  
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳  
            // 4、获取url
//            String url = R.getRequest().getScheme() + "://" + R.getRequest().getServerName() + R.getRequest().getRequestURI();
//            if (StringUtils.isNoneBlank(R.getRequest().getQueryString())) {
//                url = R.getRequest().getScheme() + "://" + R.getRequest().getServerName() + R.getRequest().getRequestURI() + "?" + R.getRequest().getQueryString();
//            }
            //5、将参数排序并拼接字符串  
            String str = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + noncestr + "&timestamp=" + timestamp;
            logger.info("参数拼接的字符串：" + str);
            //6、将字符串进行sha1加密  
            String signature = WeiXinUtils.SHA1(str);
            logger.info("签名：" + signature);
            Map<String, Object> data = new HashMap<>();
            data.put("timestamp", timestamp);
            data.put("nonceStr", noncestr);
            data.put("signature", signature);
            data.put("accessToken", accessToken);
            data.put("appId", Constant.APP_ID);
            System.out.println(data);
            //return ApiUtils.getMap(ApiUtils.STATUS_OK, "获取成功", data, null);
        } catch (Exception e) {
            logger.error("获取微信信息的错误", e);
            //return ApiUtils.getMap(ApiUtils.STATUS_SERVER_ERROR, "服务器发生错误", null, null);
        }

    }
}
