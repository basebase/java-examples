package com.moyu.example.multithreading.ch05;

/**
 *
 *      描述:     两个线程交替打印0~100的奇偶数, 使用wait/notify来实现
 */
public class WaitNotifyPrintOddEvenWait {

    private static int count;
    private static final Integer SIZE = 100;
    private static Object obj = new Object();

    public static void main(String[] args) {

        // 偶数线程
        new Thread(() -> {
            while (count < SIZE) {
                synchronized (obj) {
                    System.out.println("===================> even");
                    if (count % 2 == 0) {

                        obj.notify();

                        System.out.println(Thread.currentThread().getName() + " : " + count);
                        count ++;


                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "even").start();

        // 奇数线程
        new Thread(() -> {
            while (count < SIZE) {
                synchronized (obj) {
                    System.out.println("===================> odd");
                    if (count % 2 == 1) {
                        obj.notify();
                        System.out.println(Thread.currentThread().getName() + " : " + count);
                        count ++;

                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, "odd").start();
    }
}
