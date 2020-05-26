package com.moyu.example.multithreading.ch07;

/***
 *      描述:     如果只有main线程的程序, 会抛出异常异常信息。
 *               但是, 如果是多线程, 子线程发生异常, 会出现什么情况?
 */
public class ExceptionInChildThread {

    public static void main(String[] args) {

        /***
         *     可以看到, 子线程如果发生异常信息, 会把异常信息抛出来, 但是不影响主线程main的执行
         *     我们可以看到控制台下, main线程依旧把循环体内容全部输出, 而子线程的异常信息会沉没在一大串内容中...
         */

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " SS ");
            int i = 1 / 0;
            System.out.println(Thread.currentThread().getName() + " GG ");
        }).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
