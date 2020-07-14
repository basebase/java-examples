package com.moyu.example.multithreading.juc.ch03;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *      描述:     公平锁&非公平锁展示
 */
public class FairAndNonFairLockExample {
//    公平锁
//    private static Lock lock = new ReentrantLock(true);
    // 非公平锁
    private static Lock lock = new ReentrantLock(false);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(task(), "Thread-" + i).start();
        }
    }


    private static Runnable task() {
        return () -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁...");
                System.out.println(Thread.currentThread().getName() + " 开始打水...");
                long ms = new Random().nextInt(5);
                System.out.println(Thread.currentThread().getName() + " 需要 " + ms + "秒打水完成");
                Thread.sleep(ms * 1000);
                System.out.println(Thread.currentThread().getName() + " 打水完成, 释放锁...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            /***
             *  或许打完一次水, 还想在继续打一次水
             */

            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁...");
                System.out.println(Thread.currentThread().getName() + " 开始打水...");
                long ms = new Random().nextInt(5);
                System.out.println(Thread.currentThread().getName() + " 需要 " + ms + "秒打水完成 ");
                Thread.sleep(ms * 1000);
                System.out.println(Thread.currentThread().getName() + " 打水完成, 释放锁...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
    }
}
