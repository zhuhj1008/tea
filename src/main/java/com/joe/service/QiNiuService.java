package com.joe.service;

import com.alibaba.fastjson.JSONObject;
import com.joe.config.QiNiuConfig;
import com.qiniu.util.UrlSafeBase64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 七牛图片验证
 * create by Joe on 2018-05-27 17:54
 **/
@Slf4j
@Service
public class QiNiuService {

    @Autowired
    private QiNiuConfig qiNiuConfig;

    /**
     * 获取上传文件的Token
     */
    public String getUploadToken() {

        String token = "";
        try {
            JSONObject json = new JSONObject();

            long deadline = System.currentTimeMillis() / 1000 + qiNiuConfig.getEffective() * 60 * 60;
            json.put("deadline", deadline);
            json.put("scope", qiNiuConfig.getBucketName());

            String encodeToString = UrlSafeBase64.encodeToString(json.toString().getBytes());
            byte[] sign = hmacSHA1Encrypt(encodeToString, qiNiuConfig.getSecretKey());
            String encodedSign = UrlSafeBase64.encodeToString(sign);
            token = qiNiuConfig.getAccessKey() + ':' + encodedSign + ':' + encodeToString;
        } catch (Exception e) {
            log.error("获取七牛上传文件token失败。错误信息：", e);
        }
        return token;
    }

    private byte[] hmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {

        byte[] data = encryptKey.getBytes(qiNiuConfig.getEncoding());
        SecretKey secretKey = new SecretKeySpec(data, qiNiuConfig.getMacName());
        Mac mac = Mac.getInstance(qiNiuConfig.getMacName());
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(qiNiuConfig.getEncoding());

        return mac.doFinal(text);
    }
}

