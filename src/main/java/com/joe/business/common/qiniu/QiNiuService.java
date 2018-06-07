package com.joe.business.common.qiniu;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.UrlSafeBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 七牛图片验证
 * create by Joe on 2018-05-27 17:54
 **/
@Service
public class QiNiuService {

    @Autowired
    private QiNiuConfig qiNiuConfig;

    /**
     * 获取上传文件的Token
     *
     * @return
     */
    public String getUploadToken() {

        String token = "";
        try {
            JSONObject json = new JSONObject();

            long deadline = System.currentTimeMillis() / 1000 + qiNiuConfig.getEffective() * 60 * 60;
            json.put("deadline", deadline);
            json.put("scope", qiNiuConfig.getBucketName());

            String _encodedPutPolicy = UrlSafeBase64.encodeToString(json.toString().getBytes());
            byte[] _sign = HmacSHA1Encrypt(_encodedPutPolicy, qiNiuConfig.getSecretKey());
            String _encodedSign = UrlSafeBase64.encodeToString(_sign);
            token = qiNiuConfig.getAccessKey() + ':' + _encodedSign + ':' + _encodedPutPolicy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    private byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
            throws Exception {

        byte[] data = encryptKey.getBytes(qiNiuConfig.getEncoding());
        SecretKey secretKey = new SecretKeySpec(data, qiNiuConfig.getMacName());
        Mac mac = Mac.getInstance(qiNiuConfig.getMacName());
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(qiNiuConfig.getEncoding());

        return mac.doFinal(text);
    }
}

