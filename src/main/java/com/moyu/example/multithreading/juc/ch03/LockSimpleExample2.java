package com.moyu.example.multithreading.juc.ch03;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *      描述:     lock()方法造成死锁
 */
public class LockSimpleExample2 {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    lock1.lock();

                    System.out.println(Thread.currentThread().getName() + " 获取到lock1");

                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 尝试获取lock2");

                    // 获取lock2

                    try {

                        lock2.lock();

                        System.out.println(Thread.currentThread().getName() + " 获取到lock2");
                    } finally {
                        lock2.unlock();
                        System.out.println(Thread.currentThread().getName() + " 释放lock2");
                    }


                } finally {
                    lock1.unlock();
                    System.out.println(Thread.currentThread().getName() + " 释放lock1");
                }
            }
        }, "Thread-A").start();

        new Thread(() -> {

            try {

                lock2.lock();
                System.out.println(Thread.currentThread().getName() + " 获取到lock2");

                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " 尝试获取lock1");

                // 获取lock1

                try {
                    lock1.lock();
                    System.out.println(Thread.currentThread().getName() + " 获取到lock1");
                } finally {
                    lock1.unlock();
                    System.out.println(Thread.currentThread().getName() + " 释放lock1");
                }
            } finally {
                lock2.unlock();
                System.out.println(Thread.currentThread().getName() + " 释放lock2");
            }

        }, "Thread-B").start();
    }
}
