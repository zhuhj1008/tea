package com.joe.manager.api.restaurant;

import com.alibaba.fastjson.JSON;
import com.joe.manager.BaseTest;
import com.joe.manager.api.restaurant.pojo.Dishes;
import com.joe.manager.api.restaurant.service.DishesService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TestDishes extends BaseTest {

        @Autowired
        DishesService dishesService;

        @Test
        public void testAddDishes(){
            Dishes dishes = new Dishes();
            dishes.setRestaurantId(6);
            dishes.setName("地三鲜");
            dishes.setPhoto("www.photo.tomato.com");
            dishes.setPrice(new BigDecimal(12.5));
            dishes.setDiscount(new BigDecimal(0.78));
            dishes.setFlavor("甜，辣");
            dishes.setTag("土豆茄子土豆");
            dishes.setCreateTime(new Date());
            dishes.setUpdateTime(new Date());
            dishes.setCreateUser(1);
            dishes.setUpdateUser(1);
            int i = dishesService.addDishes(dishes);
            System.out.println(i);
        }

        @Test
        public void testFindDishesByRestaurantId(){
            List<Dishes> dishesByRestaurantId = dishesService.findDishesByRestaurantId(6, 1, 10);

            System.out.println(JSON.toJSONString(dishesByRestaurantId));
        }

        @Test
        public void testCountDishesByDishesSelective(){
            Dishes dishes = new Dishes();
            dishes.setRestaurantId(1);
            dishes.setTag("%'1'%");
            Integer integer = dishesService.countDishesByDishesSelective(dishes);
            System.out.println(integer);
        }

        @Test
        public void testFindDishesWidth(){
            Dishes dishes = new Dishes();
            dishes.setName("鱼香肉丝");
            List<Dishes> dishesWidth = dishesService.findDishesWidth(dishes, 1, 10);
            System.out.println(JSON.toJSONString(dishesWidth));
        }
}
