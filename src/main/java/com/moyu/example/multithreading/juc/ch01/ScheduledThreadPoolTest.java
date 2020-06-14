package com.moyu.example.multithreading.juc.ch01;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *      描述:     调度线程池使用
 */
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);

        // 1秒之后执行任务
//        scheduledExecutorService.schedule(task(), 1, TimeUnit.SECONDS);

        // 初始化为1s执行之后, 每次等待3s后再一次执行
        scheduledExecutorService.scheduleAtFixedRate(task(), 1, 3, TimeUnit.SECONDS);
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
