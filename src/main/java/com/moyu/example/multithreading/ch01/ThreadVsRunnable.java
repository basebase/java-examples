package com.moyu.example.multithreading.ch01;

/****
 *   描述:        验证Runnable和Thread资源共享
 */


class ImplementsRunnable implements Runnable {

    private int counter = 0;

    @Override
    public void run() {
        counter ++;
        System.out.println("ImplementsRunnable : Counter : " + counter);
    }
}

class ExtendsThread extends Thread {
    private int counter = 0;

    @Override
    public void run() {
        counter ++;
        System.out.println("ExtendsThread : Counter : " + counter);
    }
}

public class ThreadVsRunnable {

    public static void main(String[] args) throws Exception {

        // 多个线程共享同一个对象
        ImplementsRunnable rc = new ImplementsRunnable();
        Thread t1 = new Thread(rc);
        t1.start();
        Thread.sleep(1000); // 休眠1s

        Thread t2 = new Thread(rc);
        t2.start();
        Thread.sleep(1000);

        Thread t3 = new Thread(rc);
        t3.start();


        // 每个线程创建新的实例

        ExtendsThread tc1 = new ExtendsThread();
        tc1.start();
        Thread.sleep(1000);


        ExtendsThread tc2 = new ExtendsThread();
        tc2.start();
        Thread.sleep(1000);


        ExtendsThread tc3 = new ExtendsThread();
        tc3.start();

    }
}
