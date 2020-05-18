package com.moyu.example.multithreading.ch05;

/**
 *      描述:     两个线程交替打印0~100的奇偶数, 使用synchronized来实现
 */
public class WaitNotifyPrintOddEvenSync {

    private static int count;
    private static final Integer SIZE = 100;
    private static Object obj = new Object();

    public static void main(String[] args) {

        /***
         *      1. 创简两个线程, 一个输出奇数一个输出偶数
         *      2. 通过synchronized来完成交替
         *
         *      下面的两个线程可以正确的输出奇偶数, 但是有以下问题:
         *              1. 由于synchronized是在while循环体内的, 所以当结束synchronized代码块线程又可以抢占需要的锁
         *              2. 哪个线程能抢到锁资源是不确定的, 所以当奇数线程正确输出后应该是偶数线程输出,但是锁却被奇数线程再次抢占
         *              3. 这就导致线程有很多空操作
         *
         */

        // 偶数线程
        new Thread(() -> {
            while (count < SIZE) {
                synchronized (obj) {  // 可能当前是偶数, 但是抢不到锁资源导致无法执行
                    System.out.println("===================> even");
                    if (count % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + count);
                        count ++;
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
                        System.out.println(Thread.currentThread().getName() + " : " + count);
                        count ++;
                    }
                }
            }
        }, "odd").start();
    }
}
