
package com.joe.util.mvc;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class Pagination extends JSONObject implements Serializable {

    public Pagination() {
    }

    public int getPageSize() {
        int pageSize = getIntValue("pageSize");
        if(pageSize !=0){
            return pageSize;
        }
        return 10;
    }

    public int getPageNo() {
        int pageSize = getIntValue("pageNo");
        if(pageSize !=0){
            return pageSize;
        }
        return 1;
    }



    public JSONObject getOrder() {
        Object order = get("order");
        if(order != null){
            return (JSONObject)order;
        }
        return null;
    }


    public JSONObject getSearch() {
        Object search = get("search");
        if(search != null){
            return (JSONObject)search;
        }
        return null;
    }
}
