package com.joe.util.mvc;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 返回体中分页模型
 * 和App端定义好的规则, 总条数叫total, 数据叫contents
 */
public class ResponsePageEntity {

    /**
     * 记录总条数
     */
    private int total;

    /**
     * 数据
     */
    private Object contents;

    public ResponsePageEntity() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getContents() {
        return contents;
    }

    public void setContents(Object contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
