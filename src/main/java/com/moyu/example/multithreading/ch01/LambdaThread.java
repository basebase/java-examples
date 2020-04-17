package com.moyu.example.multithreading.ch01;

/***
 *      描述:     lambda表达式创建线程
 */
public class LambdaThread {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
