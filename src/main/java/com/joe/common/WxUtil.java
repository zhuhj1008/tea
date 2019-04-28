package com.joe.common;

import java.util.*;

public class WxUtil {

    private WxUtil() {
    }

    /**
     * 获取微信签名sign
     *
     * @param map 请求参数集合
     * @return 微信请求签名串
     */
    public static String getSign(SortedMap<Object, Object> map, String key) {
        StringBuilder sb = new StringBuilder();
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
        sb.append("key=" + key);
        return MD5Util.md5Encode(sb.toString(), "UTF-8").toUpperCase();
    }

    /**
     * 获取随机数
     *
     * @return
     */
    public static String createNonceString() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder res = new StringBuilder();
        Random rd = new Random();
        for (int i = 0; i < 16; i++) {
            res.append(chars.charAt(rd.nextInt(chars.length() - 1)));
        }
        return res.toString();
    }

    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuilder sb = new StringBuilder();
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
