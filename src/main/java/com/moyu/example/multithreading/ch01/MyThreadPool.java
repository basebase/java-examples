package com.moyu.example.multithreading.ch01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *      描述:     线程池创建线程的方法
 */
public class MyThreadPool {
    public static void main(String[] args) {

        /***
         * Executors.DefaultThreadFactory.newThread()
         * 还是利用Thread把我们的Runnable接口传入进去
         */
        ExecutorService executorService =
                Executors.newCachedThreadPool();
        for (int i = 0 ; i < 1000; i ++)
            executorService.submit(new Task());

    }
}

class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
    }
}
