package com.joe.api.dao;

import com.joe.api.po.Config;

import java.util.List;

public interface ConfigMapper {

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    List<Config> queryAll();

    List<Config> queryConfigByType(Integer configType);

}