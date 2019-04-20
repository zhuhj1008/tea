package com.joe.common;

import lombok.Data;

@Data
public class ApiParam<T> {

    private String timeStr;

    private String signature;

    private T body;
}
