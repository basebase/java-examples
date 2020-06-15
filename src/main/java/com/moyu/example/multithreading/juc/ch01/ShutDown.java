package com.moyu.example.multithreading.juc.ch01;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 *      描述:     线程池关闭
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        /***
         *      既然是演示线程池的关闭, 这里就随意创建一个线程池进行演示.
         */

        ExecutorService executorService =
                Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.execute(task());
        }

        Thread.sleep(1000);

        // 在没调用shutdown之前一定是false
//        System.out.println(executorService.isShutdown());

        /***
         *     使用shutdown方法, 正在执行的任务以及在队列中等待的任务需要执行完成后在会结束, 在执行shutdown后
         *     新提交的任务不在处理并抛出异常。
         */
//        executorService.shutdown();

        /***
         *    当调用shutdown方法后, 再次提交任务, 那么就会抛出异常了
         */
//        executorService.execute(task());

        /***
         *      当我们调用完后, 并不知道是否真的关闭了线程池, 所以可以通过isShutdown方法进行辅助查看
         */
//        System.out.println(executorService.isShutdown());

        /**
         *      使用isTerminated方法, 可以清晰的知道当前线程池有没有关闭, 线程池中的任务有没有执行完成, 队列中的任务有没有清空
         *      如果有一个任务在执行, 返回的都是false, 否则为true
         */

//        System.out.println(executorService.isTerminated());
//        Thread.sleep(10000);
//        System.out.println(executorService.isTerminated());

        /***
         *     使用awaitTermination等待指定时间结束后, 返回当前线程池有没有关闭, 如果关闭了返回true, 否则false
         *     在等待的期间会进行阻塞, 底层原理使用了ReentrantLock
         */
//        boolean b = executorService.awaitTermination(3, TimeUnit.SECONDS);
//        boolean b = executorService.awaitTermination(10, TimeUnit.SECONDS);
//        System.out.println(b);


        /***
         *     使用shutdownNow方法会立即中断正在执行的任务, 线程池关闭, 不会等待正在执行的任务结束。
         *     并且, 会返回正在队列中的任务集合。
         *
         *     对于使用此方法, 需要对返回的任务集合做一些其它操作。
         */
        List<Runnable> runnables = executorService.shutdownNow();

    }

    private static Runnable task() {
        return () -> {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + "被中断了...");
            }
        };
    }
}
