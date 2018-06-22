package com.joe.business.common.wx;

import com.joe.util.http.HttpClientUtil;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

public class WeiXinAuthUtil {

    private static final String CHAR_SET = "UTF-8";
    private static final String APP_ID = "wx7787aa5b85cd4a58";
    private static final String SECRET = "723f7eb41927cbd33106ac322be458c3";
    private static final String GRANT_TYPE = "authorization_code";


    /**
     * 获取用户微信唯一标识open id 和当前会话session key
     * @param code 用户点击微信登录传过来的code
     * @return
     */
    public static String getOpenIdAndSessionKey(String code){
        Map<String,String> param = new HashedMap();
        param.put("appid",APP_ID);
        param.put("secret",SECRET);
        param.put("js_code",code);
        param.put("grant_type",GRANT_TYPE);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        return HttpClientUtil.doPost(url, param, CHAR_SET);
    }


}
