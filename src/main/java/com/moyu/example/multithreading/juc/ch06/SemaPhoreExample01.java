package com.moyu.example.multithreading.juc.ch06;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/***
 *      描述:     SemaPhore使用例子, 控制线程访问量
 */

public class SemaPhoreExample01 {

    public static void main(String[] args) {

        // 创建信号量
        Semaphore semaphore = new Semaphore(3);

        ExecutorService executorService =
                Executors.newFixedThreadPool(50);

        for (int i = 0; i < 50; i++) {
            final String id = "User-" + (i + 1);
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + id + " 申请产假...");
                try {
//                    semaphore.acquire(3);
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " : " + id + " 申请产假成功!");
                    int r = new Random().nextInt(5) + 1;
                    System.out.println(Thread.currentThread().getName() + " : " + id + " 产假休息需要" + r + "天");
                    Thread.sleep(r * 1000);
                    System.out.println(Thread.currentThread().getName() + " : " + id + " 休息产假回来了, 释放许可证...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    semaphore.release(1);
                    semaphore.release();
                }
            });
        }

        executorService.shutdown();
    }
}
