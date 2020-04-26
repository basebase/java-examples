package com.moyu.example.multithreading.ch03;

/**
 *     描述:      使用stop()方法停止线程, 会导致线程运行到一半就停止, 没办法完成一个基本单位的操作
 *               比如说: 假设一个班级中有10个小组, 每个小组都需要领取自己的作业, 如果使用stop()停止线程可能会出现有的学生没有领取到自己的作业
 */
public class StopThread {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(getRunnable());
        t1.start();
        Thread.sleep(2000);
        t1.stop();
    }

    public static Runnable getRunnable() {
        return () -> {

            /***
             *
             *  这里, 就是我们的一个基本的执行块。
             *      然而, 当线程调用了stop()方法时候, 而stop()方法不会抛出异常而是一个Error子类ThreadDeath。
             *      通过结果可以看到, stop()停止线程后, 一个班级下领取到作业, 有的没有。
             *      而我们之前使用interrupt()方法, 可以在catch中做一些善后操作, 比如回滚数据等。
             *      而stop()方法完全没有任何商量余地, 直接就GG了。所以, 不推荐使用stop()方法
             *
             */

            for (int i = 0; i < 10; i ++) {
                System.out.println("第 " + i + " 个班级下");
                for (int j = 0; j < 10; j ++) {
                    System.out.println("====> 第 " + i + " 个班级下第 " + j + " 个小组领取作业完成");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("会执行我catch吗?");
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("exit.");
        };
    }
}
