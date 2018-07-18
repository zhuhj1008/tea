package com.joe.business.learn;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * 异步
 * create by  on 2018-07-18 13:05
 **/
@Component
public class AsyncTask {

    public void taskOne() throws InterruptedException {
        Thread.sleep(5000);
    }

    public void taskTwo() throws InterruptedException {
        Thread.sleep(7000);
    }

    @Async
    public Future<String> taskAsyncOne() throws InterruptedException {
        Thread.sleep(5000);
        return new AsyncResult<>("同步任务1完成");
    }

    @Async
    public Future<String> taskAsyncTwo() throws InterruptedException {
        Thread.sleep(7000);
        return new AsyncResult<>("同步任务2完成");
    }


}
