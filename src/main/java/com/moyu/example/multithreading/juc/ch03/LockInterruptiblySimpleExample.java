package com.moyu.example.multithreading.juc.ch03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *
 *      描述:     一个线程获取到锁, 另外一个线程等待锁, 两种情况下中断线程
 */

public class LockInterruptiblySimpleExample {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(task(), "Thread-A");
        Thread t2 = new Thread(task(), "Thread-B");

        t1.start();
        t2.start();

        Thread.sleep(2000);

        /***
         *
         *      当调用线程的interrupt来中断线程, 可能在不同时间节点会有不同的表现, 由于lockInterruptibly()方法本身就会抛出中断异常
         *      所以, 当线程还在等待获取锁的时候就可以被中断。当线程获取到锁依旧可以被中断
         *
         *      当t1线程获取到锁时, 调用interrupt时, 会输出 "休眠期间被中断",
         *      而当t1线程没有获取到锁时, 调用interrupt时, 会输出 "等锁期间被中断"
         *
         *      都可以正常的中断执行的线程
         */

//        t1.interrupt();
        t2.interrupt();

    }


    private static Runnable task() {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " 尝试获取lock");

            /***
             *      使用lockInterruptibly()就会抛出一个线程中断的异常, 所以我们还需要在里面写入try/finally来释放锁
             */

            /***
             *
             *      线程中断可能会出现两种情况:
             *          1. 当前线程正在等待锁的时候, 如果发出了中断的信号, 则就会输出 "等锁期间被中断"
             *          2. 当线程获取到锁时, 执行程序过程中收到中断信号, 就会输出 "休眠被中断"
             *
             *      无论线程是在等待过程中还是已经获取到锁的过程中, 都可以被正常的中断。
             *
             *      可以看到, 当线程获取到锁时, 会休眠50s, 如果没有中断的话另外一个线程则需要等待50s之后才能获取到锁
             *      而中断之后, 则立马就获取到我们所需要的锁信息。
             */

            try {
                lock.lockInterruptibly();
                try {
                    System.out.println(Thread.currentThread().getName() + " 获取到锁");
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " 休眠期间被中断");
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " 释放锁");
                }
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + " 等锁期间被中断");
            }
        };
    }
}
