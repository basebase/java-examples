package com.moyu.example.multithreading.juc.ch01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *      描述:     缓存线程池使用
 */
public class CachedThreadPoolTest {

    public static void main(String[] args) {

        /***
         *      该线程池会创建出很多线程, 并在1分钟后进行回收。
         *      我们查看newCachedThreadPool线程池创建原理发现, 其中workQueue使用的是SynchronousQueue
         *      这个队列是不存储数据的, 直接交互的, 查看maximumPoolSize参数发现其设置为Integer.MAX_VALUE
         *      如此大的数量线程数几乎是塞不满的, 所以在输出线程名称时候发现很多不重名的线程。
         *
         *      不过, 如此一来也会出现OOM, 毕竟创建线程也是需要内存的, 并且线程之间的切换开销也不低
         */

        ExecutorService executorService =
                Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
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
