package com.joe.manager.api.restaurant;

import com.alibaba.fastjson.JSON;
import com.joe.manager.BaseTest;
import com.joe.manager.api.restaurant.pojo.RestaurantCarousel;
import com.joe.manager.api.restaurant.service.RestaurantCarouseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestRestaurantCarouse extends BaseTest {

    @Autowired
    RestaurantCarouseService restaurantCarouseService;

    @Test
    public void testAddCarouse(){

        RestaurantCarousel restaurantCarousel = new RestaurantCarousel();
        restaurantCarousel.setRestaurantId(6);
        restaurantCarousel.setName("套餐D");
        restaurantCarousel.setPhotoUrl("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2248134441,1789339535&fm=200&gp=0.jpg");
        restaurantCarousel.setLinkUrl("www.baidu.com");

        int i = restaurantCarouseService.addCarouse(restaurantCarousel);
        System.out.println(i);
    }

    @Test
    public void testFindCarouse(){
        int restaurantId = 6;
        List<RestaurantCarousel> carousels = restaurantCarouseService.findCarouselByRestaurantId(restaurantId,false);
        System.out.println("size:"+carousels.size());
        System.out.println(JSON.toJSONString(carousels));
    }

    @Test
    public void testModifyCarouselEnable(){

        restaurantCarouseService.modifyCarouselEnable(1,false);

    }
}
