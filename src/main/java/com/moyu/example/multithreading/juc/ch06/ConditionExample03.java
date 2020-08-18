package com.moyu.example.multithreading.juc.ch06;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *      描述:     创建多个Condition实例对象
 */
public class ConditionExample03 {

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();


    public Runnable task1() {
        return () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 开始执行任务");
                System.out.println(Thread.currentThread().getName() + " 依赖线程Thread-B进入等待");
                c1.await();
                System.out.println(Thread.currentThread().getName() + " 执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
    }

    public Runnable task2() {
        return () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 开始执行任务");
                System.out.println(Thread.currentThread().getName() + " 依赖线程Thread-C进入等待");
                c2.await();
                System.out.println(Thread.currentThread().getName() + " 执行结束, 唤醒Thread-A");
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
    }

    public Runnable task3() {
        return () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 开始执行任务");
                System.out.println(Thread.currentThread().getName() + " 执行结束, 唤醒Thread-B");
                c2.signal();
            } finally {
                lock.unlock();
            }
        };
    }


    public static void main(String[] args) throws InterruptedException {
        ConditionExample03 conditionExample03 = new ConditionExample03();
        new Thread(conditionExample03.task1(), "Thread-A").start();
        new Thread(conditionExample03.task2(), "Thread-B").start();
        Thread.sleep(10);
        new Thread(conditionExample03.task3(), "Thread-C").start();
    }
}
