package com.joe.business.learn;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks  {

    @Scheduled(fixedRate = 5000)
    public void testAAA(){

        System.out.println(new Date());

    }
}
