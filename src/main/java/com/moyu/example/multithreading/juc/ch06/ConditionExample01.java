package com.moyu.example.multithreading.juc.ch06;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *      描述:     使用Condition配合线程阻塞和唤醒
 */
public class ConditionExample01 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Runnable task1() {
        return () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 任务初始化中...");
                int r = new Random().nextInt(3) + 1;
                System.out.println(Thread.currentThread().getName() + " 任务初始化预估时间为: " + r + "秒");
                Thread.sleep(r * 1000);
                System.out.println(Thread.currentThread().getName() + " 初始化完成, 唤醒等待任务...");
                condition.signal();     // 唤醒线程
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
                System.out.println(Thread.currentThread().getName() + " 等待任务初始化完成...");
                condition.await();      // 阻塞线程
                System.out.println(Thread.currentThread().getName() + " 任务初始化完成, 执行后续任务...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionExample01 conditionExample01 = new ConditionExample01();
        new Thread(conditionExample01.task2(), "Thread-B").start();
        Thread.sleep(10);       // 让线程Thread-B先执行进行等待, Thread-A优先执行则会出现线程无法被唤醒
        new Thread(conditionExample01.task1(), "Thread-A").start();
    }
}
