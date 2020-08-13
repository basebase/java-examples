package com.moyu.example.multithreading.juc.ch06;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *
 *      描述:     CountDownLatch使用, 让一个主线程等待其余多个子线程执行完成后, 在执行。
 */

public class CountDownLatchExample01 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService =
                Executors.newFixedThreadPool(5);
        for (int i = 0; i < 4; i++) {
            final int id = i + 1;
            executorService.execute(() -> {
                System.out.println("用户ID: " + id + " 正在查阅任务...");
                int r = new Random().nextInt(6) + 1;
                System.out.println("用户ID: " + id + " 正在审批任务, 大约耗时: " + r + "秒");
                try {
                    Thread.sleep(r * 1000);
                    System.out.println("用户ID: " + id + " 审批完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();      // 最好放在finally中, 即使异常还是能将数量减少避免永久阻塞
                }
            });
        }

        System.out.println("提交任务主线程用户正在默默等待结果中...");
        latch.await();
        System.out.println("提交任务主线程用户审核通过啦!!!");

        executorService.shutdown();
    }
}
