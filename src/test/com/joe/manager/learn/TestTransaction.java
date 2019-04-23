package com.joe.manager.learn;

import com.joe.api.po.UserManager;
import com.joe.api.service.UserManagerService;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务管理
 */
public class TestTransaction extends BaseTest {


    @Autowired
    UserManagerService userManagerService;

//    @Autowired
//    CacheManager cacheManager;


    @Test
    @Transactional
    public void testTransactional() {

        // 设置 manager_name 长度为5
        UserManager a = new UserManager();
        a.setManagerName("AAA");
        userManagerService.addUserManager(a);

        UserManager b = new UserManager();
        b.setManagerName("BBB");
        userManagerService.addUserManager(b);

        UserManager c = new UserManager();
        c.setManagerName("CCC");
        userManagerService.addUserManager(c);

        UserManager error = new UserManager();
        error.setManagerName("this is joe");
        userManagerService.addUserManager(error);

        UserManager d = new UserManager();
        d.setManagerName("DDD");
        userManagerService.addUserManager(d);

        UserManager e = new UserManager();
        e.setManagerName("EEE");
        userManagerService.addUserManager(e);
    }

    @Test
    public void testEhCache(){
        UserManager userManager = userManagerService.queryUserManagerById(1);
        System.out.println(userManager.getManagerName());

        UserManager updateUser = new UserManager();
        updateUser.setManagerId(1);
        updateUser.setManagerName("joe");
        userManagerService.modifyManager(updateUser);

        UserManager userManager1 = userManagerService.queryUserManagerById(1);
        System.out.println(userManager1.getManagerName());

        UserManager userManager2 = userManagerService.queryUserManagerById(1);
        System.out.println(userManager2.getManagerName());

        System.out.println(userManager.hashCode());
        System.out.println(userManager1.hashCode());
        System.out.println(userManager2.hashCode());
    }

    @Test
    public void testRedisCache(){
        UserManager userManager = userManagerService.queryUserManagerById(1);
        System.out.println(userManager.getManagerName());

        userManager.setManagerName("joe");
        userManagerService.modifyManager(userManager);

        UserManager userManager1 = userManagerService.queryUserManagerById(1);
        System.out.println(userManager1.getManagerName());

        System.out.println(userManager == userManager1);//为什么redis里取出的同一个对象不等


    }

}
