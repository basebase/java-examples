package com.moyu.example.multithreading.ch05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *      描述:        演示sleep不释放lock(需要手动释放)
 */

public class SleepDontReleaseLock {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        SleepDontReleaseLock sleepDontReleaseLock = new SleepDontReleaseLock();
        Runnable runnable = sleepDontReleaseLock.take();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    public Runnable take() {
        return () -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 获得到lock.");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " 释放lock.");
                lock.unlock(); // 这里如果不手动释放lock另外一个线程永远都是阻塞状态
            }
        };
    }
}
