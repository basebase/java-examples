package com.moyu.example.multithreading.ch01;


class ImplementsRunnableV2 implements Runnable {

    private int counter = 0;

    @Override
    public void run() {
        counter ++;
        System.out.println("ImplementsRunnable : Counter : " + counter);
    }
}

class ExtendsThreadV2 extends Thread {
    private int counter = 0;

    @Override
    public void run() {
        counter ++;
        System.out.println("ExtendsThread : Counter : " + counter);
    }
}


public class ThreadVsRunnableV2 {

    public static void main(String[] args) throws Exception {

        // 多个线程共享同一个对象
        ImplementsRunnableV2 rc = new ImplementsRunnableV2();
        Thread t1 = new Thread(rc);
        t1.start();
        Thread.sleep(1000); // 休眠1s

        Thread t2 = new Thread(rc);
        t2.start();
        Thread.sleep(1000);

        Thread t3 = new Thread(rc);
        t3.start();


        // 每个线程创建新的实例

        ExtendsThreadV2 et = new ExtendsThreadV2();

        Thread tc1 = new Thread(et);
        tc1.start();
        Thread.sleep(1000);


        Thread tc2 = new Thread(et);
        tc2.start();
        Thread.sleep(1000);


        Thread tc3 = new Thread(et);
        tc3.start();

    }
}
