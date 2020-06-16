package com.moyu.example.multithreading.juc.ch01;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***
 *
 *      描述:     AbortPolicy拒绝策略使用, 抛出异常
 */
public class AbortPolicyTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(3, 3, 60, TimeUnit.SECONDS, new SynchronousQueue<>());
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        /**
         *      可以看到, 当线程池处理不过来的时候, 就会抛出java.util.concurrent.RejectedExecutionException异常。
         *      其实, 线程池默认就是使用此策略...
         *
         *      感觉多此一举了...
         */

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + finalI + " run ...");
            });
        }
    }
}
