package com.joe.manager.api.permisison;

import com.joe.manager.BaseTest;
import com.joe.manager.api.permission.pojo.UserRole;
import com.joe.manager.api.permission.service.UserRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * UserRoleService测试类
 * create by JOE on 2018-03-14 13:53
 **/
public class TestUserRoleService extends BaseTest {

    @Autowired
    UserRoleService userRoleService;

    @Test(timeout = 3000)
    public void testGetRoleListByUserId(){

        int userId = 1;
        List<UserRole> userRoleList = userRoleService.getRoleListByUserId(userId);

        logger.info(String.valueOf(userRoleList.size()));
    }
}
