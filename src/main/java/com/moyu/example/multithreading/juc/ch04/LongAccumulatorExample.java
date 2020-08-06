package com.moyu.example.multithreading.juc.ch04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/***
 *      描述:     Accumulator使用例子
 */
public class LongAccumulatorExample {

    public static void main(String[] args) {

        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> {
            System.out.println("x -> " + x + " y -> " + y);
            return x + y;
        }, 0);


        // 等价

//        LongAccumulator accumulator = new LongAccumulator(new LongBinaryOperator() {
//            @Override
//            public long applyAsLong(long left, long right) {
//                return left + right;
//            }
//        }, 0);



        ExecutorService executorService =
                Executors.newFixedThreadPool(20);
        IntStream.range(1, 10)
                .forEach(x -> {
                    executorService.execute(() -> longAccumulator.accumulate(x));
//                    executorService.execute(() -> accumulator.accumulate(x));
                });

        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }

        System.out.println("结果1为: " + longAccumulator.getThenReset());
//        System.out.println("结果2为: " + accumulator.getThenReset());
    }
}
