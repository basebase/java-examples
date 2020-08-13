package com.moyu.example.multithreading.juc.ch06;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *
 *      描述:     CountDownLatch使用, 多个子线程等待主线程以及主线程等待多个子线程
 */
public class CountDownLatchExample03 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch teach = new CountDownLatch(1);       // 老师的门闩
        CountDownLatch student = new CountDownLatch(5);     // 学生的门闩

        ExecutorService executorService =
                Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int id = i + 1;
            executorService.execute(() -> {
                System.out.println("学生编号: " + id + " 领取到试卷, 等待做题...");
                try {
                    teach.await();
                    System.out.println("学生编号: " + id + " 开始做题...");
                    int r = new Random().nextInt(10) + 1;
                    Thread.sleep(r * 1000);
                    System.out.println("学生编号: " + id + " 提交试卷");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    student.countDown();
                }
            });
        }

        Thread.sleep(3000);
        teach.countDown();  //  开始考试

        student.await();    // 等待学生都提交试卷
        System.out.println("所有学生都提交了试卷....");

//        student.await();
//        System.out.println("所有学生都提交了试卷....2");

        executorService.shutdown();
    }
}
