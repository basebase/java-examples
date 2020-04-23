package com.moyu.example.multithreading.ch03;

/**
 *      描述:         在执行线程中循环调用sleep或者wait等方法(可以不判断当前线程是否中断状态)
 */
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                int num = 0;
                while (num < 1000 /* && !Thread.currentThread().isInterrupted() */) {
                    System.out.println("当前的值为: " + num);
                    num ++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(runnable);
        t1.start();

        t1.sleep(5000);
        t1.interrupt();
    }
}
