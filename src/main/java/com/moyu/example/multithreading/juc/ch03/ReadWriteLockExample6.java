package com.moyu.example.multithreading.juc.ch03;


import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 *
 *      描述:     读写锁, 锁不能升级只能降级
 */
public class ReadWriteLockExample6 {

    // 创建一个公平的读写锁
    private static ReentrantReadWriteLock reentrantReadWriteLock =
            new ReentrantReadWriteLock(false);


    // 读锁
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    // 写锁
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    // 共享资源
    private static int amount = 0;


    public static void main(String[] args) throws InterruptedException {

        new Thread(writeDowngradingTask(), "Thread-A").start();


//        new Thread(() -> {
//            for (int i = 0; i < 1000; i++) {
//                new Thread(readUpgradingTask(), "Child-" + i).start();
//            }
//        }).start();

        Thread.sleep(1000);
        System.out.println("===================");
        new Thread(readUpgradingTask(), "Thread-B").start();
    }


    private static Runnable readUpgradingTask() {
        return () -> {

            System.out.println(Thread.currentThread().getName() + " 尝试获取读锁");
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到读锁");
                Thread.sleep(20);

                System.out.println(Thread.currentThread().getName() + " 尝试升级为写锁");

                /****
                 *
                 *      读写锁不能升级锁, 会阻塞
                 */

                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + " 升级为写锁成功");

                System.out.println(Thread.currentThread().getName() + " 读取数据值为: " + amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
                readLock.unlock();
            }
        };
    }

    private static Runnable writeDowngradingTask() {
        return () -> {

            System.out.println(Thread.currentThread().getName() + " 尝试获取写锁");
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到写锁");
                Thread.sleep(10);
                amount += 10;
                System.out.println(Thread.currentThread().getName() + " 更新数据完成...");

                System.out.println(Thread.currentThread().getName() + " 获取读锁之前金额为: " + amount);

                System.out.println(Thread.currentThread().getName() + " 尝试降级为读锁");
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + " 降级为读锁成功");

                System.out.println(Thread.currentThread().getName() + " 降级为读锁后金额为: " + amount);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
                System.out.println(Thread.currentThread().getName() + " 成功释放写锁");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readLock.unlock();
                System.out.println(Thread.currentThread().getName() + " 成功释放读锁");
            }
        };
    }
}
