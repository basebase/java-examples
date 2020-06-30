package com.moyu.example.multithreading.juc.ch02;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *      描述:    不正当使用ThreadLocal导致线程安全问题
 */
public class ThreadLocalSimpleDateFormatNoSafe {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return simpleDateFormat;
        }
    };

    private static ThreadLocal<SimpleDateFormat> threadLocal2 = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println("dateFormat => " + dateFormat);
            return dateFormat;
        }
    };

//    private static ThreadLocal<SimpleDateFormat> threadLocal2 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Date date = new Date(1000 * 1);
                String format = null;
                String format2 = null;
                SimpleDateFormat dateFormat = threadLocal.get();
                SimpleDateFormat dateFormat2 = threadLocal2.get();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                format = dateFormat.format(date);
                format2 = dateFormat2.format(date);

                System.out.println(Thread.currentThread().getName() + " date : " + format);
                System.out.println(Thread.currentThread().getName() + " date2 : " + format2);
            }
        }, "Thread-A").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                Date date = new Date(1000 * 2);
                String format = null;
                String format2 = null;
                SimpleDateFormat dateFormat = threadLocal.get();

                SimpleDateFormat dateFormat2 = threadLocal2.get();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                format = dateFormat.format(date);
                format2 = dateFormat2.format(date);

                System.out.println(Thread.currentThread().getName() + " date : " + format);
                System.out.println(Thread.currentThread().getName() + " date2 : " + format2);
            }
        }, "Thread-B").start();
    }
}
