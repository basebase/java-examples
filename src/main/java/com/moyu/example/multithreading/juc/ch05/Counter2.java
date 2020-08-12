package com.moyu.example.multithreading.juc.ch05;

/***
 *
 *      描述:     使用CAS算法累加变量
 */
public class Counter2 {

    private EmulatedCAS value = new EmulatedCAS();

    public int getValue() {
        return value.getValue();
    }

    public int increment() {
        int readValue = getValue();

        /***
         *      这里我们就是使用自己模拟的CAS算法实现的, 当我们的值不同的时候就循环直到正确为止
         */

        while (value.compareAndSwap(readValue, readValue + 1) != readValue) {
            readValue = value.getValue();
        }

//        System.out.println("当前线程: " + Thread.currentThread().getName() + " 输出结果为: " + readValue);

        return ++ readValue;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter2 c = new Counter2();

        Thread[] t = new Thread[100000];
        for (int i = 0; i < 100000; i++) {
            t[i] = new Thread(() -> c.increment(), "Thread-" + (i + 1));
        }

        for (int i = 0; i < 100000; i++) {
            t[i].start();
        }

        for (int i = 0; i < 100000; i++) {
            t[i].join();
        }

        System.out.println("结果为: " + c.getValue());

    }
}
