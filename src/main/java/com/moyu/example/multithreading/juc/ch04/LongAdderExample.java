package com.moyu.example.multithreading.juc.ch04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/***
 *
 *      描述:     LongAdder累加器例子
 */

public class LongAdderExample {


    static LongAdder add = new LongAdder();
    static AtomicLong atomicLong = new AtomicLong(0);

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(100);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            executorService.execute(task());
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            //
        }

        long end = System.currentTimeMillis();

        System.out.println("LongAdder消耗时长为: " + (end - start) + " 结果为: " + add.sum());



        executorService =
                Executors.newFixedThreadPool(100);
        start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            executorService.execute(task2());
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            //
        }

        end = System.currentTimeMillis();

        System.out.println("AtomicLong消耗时长为: " + (end - start) + " 结果为: " + atomicLong.get());



    }

    public static Runnable task() {
        return () -> {
            for (int i = 0; i < 10000; i++) {
//                add.increment();
                add.decrement();
            }
        };
    }


    public static Runnable task2() {
        return () -> {
            for (int i = 0; i < 10000; i++) {
//                add.increment();
                atomicLong.incrementAndGet();
            }
        };
    }
}
