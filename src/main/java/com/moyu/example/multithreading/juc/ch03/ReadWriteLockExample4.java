package com.moyu.example.multithreading.juc.ch03;


import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 *
 *      描述:     读写锁, 读可以插队
 */
public class ReadWriteLockExample4 {

    // 其实可以不用设置false, 默认就是非公平的, 这里只不过显示设置方便看的更清楚
    private static ReentrantReadWriteLock reentrantReadWriteLock =
            new ReentrantReadWriteLock(false);


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

        /***
         *
         *  上面整体的执行流程还是不会改变, 但是, 这里我们既然想让读锁线程插队, 那么我们就要做点不一样的。
         *      现在, 假设我们执行Thread-A后, Thread-B,C,D,E等都加入到等待队列中去了, 而当线程A释放锁后
         *      等待队列的头结点就是Thread-B, 按照我们的说法, 只有头结点是读锁, 我们新提交的线程(读锁线程)才有可能插队执行,
         *      也就是说, 当线程A结束, 只要我们插入新的读锁线程, 就一定可以触发插队的策略。
         *
         *  所以, 如何在释放写锁后, 等待队列中的头结点还是读锁呢? 怎么知道呢?
         *      这里, 我们也不知道什么时候回结束, 所以通过子线程创建N多个线程一直提交任务, 这样就会触发插队策略
         */

        new Thread(() -> {

            /***
             *      这里创建1000个线程去读取内容, 当Thread-A释放写锁之后, 就有机会执行了
             */
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
