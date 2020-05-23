package com.moyu.example.multithreading.ch05;

/***
 *      描述:     yield方法使用
 */
public class Yield {
    public static void main(String[] args) {


        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 线程结束");
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 线程结束");
        });

        t1.start();
        t2.start();

        /****
         *      可以看到即使调用yield, main线程的输出内容也可能会最先出现, 然后是其它子线程的输出
         */

        Thread.yield();
        System.out.println(Thread.currentThread().getName() + " 线程退出");


    }
}
