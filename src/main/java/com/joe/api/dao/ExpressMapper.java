package com.joe.api.dao;

import com.joe.api.po.Express;

public interface ExpressMapper {
    int deleteByPrimaryKey(Integer expressId);

    int insert(Express record);

    int insertSelective(Express record);

    Express selectByPrimaryKey(Integer expressId);

    int updateByPrimaryKeySelective(Express record);

}