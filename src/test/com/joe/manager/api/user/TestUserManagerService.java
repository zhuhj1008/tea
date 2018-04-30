package com.joe.manager.api.user;

import com.joe.api.po.UserManager;
import com.joe.api.service.UserManagerService;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserManagerService extends BaseTest {

    @Autowired
    UserManagerService userManagerService;

    @Test
    public void testAdd(){

        UserManager userManager = new UserManager();
        userManager.setManagerName("root");
        userManager.setEmail("1916931393@qq.com");
        userManager.setGender("男");
        userManager.setMobile("15175225612");
        userManager.setHeadPortrait("aaa546r4f5n6c6a.png");
        userManager.setParentId(0);
        userManager.setCreateBy(1);
        userManager.setUpdateBy(1);
        userManagerService.addUserManager(userManager);
    }
}
