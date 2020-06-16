package com.moyu.example.multithreading.juc.ch01;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***
 *      描述:     CallerRunsPolicy拒绝策略使用, 将任务分给调用线程来执行
 */
public class CallerRunsPolicyTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(3, 3, 60, TimeUnit.SECONDS, new SynchronousQueue<>());
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());


        /***
         *      可以看到, 这里是由main线程提交的任务, 所以交给main线程来执行
         */
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " 正在执行...");
            });
        }

        Thread.sleep(10000);

        /***
         *      我们创建了一个Thread-A线程提交任务, 就使用Thread-A线程执行任务
         */
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                threadPoolExecutor.execute(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " run...");
                });
            }

        }, "Thread-A").start();
    }
}
