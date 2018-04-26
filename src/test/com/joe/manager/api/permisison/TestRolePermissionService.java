package com.joe.manager.api.permisison;

import com.joe.manager.BaseTest;
import com.joe.manager.api.permission.pojo.RolePermission;
import com.joe.manager.api.permission.service.RolePermissionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * RolePermissionService测试类
 * create by JOE on 2018-03-14 13:48
 **/
public class TestRolePermissionService extends BaseTest{

    @Autowired
    RolePermissionService rolePermissionService;

    @Test(timeout = 2000)
    public void testGetPermissionListByRoleId(){

        int roleId = 1;
        List<RolePermission> rolePerList = rolePermissionService.getPermissionListByRoleId(roleId);

        logger.info(String.valueOf(rolePerList.size()));

    }
}
