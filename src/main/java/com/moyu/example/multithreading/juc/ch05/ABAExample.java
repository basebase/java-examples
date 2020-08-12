package com.moyu.example.multithreading.juc.ch05;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/***
 *      描述:     ABA问题
 */
public class ABAExample {

    public static AtomicInteger cas = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(task1(0, 1), "Thread-A");
        Thread t2 = new Thread(task2(0, 2), "Thread-B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("最终的结果为: " + cas.get());
    }

    public static Runnable task1(int expect, int update) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行程序...");
            int random = new Random().nextInt(6) + 1;

            System.out.println(Thread.currentThread().getName() + " 程序执行需要: " + random + " 秒");
            try {
                Thread.sleep(random * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (!cas.compareAndSet(expect, update)) {
            }

            System.out.println(Thread.currentThread().getName() + " 输出结果为: " + cas.get());
        };
    }


    public static Runnable task2(int expect, int update) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行程序...");
            int random = new Random().nextInt(1) + 1;
            System.out.println(Thread.currentThread().getName() + " 程序执行需要: " + random + " 秒");
            try {
                Thread.sleep(random * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (!cas.compareAndSet(expect, update)) {

            }

            System.out.println(Thread.currentThread().getName() + " 当前结果为: " + cas.get());

            System.out.println(Thread.currentThread().getName() + " 开始消费金额 ");
            for (int i = 0; i < 2; i++) {
                cas.decrementAndGet();
            }

            System.out.println(Thread.currentThread().getName() + " 消费和的金额为: " + cas.get());
        };
    }
}
