package com.wanghongen.demo.policy;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.wanghongen.demo.util.Constant;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

/**
 * 生成post回调策略
 *
 * Created by liumian on 2016/9/27.
 */
public class PostObjectPolicy {

    private static Logger logger = Logger.getLogger(PostObjectPolicy.class);

    /**
     * 将生成的object直接返回给前端
     *
     * @return
     */
    public static JSONObject createPolicy(String dir) {
        OSSClient client = new OSSClient(Constant.ENDPOINT, Constant.ACCESS_ID, Constant.ACCESS_KEY);
        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        String encodedPolicy;
        String postSignature;

        try {

            byte[] binaryData = postPolicy.getBytes("utf-8");
            encodedPolicy = BinaryUtil.toBase64String(binaryData);
            postSignature = client.calculatePostSignature(postPolicy);

        } catch (Exception e) {
            logger.error(e);
            return null;
        }
        Map<String, Object> respMap = new LinkedHashMap<String, Object>();
        respMap.put("accessid", Constant.ACCESS_ID);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", Constant.HOST);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));

        JSONObject callback = new JSONObject();
        callback.put("callbackUrl","http://wang.tunnel.shengnian.org/callback");
        callback.put("callbackBody","{\"mobile\":13811244507,\"purpose\":100");

        BASE64Encoder encoder = new BASE64Encoder();
        respMap.put("callback", encoder.encode(callback.toString().getBytes()));

        //return new JSONObject(respMap);
        return JSONObject.fromObject(respMap);
    }

}
