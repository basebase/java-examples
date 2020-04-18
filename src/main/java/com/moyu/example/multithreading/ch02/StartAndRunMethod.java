package com.moyu.example.multithreading.ch02;

/***
 *      描述:     对比start和run方法两种启动线程的方式
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
          System.out.println(Thread.currentThread().getName());
        };

       runnable.run();
       new Thread(runnable).start();
    }
}
