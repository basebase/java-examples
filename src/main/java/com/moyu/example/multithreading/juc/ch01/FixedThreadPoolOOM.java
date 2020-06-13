package com.moyu.example.multithreading.juc.ch01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *      描述:     演示使用newFixedThreadPool出现OOM错误
 */
public class FixedThreadPoolOOM {

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(1);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(task());
        }
    }

    private static Runnable task() {
        /**
         *      也不是什么任务都会触发OOM的, 这个任务什么都不做, 就是超长时间等待
         *      这样, 消费速度慢下来, 生成速度提上去, 就会出现OOM
         */

        return () -> {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
