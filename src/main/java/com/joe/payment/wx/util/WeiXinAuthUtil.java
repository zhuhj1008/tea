package com.joe.payment.wx.util;

import com.joe.payment.wx.dto.WxConfig;
import com.joe.util.encryption.MD5Util;
import com.joe.util.http.HttpClientUtil;
import org.apache.commons.collections.map.HashedMap;

import java.util.*;

public class WeiXinAuthUtil {


    /**
     * 获取用户微信唯一标识open id 和当前会话session key
     *
     * @param code 用户点击微信登录传过来的code
     * @return
     */
    public static String getOpenIdAndSessionKey(String code) {
        Map<String, String> param = new HashedMap();
        param.put("appid", WxConfig.APP_ID);
        param.put("secret", WxConfig.SECRET);
        param.put("js_code", code);
        param.put("grant_type", WxConfig.GRANT_TYPE);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        return HttpClientUtil.doPost(url, param, WxConfig.CHAR_SET);
    }

    /**
     * 获取微信签名sign
     *
     * @param map 请求参数集合
     * @return 微信请求签名串
     */
    public static String getSign(SortedMap<Object, Object> map) {
        StringBuffer sb = new StringBuffer();
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //参数中sign、key不参与签名加密
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + WxConfig.WE_PAY_KEY);
        String sign = MD5Util.MD5Encode(sb.toString(), WxConfig.CHAR_SET).toUpperCase();
        return sign;
    }

    /**
     * 获取随机数
     *
     * @return
     */
    public static String CreateNonceString() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }


}
