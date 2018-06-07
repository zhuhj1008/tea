package com.joe.business.base;

import com.alibaba.fastjson.JSON;
import com.joe.util.mvc.RequestEntity;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * base controller
 * create by Joe on 2018-05-27 16:52
 **/
public class BaseController {


    /**
     * 获取请求体
     * @param request
     * @return
     */
    public String convertRequest2String(HttpServletRequest request){

        String result = null;
        try {
            ServletInputStream inputStream = request.getInputStream();
            StringBuilder content = new StringBuilder();
            byte[] b = new byte[1024];
            int lens = -1;
            while ((lens = inputStream.read(b)) > 0) {
                content.append(new String(b, 0, lens));
            }

            result = content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 得到请求体的参数
     * @param request
     * @return
     */
    public String getRequestParam(HttpServletRequest request){
        String requestStr = convertRequest2String(request);
        RequestEntity requestEntity = JSON.parseObject(requestStr, RequestEntity.class);
        if(requestEntity == null){
            return "";
        }

        return requestEntity.getBody();
    }


}
