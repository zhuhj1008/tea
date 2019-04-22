package com.joe.util.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 返回体中分页模型
 * 和App端定义好的规则, 总条数叫total, 数据叫contents
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePageEntity {

    /**
     * 记录总条数
     */
    private int total;

    /**
     * 数据
     */
    private Object contents;

}
