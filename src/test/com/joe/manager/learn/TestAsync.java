package com.joe.manager.learn;

import com.joe.learn.AsyncTask;
import com.joe.manager.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 异步执行测试类
 * create by Joe on 2018-07-18 13:04
 **/
public class TestAsync extends BaseTest {

    @Autowired
    AsyncTask asyncTask;

    @Test
    public void testTask() throws InterruptedException, ExecutionException {

        long begin = System.currentTimeMillis();
        asyncTask.taskOne();
        asyncTask.taskTwo();
        long end1 = System.currentTimeMillis();
        System.out.println("普通任务执行时间" + (end1 - begin));

        Future<String> task1 = asyncTask.taskAsyncOne();

        Future<String> task2 = asyncTask.taskAsyncTwo();
        String s = task2.get();
        System.out.println(s);

        while (true){
            if(task1.isDone() && task2.isDone()){
                break;
            }
            Thread.sleep(1000);
        }

        long end2 = System.currentTimeMillis();
        System.out.println("异步任务执行时间" + (end2 - end1));

    }


}
