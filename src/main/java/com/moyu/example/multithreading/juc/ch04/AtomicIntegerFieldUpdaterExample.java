package com.moyu.example.multithreading.juc.ch04;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/***
 *      描述:     AtomicIntegerFieldUpdater例子
 */

public class AtomicIntegerFieldUpdaterExample {

    /**
     *  利用反射的原理
     *      class: 就是我们要升级的类
     *      fieldName: 升级的字段
     */
    static AtomicIntegerFieldUpdater<Preson> updater =
            AtomicIntegerFieldUpdater.newUpdater(Preson.class, "score");

    static Preson p1 = new Preson();
    static Preson p2 = new Preson();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(task(), "Thread-A");
        Thread t2 = new Thread(task(), "Thread-B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("非原子操作变量: " + p1.score);
        System.out.println("升级原子操作变量: " + p2.score);
    }

    public static Runnable task() {
        return () -> {
            for (int i = 0; i < 1000; i++) {
                p1.score ++;
//                p1.setScore(p1.getScore() + 1);


//                updater.getAndIncrement(p2);
                /***
                 *      比较的是p2下面的score字段是否和当前的值相等, 如果相等就加1
                 *      其实和updater.getAndIncrement(p2);做的事情一样, 为了展示一下使用方法而已。
                 */
                updater.compareAndSet(p2, updater.get(p2), updater.getAndIncrement(p2));
            }
        };
    }
}


class Preson {
    /**
     *      1. 要被升级的字段不支持static
     *      2. 必须使用volatile修饰
     *      3. 如果变量设置为private即不可见, 即使提供了get和set方法
     */
    volatile int score;
//    private volatile int score;
//
//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        score = score;
//    }
}
