package com.moyu.example.multithreading.ch01;

/***
 *  描述:   实现Runnable接口创建线程
 */
public class RunnableStyle implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread Name : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        RunnableStyle runnableStyle = new RunnableStyle();
        for (int i = 0 ; i < 10; i ++) {
            Thread t = new Thread(runnableStyle);
            t.start();
        }
    }
}
