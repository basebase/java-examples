package com.moyu.example.multithreading.juc.ch04;

import java.util.concurrent.atomic.AtomicIntegerArray;

/***
 *
 *      描述:     原子数组, 数组中的元素都能保证原子性
 */

public class AtomicArrayExample {

    // 创建一个原子数组对象, 包含1000个元素, 里面元素初始化的值为0
    public static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);

    public static Runnable addTask() {
        return () -> {
            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                atomicIntegerArray.incrementAndGet(i);       // 以原子方式将索引i处的元素加1
            }
        };
    }

    public static Runnable subTask() {
        return () -> {
            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                atomicIntegerArray.decrementAndGet(i);       // 以原子方式将索引i的元素减1
            }
        };
    }


    public static void main(String[] args) throws InterruptedException {

        Thread[] addThreads = new Thread[100];
        Thread[] subThreads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            addThreads[i] = new Thread(addTask());
            subThreads[i] = new Thread(subTask());
            addThreads[i].start();
            subThreads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            addThreads[i].join();
            subThreads[i].join();
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0)         // 获取位置i的当前值
                throw new IllegalArgumentException("原子变量失效, 线程出现安全问题...");

            System.out.println(atomicIntegerArray.get(i));
        }

        System.out.println("安全输出");
    }
}