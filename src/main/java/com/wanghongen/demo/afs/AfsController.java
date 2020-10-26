package com.wanghongen.demo.afs;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.wanghongen.demo.util.CusAccessObjectUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("afs")
public class AfsController {

    @Value("${aliyun.huakuai.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.huakuai.accessKeyId}")
    private String accessKeySecret;



    @PostMapping(value = "test")
    public String test(HttpServletRequest request, @RequestBody AfsAuth afsAuth) throws ClientException {

        //验签结果
        String checkSign = "验签失败";


        System.out.println("前端参数================");
        String sessionId = afsAuth.getSessionId();
        String sign = afsAuth.getSign();
        String token = afsAuth.getToken();
        String scene = afsAuth.getScene();

        System.out.println("sessionId==" + sessionId);
        System.out.println("sign==" + sign);
        System.out.println("token==" + token);
        System.out.println("scene==" + scene);

        System.out.println("后端参数================");
        String ip = CusAccessObjectUtil.getIpAddress(request);
        System.out.println("accessKeySecret==" + accessKeySecret);
        System.out.println("accessKeyId==" + accessKeyId);
        System.out.println("ip==" + ip);


        //YOUR ACCESS_KEY、YOUR ACCESS_SECRET请替换成您的阿里云accesskey id和secret
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "afs", "afs.aliyuncs.com");

        AuthenticateSigRequest authenticateSigRequest = new AuthenticateSigRequest();
        authenticateSigRequest.setSessionId(sessionId);// 必填参数，从前端获取，不可更改，android和ios只传这个参数即可
        authenticateSigRequest.setSig(sign);// 必填参数，从前端获取，不可更改
        authenticateSigRequest.setToken(token);// 必填参数，从前端获取，不可更改
        authenticateSigRequest.setScene(scene);// 必填参数，从前端获取，不可更改

        authenticateSigRequest.setAppKey("FFFF0N00000000009878");// 必填参数，后端填写
        authenticateSigRequest.setRemoteIp(ip);// 必填参数，后端填写

        try {
            AuthenticateSigResponse response = client.getAcsResponse(authenticateSigRequest);
            if (response.getCode() == 100) {
                System.out.println("验签通过");
            } else {
                System.out.println("验签失败");
            }
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkSign;
    }

}





