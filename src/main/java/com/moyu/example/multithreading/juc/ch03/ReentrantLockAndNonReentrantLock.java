package com.moyu.example.multithreading.juc.ch03;

import java.util.concurrent.locks.ReentrantLock;

/***
 *      描述: 可重入锁&非可重入锁演示
 */

public class ReentrantLockAndNonReentrantLock {

    private static ReentrantLock lock = new ReentrantLock();

    private static MyLock myLock = new MyLock();


    /***
     *      可重入锁实例, 我们可以看到, 任何一个线程, 只要获取到锁之后, 调用另外一个方法同样是可以进入的。
     */

    public static void eat() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 开始吃饭...");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " 吃饭结束...");
            play();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void play() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 开始游戏...");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " 结束游戏...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    /****
     *      非可重入锁, 同一个线程想再次进入方法时无法获取到锁, 进行阻塞, 又无法释放锁。导致死锁
     */

    public static void func1() {
        try {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "开始执行func1()方法...");
            func2();
            System.out.println(Thread.currentThread().getName() + "func1()方法执行完成...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            myLock.unlock();
        }
    }

    public static void func2() {
        try {
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "开始执行func2()方法...");
            func2();
            System.out.println(Thread.currentThread().getName() + "func2()方法执行完成...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            myLock.unlock();
        }
    }



    public static void main(String[] args) {
//        new Thread(() -> eat(), "Thread-A").start();
//        new Thread(() -> eat(), "Thread-B").start();
//        new Thread(() -> eat(), "Thread-C").start();

        new Thread(() -> func1(), "Thread-D").start();
        new Thread(() -> func1(), "Thread-E").start();
    }
}


class MyLock {
    private boolean isLock = false;

    /***
     * 加锁
     * @throws InterruptedException
     */
    public synchronized void lock() throws InterruptedException {
        while (isLock) {
            /**
             *  这里进行阻塞, 模拟必须先释放锁才能在获取锁, 但是当前线程无法释放锁...
             *  这里使用while是防止虚假唤醒调用notify之类的方法, 还必须要把状态变量进行检查
             */
            wait();
        }

        isLock = true;
    }

    /***
     *  释放锁
     */
    public synchronized void unlock() {
        isLock = false;
        notifyAll();
    }
}