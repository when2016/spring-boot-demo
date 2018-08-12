package com.wanghongen.demo.controller;

import com.wanghongen.demo.policy.PostObjectPolicy;
import com.wanghongen.demo.util.CallbackUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 下发oss回调策略； 响应oss回调。
 *
 * Created by liumian on 2016/9/27.
 */
@RestController
public class OssController {

  /**
   * 提供给oss服务器回调的地址
   */
  @RequestMapping(value = "oss/callback", method = {RequestMethod.POST, RequestMethod.GET})
  public String callback(@RequestBody String ossCallbackBody,
      @RequestHeader("Authorization") String authorization,
      @RequestParam("callback") String callbackMethodName,
      @RequestHeader("x-oss-pub-key-url") String publicKeyUrlBase64,
      HttpServletRequest request,
      HttpServletResponse response) {

    System.out.println("oss/callback------------------");
    System.out.println(ossCallbackBody);
    System.out.println(authorization);
    System.out.println(callbackMethodName);
    System.out.println(publicKeyUrlBase64);
    System.out.println(request.getQueryString());
    System.out.println(request.getRequestURI());


    boolean isOssCallback = CallbackUtil.verifyOSSCallbackRequest(authorization
        , publicKeyUrlBase64
        , ossCallbackBody
        , request.getQueryString()
        , request.getRequestURI());

    if (isOssCallback) {
      response.setStatus(HttpServletResponse.SC_OK);
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("success", true);
      return jsonObject.toString();
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("success", false);
      return jsonObject.toString();
    }
  }

  @RequestMapping(value = "oss/policy")
  public String createPolicy() {

    String jsonStr = PostObjectPolicy.createPolicy("images/").toString();
    System.out.println(jsonStr);
    return jsonStr;

  }

}
