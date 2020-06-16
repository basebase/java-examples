package com.moyu.example.multithreading.juc.ch01;

import java.util.concurrent.*;


/***
 *      描述:     DiscardPolicy拒绝策略使用, 直接丢弃任务
 */
public class DiscardPolicyTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,  new SynchronousQueue<>());
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());


        /***
         *      由于生产任务太多, 消费完全更不上。所以会导致后面任务都被丢弃掉
         */

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " run ...");
            });
        }


        /***
         *  如果在这里等待一定时间后, 线程池有可以使用的线程了, 下面的queue是可以offer进去的,
         *  如果线程池中的所有线程还在执行任务, 这个任务依旧是没有执行的机会, 队列为空
         */
        Thread.sleep(3000);

        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        threadPoolExecutor.execute(() -> {
            queue.offer("Discarded Result");
            System.out.println("Go...");
        });

        // 这里也需要等待一定时间, 线程池线程不一定offer进去了, 等待后, 程序正常可以看到队列长度是1
        Thread.sleep(1000);
        System.out.println("thread addWork queue size : " + queue.size());
    }
}
