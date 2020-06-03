package com.moyu.example.multithreading.ch08;

import java.util.concurrent.atomic.AtomicInteger;

/***
 *      描述:     多个线程运行结果出错, 运行后的结果会小于预期结果, 找出问题并解决
 */
public class MultiThreadsErrorMarkSync {

    static int value = 0;
    static final boolean[] marked = new boolean[30000];

    static AtomicInteger realCount = new AtomicInteger(0);
    static AtomicInteger wrongCount = new AtomicInteger(0);
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = task();
        Thread t1 = new Thread(r, "Thread-A");
        Thread t2 = new Thread(r, "Thread-B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("最终的结果为: " + value);
        System.out.println("真正运行的次数: " + realCount.get());
        System.out.println("错误次数: " + wrongCount.get());

    }

    public static Runnable task() {
        return () -> {
            for (int i = 0; i < 10000; i++) {
                value ++;
                realCount.incrementAndGet();

//                System.out.println("init : " + Thread.currentThread().getName());

                /***
                 *  可以发现即使加了sync进行保护, 我们的数据依旧对应不回去?
                 *      其实还是在value++上
                 *
                 *  假设线程1的value=1进入了sync代码块, 在判断有没有写入之前线程2进来了并进行value++后value=2
                 *  这个时候, 在切回线程1, 此时由于sync的可见性value的值为2, 而之前的1丢失了。
                 *
                 *  这就导致我们的数据被篡改了。
                 *
                 */
                synchronized (lock) {
//                    System.out.println("sync : " + Thread.currentThread().getName());
                    if (marked[value]) {
                        System.out.println("发生了错误: " + value);
                        wrongCount.incrementAndGet();
                    }
                    marked[value] = true;
                }
            }
        };
    }
}
