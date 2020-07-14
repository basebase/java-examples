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

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(task(), "Thread-" + i).start();

            // 按顺序启动线程, 避免在启动的过程中造成优先问题
            // 现在线程的启动执行顺序一定是Thread-0到Thread-9
            Thread.sleep(100);
        }
    }


    private static Runnable task() {
        return () -> {


            /****
             *
             *      如果是公平锁:
             *          如果是公平锁的话, 首先Thread-0获取到锁, 然后执行, 但是期间Thread-1到Thread-9的线程
             *          都被加入到等待队列中了, 所以, Thread-0想在一次打水的话会加入到队列的队尾中。
             *
             *          此时, 按照我们的构想, 队列应该为
             *             Thread-1 -> Thread-2 -> Thread-3 -> Thread-4 -> Thread-5 ->
             *             Thread-6 -> Thread-7 -> Thread-8 -> Thread-9 -> Thread-0
             *
             *          所以, 我们打印输出的结果顺序一定是:
             *              Thread-0 -> Thread-1 -> Thread-2 -> Thread-3 -> Thread-4
             *              Thread-5 -> Thread-6 -> Thread-7 -> Thread-8 -> Thread-9
             *              ...
             *              Thread-0 -> Thread-1 -> Thread-2 -> Thread-3 -> Thread-4
             *              Thread-5 -> Thread-6 -> Thread-7 -> Thread-8 -> Thread-9
             *
             *
             *
             *      如果是非公平锁:
             *          如果是非公平锁的话, 第一个线程Thread-0仍然是先获取到锁, 但是在执行过程中
             *          Thread-1到Thread-9都进入等待队列中排队去了....
             *
             *          此时, 我们第一次打水完成后, 线程Thread-0释放锁, 但是请注意, 当前线程Thread-0
             *          还没有进入阻塞状态, 而我们要唤醒等待队列中的线程是需要时间的, 所以线程Thread-0
             *          可能会再一次获取到锁, 从而在打水一次。
             *
             *          所以输出结果会出现:
             *              Thread-0 -> Thread-0 -> Thread-1 -> Thread-1 -> ... Thread-9
             *
             */

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
