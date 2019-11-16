package joe.service;

import com.github.pagehelper.PageInfo;
import com.joe.Application;
import com.joe.api.po.Order;
import com.joe.api.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testA(){

        PageInfo<Order> orderPageInfo = orderService.queryOrderListByQueryDto(new Order(), 1, 10);
        System.out.println(orderPageInfo.getList());
    }
}
