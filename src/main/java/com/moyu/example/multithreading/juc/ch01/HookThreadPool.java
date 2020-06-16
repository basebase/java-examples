package com.moyu.example.multithreading.juc.ch01;

import java.util.concurrent.*;

/***
 *      描述:     线程池钩子方法的使用
 */

public class HookThreadPool extends ThreadPoolExecutor {

    /////////////////////////////////程序自动生成生成///////////////////////////////////////////////////
    public HookThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public HookThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public HookThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public HookThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


    // 钩子方法

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println(t.getName() + " 执行之前运行");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        System.out.println(Thread.currentThread().getName() + " 执行之后运行");
    }

    @Override
    protected void terminated() {
        super.terminated();
        System.out.println("线程池结束后执行...");
    }

    public static void main(String[] args) throws InterruptedException {
        HookThreadPool hookThreadPool =
                new HookThreadPool(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

        for (int i = 0; i < 100; i++) {
            hookThreadPool.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " 开始运行了...");
            });
        }

//        Thread.sleep(5000);
//        hookThreadPool.shutdown();

        hookThreadPool.shutdownNow();

    }
}
