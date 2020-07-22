package com.moyu.example.multithreading.juc.ch03;

import java.util.concurrent.atomic.AtomicReference;

/***
 *
 *
 *      描述:     一个实现自旋锁的例子
 */

public class SpinLockExample {

    // 利用原子引用类
    private AtomicReference<Thread> cas = new AtomicReference<>();

    /***
     *  加锁方法, 我们把当前的线程写入进去, 其它线程在比较的时候发现不是此线程就会一直循环执行
     */
    public void lock() {
        Thread curr = Thread.currentThread();
        while (!cas.compareAndSet(null, curr)) {
            // TODO
            System.out.println(Thread.currentThread().getName() + " 正在自旋中...");
        }
    }

    /***
     *  解锁方法, 获取到当前线程后将当前线程写入, 并将值更新为null此时其它线程又可以加锁
     */
    public void unlock() {
        Thread curr = Thread.currentThread();
        cas.compareAndSet(curr, null);
    }

    public static void main(String[] args) {
        SpinLockExample spinLock = new SpinLockExample();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " 开始尝试获取锁");
            try {
                spinLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取锁成功");
                System.out.println(Thread.currentThread().getName() + " 开始执行业务方法");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " 释放锁");
                spinLock.unlock();
            }
        };

        new Thread(runnable, "Thread-A").start();
        new Thread(runnable, "Thread-B").start();
        new Thread(runnable, "Thread-C").start();
    }
}
