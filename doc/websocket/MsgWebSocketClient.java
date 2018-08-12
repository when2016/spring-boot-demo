package com.wanghongen.demo.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * Created by wang on 2018/6/25
 */
public class MsgWebSocketClient extends WebSocketClient {

  public MsgWebSocketClient(String url) throws URISyntaxException {
    super(new URI(url));
    // TODO Auto-generated constructor stub
  }

  @Override
  public void onOpen(ServerHandshake shake) {
    // TODO Auto-generated method stub
    System.out.println("握手...");
    for (Iterator<String> it = shake.iterateHttpFields(); it.hasNext(); ) {
      String key = it.next();
      System.out.println(key + ":" + shake.getFieldValue(key));
    }
  }

  @Override
  public void onMessage(String paramString) {
    System.out.println("接收到消息：" + paramString);
  }

  @Override
  public void onClose(int i, String s, boolean b) {
    System.out.println("关闭...");
  }

  @Override
  public void onError(Exception e) {
    System.out.println("异常" + e);
  }
}
