package com.moyu.example.multithreading.juc.ch01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *         描述:      newSingleThreadExecutor线程池使用例子
 */
public class singleThreadExecutorTest {

    public static void main(String[] args) {

        /***
         *      该线程池只会创建一个线程, 其原理几乎和newFixedThreadPool一样,
         *      唯一不同可能就是newFixedThreadPool可以自己传入所需的核心线程数, 而此线程池固定为1个线程
         */

        ExecutorService executorService =
                Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(task());
        }
    }

    private static Runnable task() {
        return () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        };
    }
}
