package com.moyu.example.multithreading.ch05;

/**
 *      描述:     如果使用Thread类的wait方法, 当线程执行并退出会调用notify/notifyAll方法
 */
public class ThreadExitCallBackNotify {

    public static void main(String[] args) {

        Thread lockThread = new Thread(lockThread());
        Object lock = new Object();

        /***
         *      一个是不同的Object对象, 一个是Thread实例, 使用Thread线程在退出的时候会调用notify方法
         */
        Runnable runnable = taskN1(lockThread);
//        Runnable runnable = taskN1(lock);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t1.start();
        t2.start();
        lockThread.start(); // 但是, 如果线程不调用start方法的话, 依旧和普通的对象锁一样, 不会调用notify方法
    }

    public static Runnable lockThread() {
        return () -> {
            System.out.println("lockThread start ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lockThread end ...");
        };
    }

    public static Runnable taskN1(Object lock) {
        return () -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " start ...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end ...");
            }
        };
    }
}
