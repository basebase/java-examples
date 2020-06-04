package com.moyu.example.multithreading.ch08;

/***
 *      描述:     线程安全, 死锁问题
 *               手动写一个一定会死锁的例子
 */
public class MultiThreadsDeadLockError {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();


    public static void main(String[] args) {
        Runnable r1 = task1();
        Runnable r2 = task2();

        Thread t1 = new Thread(r1, "Thread-A");
        Thread t2 = new Thread(r2, "Thread-B");

        t1.start();
        t2.start();

    }

    private static Runnable task1() {
        return () -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 获取到lock1了...");
                System.out.println(Thread.currentThread().getName() + " 尝试获取lock2...");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " 获取到lock2了...");
                }
            }
        };
    }

    private static Runnable task2() {
        return () -> {
            synchronized (lock2) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 获取到lock2了...");
                System.out.println(Thread.currentThread().getName() + " 尝试获取lock1...");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " 获取到lock1了...");
                }
            }
        };
    }
}
