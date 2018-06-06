package com.wanghongen.demo;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wang on 2018/6/3
 */

@RestController
public class OssUploaderController {

  @RequestMapping(value = "/getPolicy", method = {RequestMethod.GET, RequestMethod.POST})
  public void getPostObjectPolicy(HttpServletRequest request, HttpServletResponse response) {
    String endpoint = "oss-cn-beijing.aliyuncs.com";
    String accessId = "LTAIc1R15LLE7COe";
    String accessKey = "zZa98mFIiyzDJY6kcE6Yrl6lpAUMmC";
    String bucket = "wanghongen";
    String dir = "images/";
    String host = "http://" + bucket + "." + endpoint;
    OSSClient client = new OSSClient(endpoint, accessId, accessKey);
    try {
      long expireTime = 30;
      long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
      Date expiration = new Date(expireEndTime);
      PolicyConditions policyConds = new PolicyConditions();
      policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
      policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

      String postPolicy = client.generatePostPolicy(expiration, policyConds);
      byte[] binaryData = postPolicy.getBytes("utf-8");
      String encodedPolicy = BinaryUtil.toBase64String(binaryData);
      String postSignature = client.calculatePostSignature(postPolicy);

      Map<String, String> respMap = new LinkedHashMap<String, String>();
      respMap.put("accessid", accessId);
      respMap.put("policy", encodedPolicy);
      respMap.put("signature", postSignature);
      //respMap.put("expire", formatISO8601Date(expiration));
      respMap.put("dir", dir);
      respMap.put("host", host);
      respMap.put("expire", String.valueOf(expireEndTime / 1000));
      JSONObject ja1 = JSONObject.fromObject(respMap);
      System.out.println(ja1.toString());
      response.setHeader("Access-Control-Allow-Origin", "*");
      response.setHeader("Access-Control-Allow-Methods", "GET, POST");
      response(request, response, ja1.toString());

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void response(HttpServletRequest request, HttpServletResponse response, String results)
      throws IOException {

    System.out.println("results==" + results);
    String callbackFunName = request.getParameter("callback");
    System.out.println("callbackFunName==" + callbackFunName);
    //callbackFunName="http://wang.tunnel.shengnian.org/hello";

    if (callbackFunName == null || callbackFunName.equalsIgnoreCase("")) {
      response.getWriter().println(results);
    } else {
      response.getWriter().println(callbackFunName + "( " + results + " )");
    }
    response.setStatus(HttpServletResponse.SC_OK);
    response.flushBuffer();
  }

}
