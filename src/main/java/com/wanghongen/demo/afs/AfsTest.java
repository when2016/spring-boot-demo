package com.wanghongen.demo.afs;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AfsTest {
    public static void main(String[] args) throws Exception {
        test();
    }


    public static void test() throws Exception {

        String regionid = "cn-hangzhou";
        String accessKeyId = "*** Provide your AccessKeyId ***";
        String accessKeySecret = "*** Provide your AccessKeySecret ***";
        // Create a new IClientProfile instance
        IClientProfile profile = DefaultProfile.getProfile(regionid, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "afs", "afs.aliyuncs.com");

        AuthenticateSigRequest request = new AuthenticateSigRequest();
        request.setSessionId("xxx");// 会话ID。必填参数，从前端获取，不可更改。
        request.setSig("xxx");// 签名串。必填参数，从前端获取，不可更改。
        request.setToken("xxx");// 请求唯一标识。必填参数，从前端获取，不可更改。
        request.setScene("xxx");// 场景标识。必填参数，从前端获取，不可更改。
        request.setAppKey("xxx");// 应用类型标识。必填参数，后端填写。
        request.setRemoteIp("xxx");// 客户端IP。必填参数，后端填写。

        //response的code枚举：100验签通过，900验签失败
        AuthenticateSigResponse response = client.getAcsResponse(request);

        if (response.getCode() == 100) {
            System.out.println("验签通过");
        } else {
            System.out.println("验签失败");
            // TODO
        }

    }
}
