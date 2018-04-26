package com.joe.api.dao;

import com.joe.api.po.UserManager;

public interface UserManagerMapper {
    int deleteByPrimaryKey(Integer managerId);

    int insert(UserManager record);

    int insertSelective(UserManager record);

    UserManager selectByPrimaryKey(Integer managerId);

    int updateByPrimaryKeySelective(UserManager record);

    int updateByPrimaryKey(UserManager record);
}