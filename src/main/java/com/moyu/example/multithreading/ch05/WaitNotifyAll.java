package com.moyu.example.multithreading.ch05;

/***
 *      描述:         wait/notifyAll配合使用, 当前有三个线程A,B,C
 *                   A和B线程进入阻塞, 通过C线程唤醒A和B线程
 */
public class WaitNotifyAll {

    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = task();

        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);

        Thread threadC = new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " 调用notifyAll()方法");
                obj.notifyAll();

                /***
                 *   如果这里使用了notify()方法的话, 那么程序不会终止, 始终会有一个线程一直是等待状态
                 *   毕竟notify()方法只能唤醒一个线程。
                 */
//                obj.notify();
            }
        });


        /***
         *     执行说明:
         *         1. 执行步骤①和步骤②, 此时线程被启动, 至于谁优先执行取决系统调度。
         *
         *         2. 如果将步骤③注释掉, 可能会出现线程A或者线程B其中一个落后于线程C执行, 或者线程A和线程B都落后线程C执行
         *            这就会导致线程C的唤醒是无效的, 程序不会被终止。一直是等待状态。
         *
         *         3. 如果步骤③没有被注释的话, 则会顺利的唤醒线程A和线程B, 两个线程去获取锁进而执行后面的程序。
         */

        // ①
        threadA.start();
        // ②
        threadB.start();
        // ③
        Thread.sleep(1000);
        // ④
        threadC.start();
    }

    public static Runnable task() {
        return () -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " 开始执行任务.");
                try {
                    // 模拟正在执行任务, 休眠1s
                    Thread.sleep(300);
                    System.out.println(Thread.currentThread().getName() + " 开始释放锁进入阻塞.");
                    obj.wait();
                    System.out.println(Thread.currentThread().getName() + " 从新获取到锁, 执行完成.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
