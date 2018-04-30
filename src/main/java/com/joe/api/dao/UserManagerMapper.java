package com.joe.api.dao;

import com.joe.api.po.UserManager;

public interface UserManagerMapper {

    int insertSelective(UserManager record);

    int deleteByPrimaryKey(Integer managerId);

    int updateByPrimaryKeySelective(UserManager record);

    UserManager selectByPrimaryKey(Integer managerId);

}