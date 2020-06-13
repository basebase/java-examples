package com.moyu.example.multithreading.juc.ch01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *      描述:     演示newFixedThreadPool的使用
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(5);

        /***
         *      我们这里创建出只有5个线程的线程池, 而且输出的线程名字都是1~5之间的, 没有多出来的临时线程?
         *      我们点击进入newFixedThreadPool()方法看看, 可以看到newFixedThreadPool传入的corePoolSize和maximumPoolSize
         *      是一样大小的, 所有肯定不会有新的线程被创建出来, 并且可以看到keepAliveTime线程活跃时间设置为0秒, 这个是没问题的
         *      毕竟都不会新建临时线程, 更本就不需要活跃时间进行销毁临时线程数。
         *
         *      还有一个点就是, newFixedThreadPool()方法传入的workQueue参数是LinkedBlockingQueue(无界队列)
         *      这也就意味着, 我们的队列永远不会满, maximumPoolSize也永远不会起作用, 这就会导致刚才我们说的一个问题
         *      如果提交任务比消费任务快很多, 任务队列很有可能就会出现OOM
         */
        for (int i = 0; i < 100; i++) {
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
