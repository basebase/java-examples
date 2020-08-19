package com.moyu.example.multithreading.juc.ch06;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *      描述:     CyclicBarrier例子使用
 */
public class CyclicBarrierExample03 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println(Thread.currentThread().getName() + " 调用, 统一执行所有任务");
        });

        for (int i = 0; i < 15; i++) {
            new Thread(task(cyclicBarrier), "Thread-" + i).start();
        }
    }

    public static Runnable task(CyclicBarrier cyclicBarrier) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " 开始初始化");
            int r = new Random().nextInt(5) + 1;
            System.out.println(Thread.currentThread().getName() + " 预估初始化时间为: " + r + "秒");
            try {
                Thread.sleep(r * 1000);
                System.out.println(Thread.currentThread().getName() + " 初始化完成, 等待其它线程任务初始化完成");
                cyclicBarrier.await();      // 等待, 并将等待线程数量减1

                System.out.println(Thread.currentThread().getName() + " 开始执行任务...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
    }
}
