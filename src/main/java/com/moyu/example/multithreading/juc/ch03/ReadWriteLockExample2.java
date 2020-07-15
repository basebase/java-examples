package com.moyu.example.multithreading.juc.ch03;


import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 *
 *      描述:     常说只是读取线程为什么还需要加锁? 通过这个例子证明,
 *               如果不使用读写锁, 读取出来的是脏数据。
 *
 *
 *               当线程在写入数据中, 其它线程去读取数据, 这个时候读取出来的数据是旧的值。
 *
 */
public class ReadWriteLockExample2 {

    private static ReentrantLock lock =
            new ReentrantLock();

    // 共享资源
    private static int amount = 0;

    public static void main(String[] args) {
        new Thread(writeTask(), "Thread-A").start();
        new Thread(readTask(), "Thread-B").start();
        new Thread(readTask(), "Thread-C").start();
        new Thread(readTask(), "Thread-D").start();
        new Thread(writeTask(), "Thread-E").start();
        new Thread(readTask(), "Thread-F").start();
        new Thread(writeTask(), "Thread-G").start();
        new Thread(readTask(), "Thread-H").start();
        new Thread(readTask(), "Thread-I").start();
    }


    private static Runnable readTask() {
        return () -> {
            try {
                int random = new Random().nextInt(4) + 1;
                System.out.println(Thread.currentThread().getName() + " 读取数据, 需要" + random + " 秒");
                Thread.sleep(random * 1000);
                System.out.println(Thread.currentThread().getName() + " 读取数据值为: " + amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }


    private static Runnable writeTask() {
        return () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到锁");
                int random = new Random().nextInt(4) + 1;
                System.out.println(Thread.currentThread().getName() + " 更新数据, 需要" + random + " 秒");
                Thread.sleep(random * 1000);
                amount += 10;
                System.out.println(Thread.currentThread().getName() + " 更新数据完成...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
    }

}
