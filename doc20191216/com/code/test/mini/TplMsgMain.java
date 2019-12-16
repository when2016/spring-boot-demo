package com.code.test.mini;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class TplMsgMain {
    public static void main(String[] args) {
        String result = get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Constant.APP_ID + "&secret=" + Constant.APP_SECRET);
        System.out.println(result);
        String access_token = JSONObject.parseObject(result).getString("access_token");
        System.out.println(access_token);
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
