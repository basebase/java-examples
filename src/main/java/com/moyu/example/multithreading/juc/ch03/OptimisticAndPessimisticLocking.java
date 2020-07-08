package com.moyu.example.multithreading.juc.ch03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/***
 *     描述:      展示乐观锁和悲观锁的例子, 乐观锁在不加锁的情况下依旧可以保证数据的正确性
 */
public class OptimisticAndPessimisticLocking {

    static double amount = 0.0;
    static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {

        ExecutorService executorService =
                Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                synchronized (OptimisticAndPessimisticLocking.class) {
                    amount += 10.0;
                    System.out.println("amount => " + amount);


                    /***
                     *    在这里, 一个乐观锁对象放入到悲观锁中, 这代表乐观锁是加锁了吗?
                     *      不是的, 这只是乐观锁与加锁操作合作的一个例子, 不能改变, "乐观锁本身不加锁"的事实。
                     */
                    // count.incrementAndGet();
                }

                /***
                 *  就算不在synchronized中依旧不会出现线程安全问题,
                 *  可以看到这里没有使用synchronized和lock锁住任何资源对象。
                 */
                count.incrementAndGet(); // 自增加1
                System.out.println("count => " + count.get());
            });
        }

        executorService.shutdown();
    }
}
