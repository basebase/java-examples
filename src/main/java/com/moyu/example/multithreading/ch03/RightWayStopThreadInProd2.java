package com.moyu.example.multithreading.ch03;

/***
 *      描述:     在catch中调用Thread.currentThread().interrupt()来恢复设置中断状态。
 *               以便在后续的执行中, 依然能够检查到刚才发生了线程中断。(解决RightWayStopThreadInProd中断问题)
 */
public class RightWayStopThreadInProd2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(getRunnable());
        t1.start();

        // 休眠1s后, t1线程休眠2s, 这样就能触发阻塞异常
        Thread.sleep(1000);
        t1.interrupt();

    }

    public static Runnable getRunnable() {
        return () -> {
            int num = 0;
            while (num < 10000) {

                // 检查线程是否中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("响应线程中断, 退出中...");
                    break ;
                }

                System.out.println("当前值为: " + num);
                num ++;

                // ①    不用处理异常信息, 方法已经处理了, 并重新设置了中断信息
//                reInterrupt();

                // ②    在run方法中自己处理异常信息, 重新设置中断信息
                try {
                    throwInMethod();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        };
    }

    /***
     * 捕获异常信息, 不吞并异常信息, 重新设置中断信息。
     */
    private static void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private static void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }
}