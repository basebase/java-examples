package com.moyu.example.multithreading.ch06;

/***
 *      描述:     设置守护线程晚于main线程之前, 验证用户线程结束守护线程是不是也退出了
 */
public class ThreadDaemon {
    public static void main(String[] args) throws InterruptedException {


        Thread daemonThread = new Thread(() -> {
            int count = 0;
            while (count++ < 1000) {
                try {
                    System.out.println(Thread.currentThread().getName() + " : " + count);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "daemonThread");

        /**
         *      设置线程为守护线程
         */
        daemonThread.setDaemon(true);
        daemonThread.start();

        System.out.println(Thread.currentThread().getName() + " 线程开始等待中...");
        Thread.sleep(5000);

        /***
         *     可以看到当main线程结束睡眠的同时, 我们的程序就结束了, 而不在意我们的守护线程有没有执行完毕
         */

        System.out.println(Thread.currentThread().getName() + " 线程结束了...");
    }
}
