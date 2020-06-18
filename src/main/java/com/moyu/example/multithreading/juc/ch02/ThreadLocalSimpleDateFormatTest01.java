package com.moyu.example.multithreading.juc.ch02;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 *      描述:         在多个线程下不使用ThreadLocal使用SimpleDateFormat类
 *                   SimpleDateFormat类是线程不安全的
 */

public class ThreadLocalSimpleDateFormatTest01 {

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public static void main(String[] args) {

        new Thread(() -> {
            String date =
                    new ThreadLocalSimpleDateFormatTest01().date(10);
            System.out.println(date);
        }, "Thread-A").start();

        new Thread(() -> {
            String date =
                    new ThreadLocalSimpleDateFormatTest01().date(1000);
            System.out.println(date);
        }, "Thread-B").start();
    }
}
