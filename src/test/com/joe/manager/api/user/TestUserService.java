package com.joe.manager.api.user;

import com.joe.manager.BaseTest;
import com.joe.manager.api.user.dao.UserMapper;
import com.joe.manager.api.user.pojo.User;
import com.joe.manager.api.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * create by JOE on 2018-03-11 12:03
 **/
public class TestUserService extends BaseTest {

    @Autowired
    private UserService userService;


    @Test(timeout = 3000)
    public void testSelectByPrimaryKey() {
        int useId = 1;
        User user = userService.findUserByUserId(useId);
        System.out.println(user.getUserId());
        System.out.println(123);
    }

    @Test
    public void testSelectByMobilePhone() {
        String name = "1517521289";
        User user = userService.findUserByUserName(name);
        System.out.println(user);
    }

    @Test
    public void testGetUserListByParam() {
        User user = new User();
        user.setSalt("q");
        List<User> userListByParam = userService.findUserList(user, 1, 10);
        System.out.println(userListByParam);
    }



    @Test
    public void testAddTestData() {
        User user = new User();
        int total = 25;
        for (int i = 2; i < total; i++) {
            user.setUserId(null);
            user.setName("joe"+i);
            user.setBusinessId(i);
            user.setRoleId(1);
            user.setEmail("1916931393@qq.com");
            user.setSalt("a5daf");
            user.setPassword("root");
            user.setMobilePhone("15175225612");
            user.setEnable(true);
            user.setReadonly(false);
            userService.addUser(user);
            System.out.println(user.getUserId());
        }

    }


}
