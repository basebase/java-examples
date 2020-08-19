package com.moyu.example.multithreading.juc.ch06;

import java.util.Random;
import java.util.concurrent.*;

/**
 *      描述:     CyclicBarrier例子, 使用单例的线程池数量不满足等待线程数量出现阻塞, 由于只有1个线程而我们需要等待的线程数量为2
 *                所以进入阻塞状态, 但可以使用CountDownLatch执行多次countDown()减少进而释放
 */
public class CyclicBarrierExample04 {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println(Thread.currentThread().getName() + " 调用, 统一执行所有任务");
        });

        CountDownLatch countDownLatch = new CountDownLatch(5);

//        for (int i = 0; i < 2; i++) {
//            new Thread(task1(cyclicBarrier), "Thread-" + i).start();
//        }

        ExecutorService executorService =
                Executors.newSingleThreadExecutor();
        for (int i = 0; i < 2; i++) {
            executorService.execute(task1(cyclicBarrier));
        }

        executorService.shutdown();


//        Thread.sleep(100);
//        new Thread(task2(countDownLatch), "Thread-A").start();
//
//        Thread.sleep(20000);    // 等待时间越长, task2任务阻塞越长,
//        for (int i = 0; i < 5; i++) {
//            countDownLatch.countDown();     // countDown()方法可以在任何你觉得合适的地方去调用执行, 不依赖线程数量
//            System.out.println("当前计数器的值为: " + countDownLatch.getCount());
//        }
    }

    public static Runnable task1(CyclicBarrier cyclicBarrier) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " 开始初始化");
            int r = new Random().nextInt(2) + 1;
            System.out.println(Thread.currentThread().getName() + " 预估初始化时间为: " + r + "秒");
            try {
                Thread.sleep(r * 1000);
                System.out.println(Thread.currentThread().getName() + " 初始化完成, 等待其它线程任务初始化完成");
                cyclicBarrier.await();      // 等待, 并将等待线程数量减1

                System.out.println(Thread.currentThread().getName() + " 开始执行任务...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
    }

    public static Runnable task2(CountDownLatch countDownLatch) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " 开始初始化任务");
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " 任务初始化完成, 等待计数器为0");
                countDownLatch.await();
                System.out.println("开始执行任务");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
