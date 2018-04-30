package com.joe.manager.api.user;

import com.joe.api.enums.UserTypeEnum;
import com.joe.api.po.UserCustomer;
import com.joe.api.service.UserCustomerService;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserCustomerService extends BaseTest {

    @Autowired
    UserCustomerService userCustomerService;

    @Test
    public void testAdd(){
        UserCustomer userCustomer = new UserCustomer();
        userCustomer.setCustomerName("朱鸿钧");
        userCustomer.setEmail("1916931393@qq.com");
        userCustomer.setHeadPortrait("a8a5f6g4h2j3.jpg");
        userCustomer.setMobile("15175225612");
        userCustomer.setIntegral(52);
        userCustomer.setReceivingAddressDefault(1);
        userCustomer.setUserType(UserTypeEnum.CUSTOMER_ORDINARY.getValue());
        userCustomer.setCreateBy(1);
        userCustomer.setUpdateBy(1);
        int i = userCustomerService.addCustomer(userCustomer);
        logger.info("自增主键：{}",i);
    }
}
