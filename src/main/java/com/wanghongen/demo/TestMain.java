package com.wanghongen.demo;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public class TestMain {


    public static void main(String[] args) {
        System.out.println("AAAAAAAAAAAAAAAAAAAA");

        String url = "http://wwww.baidu.com";
        url = "http://localhost:8040/jobtbao-oauth-srv/v1/oauth/api/gt/register";
        while (true) {
            getRequest(url);
        }

    }

    public static void getRequest(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        new Thread(() -> {
            try {
                Response response = call.execute();
                log.info("run: " + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
