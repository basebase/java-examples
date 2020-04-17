package com.moyu.example.multithreading.ch01;

/***
 *      描述:     匿名内部类创建线程
 */
public class InnerClassThread {
    public static void main(String[] args) {
        /**
         * 创建方式1: 直接匿名Thread类
         */
        new Thread() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName());
            }
        }.start();

        /***
         * 创建方式2: 传入target对象
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable : " + Thread.currentThread().getName());
            }
        }).start();
    }
}
