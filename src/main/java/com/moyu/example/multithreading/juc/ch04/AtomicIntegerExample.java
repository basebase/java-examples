package com.moyu.example.multithreading.juc.ch04;


import java.util.concurrent.atomic.AtomicInteger;

/***
 *      描述:     Atomic基本类型使用方法, 多线程环境下原子类不加锁依旧保持线程安全, 而非原子类则无法保证
 */
public class AtomicIntegerExample {

    // 创建一个原子变量
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    // 创建一个非原子变量, 多线程环境下会出现安全问题
    public static volatile Integer basic = 0;


    public static void increment() {
        atomicInteger.getAndIncrement();        // 该方法是获取并自增数据
//        atomicInteger.getAndAdd(10);        // 如果不想自增加1, 可以自定义加想要的值, 还可以是负数
    }

    public static void basicAdd() {
        basic ++;                             //  由于不是原子变量, 所以会出现线程安全问题
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(task(), "Thread-A");
        Thread t2 = new Thread(task(), "Thread-B");

        t1.start();
        t2.start();

        // 等待t1和t2执行完毕
        t1.join();
        t2.join();

        System.out.println("原子变量输出结果为: " + atomicInteger.get());
        System.out.println("非原子变量输出结果为: " + basic);
    }

    public static Runnable task() {
        return () -> {
            for (int i = 0; i < 1000; i++) {
                increment();
                basicAdd();
            }
        };
    }
}
