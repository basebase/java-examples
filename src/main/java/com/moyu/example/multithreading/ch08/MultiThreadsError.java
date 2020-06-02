package com.moyu.example.multithreading.ch08;

/***
 *      描述:     多个线程运行结果出错, 运行后的结果会小于预期结果, 找出问题并解决
 */
public class MultiThreadsError {

    static int value = 0;
    public static void main(String[] args) throws InterruptedException {
        Runnable r = task();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("最终的结果为: " + value);
    }

    public static Runnable task() {
        return () -> {
            for (int i = 0; i < 10000; i++) {
                value ++;
            }
        };
    }
}
