package com.joe.api.service;

import com.joe.api.dao.UserManagerMapper;
import com.joe.api.po.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserManagerService {

    @Autowired
    UserManagerMapper userManagerMapper;


    /**
     * @param userManager
     * @return
     */
    public int addUserManager(UserManager userManager) {

        userManager.setCreateTime(new Date());
        userManager.setUpdateTime(new Date());
        userManager.setEnable(false);
        userManagerMapper.insertSelective(userManager);
        return userManager.getManagerId();
    }

    /**
     * 修改管理员信息
     *
     * @param userManager
     * @return
     */
    public int modifyManager(UserManager userManager) {

        return userManagerMapper.updateByPrimaryKeySelective(userManager);
    }


    /**
     * 删除管理员（逻辑删除）
     *
     * @param managerId
     * @return
     */
    public int dropManager(int managerId) {
        return userManagerMapper.deleteByPrimaryKey(managerId);
    }


}
