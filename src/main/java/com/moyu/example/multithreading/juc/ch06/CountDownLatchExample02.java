package com.moyu.example.multithreading.juc.ch06;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *
 *      描述:     CountDownLatch使用, 让多个子线程等待一个主线程
 */

public class CountDownLatchExample02 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService =
                Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int id = i + 1;
            executorService.execute(() -> {
                System.out.println("学生编号: " + id + " 领取到试卷, 等待做题...");
                try {
                    latch.await();
                    System.out.println("学生编号: " + id + " 开始做题...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(3000);     // 等待一定时间, 老师正在拿着试卷走回教室
        System.out.println("各位同学开始考试...");
        latch.countDown();
        executorService.shutdown();
    }
}
