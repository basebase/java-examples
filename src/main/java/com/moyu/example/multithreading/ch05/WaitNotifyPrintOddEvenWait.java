package com.moyu.example.multithreading.ch05;

/**
 *
 *      描述:     两个线程交替打印0~100的奇偶数, 使用wait/notify来实现
 */
public class WaitNotifyPrintOddEvenWait {

    private static int count;
    private static final Integer SIZE = 100;
    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {

        /***
         *
         *      1. 两个线程共用同一个任务, 无论哪个线程获取到锁都直接打印出内容。
         *      2. 打印完后, 唤醒其它线程, 当前线程休眠
         *
         *      为什么可以这么做?
         *          假设count从0开始, 我们偶数线程先行, 打印完后进入休眠, 这样奇数线程就能获取到锁
         *          然后奇数线程打印完后进入休眠, 接着偶数线程运行...依次运行, 直到count > SIZE结束任务
         *
         */

        Runnable runnable = task();
        /***
         *  这样的启动方式没问题, 但是有可能会串位, 谁也不知道哪个线程优先执行, 所以要保证正确的话,
         *  可以使用sleep来阻塞一下下
         */
        new Thread(runnable, "even").start();
        Thread.sleep(100);
        new Thread(runnable, "odd").start();

    }

    public static Runnable task() {
        return () -> {
            while (count < SIZE) {
                synchronized (obj) {
                    // 当前线程输出对应的值
                    System.out.println(Thread.currentThread().getName() + " : " + count++);
                    // 必须要唤醒上一次等待的线程
                    obj.notify();

                    // 如果count还小于SIZE就必须进入等待状态, 等待另外一个线程将其唤醒
                    if (count < SIZE) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
    }
}
