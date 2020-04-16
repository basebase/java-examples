package com.moyu.example.multithreading.ch01;

/***
 * 描述:     利用Thread实现创建线程
 */
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("利用Thread实现多线程: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++)
            new ThreadStyle().start();
    }
}
