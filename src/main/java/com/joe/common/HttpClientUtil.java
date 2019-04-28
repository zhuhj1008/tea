package com.joe.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 * create by Joe on 2018-06-21 12:54
 **/
@Slf4j
public class HttpClientUtil {

    private HttpClientUtil() {
    }

    private static final int TIMEOUT = 60000;

    private static final String CHARSET = "UTF-8";

    public static String sendGet(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(TIMEOUT)
                .setSocketTimeout(TIMEOUT)
                .setRedirectsEnabled(true)
                .build();
        HttpGet httpGet2 = new HttpGet(url);
        httpGet2.setConfig(requestConfig);
        String srtResult = "";
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet2);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                srtResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
            }
        } catch (IOException e) {
            log.error("发送请求失败,异常信息:{}", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("关闭资源异常,异常信息:{}", e);
            }
        }
        return srtResult;
    }

    public static String doPost(String url, Map<String, String> paramMap, String encoding) {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> paramList = new ArrayList();
            if (paramMap != null) {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, encoding));

            //发送请求，并取得响应体
            response = httpClient.execute(httpPost);

            //解析响应体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String body = EntityUtils.toString(entity, encoding);
                return body;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String postJson(String postUrl, String jonStr) {


        CloseableHttpResponse response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            //获取httpPost
            HttpPost httpPost = getHttpJsonPost(postUrl);
            StringEntity stringEntity = new StringEntity(jonStr, CHARSET);
            httpPost.setEntity(stringEntity);
            long start = System.currentTimeMillis();
            // 发送请求
            response = httpClient.execute(httpPost);
            log.info("HttpUtil 请求耗时：{}毫秒，请求路径：{}， 请求报文：{}", System.currentTimeMillis() - start, postUrl, jonStr);
            if (response == null) {
                log.error("请求返回 response 为空");
                return null;
            }
            int statusCode = response.getStatusLine().getStatusCode();
            log.info("HttpUtil 请求状态码为：{}", statusCode);

            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    log.error("HttpUtil 应答状态为 200，但HttpEntity，应答异常");
                    return null;
                }

                String responseContent = EntityUtils.toString(entity, CHARSET);

                // 请求成功，但无参数返回，需要发起查询
                if (StringUtils.isEmpty(responseContent)) {
                    log.error("HttpUtil 应答状态为 200，,应答报文为空");
                    return null;
                }
                log.info("HttpUtil 应答报文：{}", responseContent);
                return responseContent;

            } else if (statusCode > 200 && statusCode < 300) {
                // 2 xx （成功）表示成功处理了请求的状态代码，需要发起查询
                log.error("HttpUtil 请求状态为2xx，statusCode：{}", statusCode);
                return null;
            } else {
                // 请求状态http status不为2xx，请求失败
                log.error("HttpUtil 失败statusCode:" + statusCode);
                return null;
            }

        } catch (ClientProtocolException e) {
            // 请求出现异常
            log.error("HttpUtil 请求发送失败，ClientProtocolException原因：", e);
        } catch (IOException e) {
            // 请求发送失败，java的IOException代表HttpClient的通用的transport exceptions
            log.error("HttpUtil 请求发送失败，IOException原因：", e);

        }
        return null;
    }

    private static HttpPost getHttpJsonPost(String url) {
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输时长
        RequestConfig.Builder builder = RequestConfig.custom();
        // 连接超时，这定义了通过网络与服务器建立连接的超时时间。
        builder.setConnectTimeout(TIMEOUT);
        // 请求超时，这定义了Socket读数据的超时时间，即从服务器获取响应数据需要等待的时间，
        builder.setSocketTimeout(TIMEOUT);
        RequestConfig config = builder.build();
        httpPost.setConfig(config);
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Accept", "application/json");
        return httpPost;
    }

}
