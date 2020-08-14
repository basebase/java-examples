package com.moyu.example.multithreading.juc.ch06;

import java.util.Random;
import java.util.concurrent.Semaphore;

/***
 *      描述:     SemaPhore使用例子, 在非获取许可证的线程中统一释放许可证
 */

public class SemaPhoreExample02 {

    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                threads[i] = new Thread(releaseTask(2), "Thread-" + i);
            } else {
                threads[i] = new Thread(acquireTask(), "Thread-" + i);
            }
        }

        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }
    }

    public static Runnable acquireTask() {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " 尝试获取许可证...");
            try {
                semaphore.acquire(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 获取到了许可证...");
            // 此任务不释放许可证
        };
    }

    public static Runnable releaseTask(int releaseNum) {
        return () -> {
            // 用来清除积压的许可证任务
            int r = new Random().nextInt(3) + 1;
            System.out.println(Thread.currentThread().getName() + " 正在清除积压任务, 耗时: " + r + "秒");
            try {
                Thread.sleep(r * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release(releaseNum);      // 如果注释掉这段代码, 则其余线程没有许可证可以会一直阻塞
            System.out.println(Thread.currentThread().getName() + " 清空积压任务, 又有许可证可以使用了...");
        };
    }
}
