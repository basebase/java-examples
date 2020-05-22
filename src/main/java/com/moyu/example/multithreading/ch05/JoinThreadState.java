package com.moyu.example.multithreading.ch05;

/**
 *      描述:     main线程在join中是什么状态?
 */
public class JoinThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " 开始执行...");
                System.out.println(mainThread.getName() + " 线程状态: " + mainThread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        t1.start();
        /**
         *      当mian线程等待子线程执行的时候会进入WAITING(等待)状态, 等子线程执行完后进入RUNNABLE状态
         */
        t1.join();
        System.out.println(Thread.currentThread().getName() + " 线程状态 : "
                + Thread.currentThread().getState());
    }
}