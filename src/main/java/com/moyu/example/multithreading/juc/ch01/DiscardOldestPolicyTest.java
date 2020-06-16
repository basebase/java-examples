package com.moyu.example.multithreading.juc.ch01;

import java.util.concurrent.*;

/***
 *      描述:     DiscardOldestPolicy拒绝策略使用, 丢弃队列中最老的任务
 */
public class DiscardOldestPolicyTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,  new ArrayBlockingQueue<>(2));
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());


        /***
         *      现在我们的任务队列大小为2, 有一个核心线程执行。我们需要添加4个任务去执行, 会有下面的情况发生:
         *          1. 第一个任务将单线程占据500毫秒
         *          2. 执行程序成功地将第二个和第三个任务排队
         *          3. 当第四个任务到达时，丢弃最旧的策略将删除最早的任务，以便为新任务腾出空间
         *
         *          所以, 下面的queue只会有[Second, Third], 而First是最早提交的, 所以被移除了。
         *
         *          注意:
         *              丢弃最早的策略和优先级队列不能很好地配合使用。
         *              因为优先级队列的头具有最高优先级，所以我们可能会简单地失去最重要的任务。
         */

        threadPoolExecutor.execute(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        threadPoolExecutor.execute(() -> queue.offer("First"));
        threadPoolExecutor.execute(() -> queue.offer("Second"));
        threadPoolExecutor.execute(() -> queue.offer("Third"));

        Thread.sleep(1000);
        System.out.println(queue);

    }
}
