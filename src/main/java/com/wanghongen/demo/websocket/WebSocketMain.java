package com.wanghongen.demo.websocket;

import java.net.URISyntaxException;

/**
 * https://blog.csdn.net/yaomingyang/article/details/79015674
 *
 * Created by wang on 2018/6/25
 */
public class WebSocketMain {

  public static void main(String[] args) {
    try {
      WebClientEnum.CLIENT
          .initClient(new MsgWebSocketClient("wss://xiaochanpin.heixiongboji.com:9801"));
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
