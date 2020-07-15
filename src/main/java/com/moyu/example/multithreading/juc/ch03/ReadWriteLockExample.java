package com.moyu.example.multithreading.juc.ch03;


import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 *
 *      描述:     共享锁和排它锁使用例子
 *                  这里, 我们的读锁就是共享锁。写锁就是排它锁
 *
 *               对比参考例子ReadWriteLockExample2.java
 */
public class ReadWriteLockExample {

    private static ReentrantReadWriteLock reentrantReadWriteLock =
            new ReentrantReadWriteLock();


    // 读锁
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    // 写锁
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

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
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到读锁");
                int random = new Random().nextInt(4) + 1;
                System.out.println(Thread.currentThread().getName() + " 读取数据, 需要" + random + " 秒");
                Thread.sleep(random * 1000);
                System.out.println(Thread.currentThread().getName() + " 读取数据值为: " + amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        };
    }


    private static Runnable writeTask() {
        return () -> {
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到写锁");
                int random = new Random().nextInt(4) + 1;
                System.out.println(Thread.currentThread().getName() + " 更新数据, 需要" + random + " 秒");
                Thread.sleep(random * 1000);
                amount += 10;
                System.out.println(Thread.currentThread().getName() + " 更新数据完成...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        };
    }

}
