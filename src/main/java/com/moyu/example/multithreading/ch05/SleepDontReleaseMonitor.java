package com.moyu.example.multithreading.ch05;

/***
 *      描述:     展示sleep不释放锁, 等待sleep超时后, 正常退出synchronized代码块才释放锁
 */
public class SleepDontReleaseMonitor {

    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor = new SleepDontReleaseMonitor();
        Runnable runnable = sleepDontReleaseMonitor.take();

        /***
         *      可以看到线程在等待5s后继续执行, 退出synchronized代码块后另外一个线程才能进入synchronized代码块
         */
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    public Runnable take() {
        return () -> {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " 获得到锁.");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 即将释放锁.");
            }
        };
    }
}
