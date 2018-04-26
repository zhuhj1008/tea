package com.joe.manager.api.permisison;

import com.alibaba.fastjson.JSON;
import com.joe.manager.BaseTest;
import com.joe.manager.api.permission.pojo.Permission;
import com.joe.manager.api.permission.service.PermissionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 权限测试类
 * create by JOE on 2018-03-14 13:27
 **/
public class TestPermissionService extends BaseTest{

    @Autowired
    PermissionService permissionService;

    @Test(timeout = 2000)
    public void testSelectByPrimaryKey(){
        int id = 1;
        Permission permission = permissionService.selectByPrimaryKey(id);
        System.out.println(JSON.toJSONString(permission));
    }

    @Test
    public void testGetPermissionListByUserId(){
        int id = 1;
        List<Permission> permissionListByUserId = permissionService.getPermissionListByUserId(id);
        System.out.println(JSON.toJSONString(permissionListByUserId));
    }




}
