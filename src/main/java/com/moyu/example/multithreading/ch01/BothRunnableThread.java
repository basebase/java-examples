package com.moyu.example.multithreading.ch01;

/***
 *   描述:      同时使用Runnable和Thread两种方式实现线程
 */
public class BothRunnableThread {
    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable run()...");
            }
        }) {
            @Override
            public void run() {
                System.out.println("Thread run() ...");
            }
        }.start();
    }
}
