package com.moyu.example.multithreading.ch07;

/***
 *      描述:     我们启动四个会抛出异常的线程程序, 在main线程(调用者)中进行捕获, 会发生什么情况?
 *                  1. 捕获到第一个线程异常后, 其余线程都不应该运行了, 并且打印出异常信息
 *                  2. main线程根本没有捕获到异常, 其余线程依旧运行并抛出异常信息
 */
public class CantCatchDirectly {

    public static void main(String[] args) {

        try {

            /***
             *      从运行结果上来看, main线程中并没有执行到catch中的代码, 代表子线程的异常在main线程没捕获到
             *      其余线程依旧执行并抛出各自的异常信息, 这是为什么呢?
             *
             *      每个线程可以想象成一个人, main线程是总负责人, 其余的子线程是工作人员, 而main线程说大家都去工作吧调用start()方法
             *      每个子线程都各自去完成自己的工作, 期间完成的怎么样main线程并不清楚, 而只有在main线程中不小心让一个工作线程启动1次以上
             *      start()方法, 这个时候main线程就会捕获到异常并终止程序。
             *
             *      总结下:
             *              每个线程都是独立的, 所以main线程中使用try/catch捕获子线程异常是无法捕捉到的,
             *              try/catch只能捕获对应线程内的异常(而当前对应的线程是main线程中发生的异常信息)
             *
             */

            new Thread(() -> {
                int i = 1 / 0;
            }).start();

            new Thread(() -> {
                int i = 1 / 0;
            }).start();

            new Thread(() -> {
                int i = 1 / 0;
            }).start();

            new Thread(() -> {
                int i = 1 / 0;
            }).start();
        } catch (ArithmeticException e) {
            System.out.println("检测到子线程异常啦!!!" + e);
            e.printStackTrace();
        }
    }
}
