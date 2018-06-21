package com.joe.business.common.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joe.util.http.HttpClientUtil;
import org.apache.commons.collections.map.HashedMap;
import sun.net.www.http.HttpClient;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class AuthUtil {

    private static final String CHAR_SET = "UTF-8";
    private static final String APP_ID = "wx7787aa5b85cd4a58";
    private static final String SECRET = "723f7eb41927cbd33106ac322be458c3";
    private static final String CODE = "071pp8uK0Pkxx72ps7uK0ydQtK0pp8uY";
    private static final String GRANT_TYPE = "authorization_code";


    /**
     *
     * @param code 用户点击微信登录传过来的code
     * @return
     */
    public static String getSessionKey(String code){
        Map<String,String> param = new HashedMap();
        param.put("appid",APP_ID);
        param.put("secret",SECRET);
        param.put("js_code",CODE);
        param.put("grant_type",GRANT_TYPE);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        return HttpClientUtil.doPost(url, param, CHAR_SET);
    }



    public static void main(String[] args) throws UnsupportedEncodingException {

    }
}
