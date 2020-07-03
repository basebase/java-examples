package com.moyu.example.multithreading.juc.ch03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *      描述:     Lock不会和synchronized一样自动释放锁, 所以需要在finally中释放持有的锁,
 *               保证发生异常时锁能够被释放
 */
public class LockSimpleExample {

    static Lock lock = new ReentrantLock();
    static int count = 0;

    public static void main(String[] args) {

        /***
         *
         * 使用lock最佳写法就是在try/finally中加锁和释放锁, 否则会出现死锁问题。
         *
         * try {
         *  lock.lock();
         * } finally {
         *  lock.unlock();
         * }
         */

        ExecutorService executorService =
                Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    lock.lock();
                    ++ count;
                    System.out.println(Thread.currentThread().getName() +
                            " start job id : " + count);

                    /***
                     *     使用 1 / 0 抛出一个异常信息, 而我们的unlock再此之后
                     *     则无法释放锁, 导致后面的线程无法获取到锁执行任务
                     */
                    int j = 1 / 0;
                    lock.unlock();

                } finally {
                    // 如果这里不释放锁, 那么就会出现死锁了, 当前线程不释放锁, 其它线程无法获取到锁;
//                    lock.unlock();
                }
            });
        }

        executorService.shutdown();
    }
}
