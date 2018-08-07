package com.joe.api.dao;

import com.joe.api.po.Config;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    List<Config> queryConfigByType(Integer configType);

    List<Config> queryConfigByTypeAndKey(@Param(value = "configKey") Integer configKey, @Param(value = "configKey") Integer configType);
}