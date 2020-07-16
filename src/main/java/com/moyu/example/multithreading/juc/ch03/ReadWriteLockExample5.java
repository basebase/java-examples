package com.moyu.example.multithreading.juc.ch03;


import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 *
 *      描述:     读写锁, 使用公平的方式创建不允许任何线程插队执行
 */
public class ReadWriteLockExample5 {

    // 创建一个公平的读写锁
    private static ReentrantReadWriteLock reentrantReadWriteLock =
            new ReentrantReadWriteLock(true);


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
        new Thread(writeTask(), "Thread-D").start();
        new Thread(readTask(), "Thread-E").start();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                new Thread(readTask(), "Child-" + i).start();
            }
        }).start();

    }


    private static Runnable readTask() {
        return () -> {

            System.out.println(Thread.currentThread().getName() + " 尝试获取读锁");
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到读锁");
                Thread.sleep(20);
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

            System.out.println(Thread.currentThread().getName() + " 尝试获取写锁");
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到写锁");
                // 这里时间设置短点, 否则我们所有子线程都提交完成了, 看不到效果了
                Thread.sleep(30);
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
