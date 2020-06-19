package com.moyu.example.multithreading.juc.ch02;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *      描述:     多个线程共同使用SimpleDateFormat对象, 使用ThreadLocal解决数据错误问题
 */
public class ThreadLocalSimpleDateFormatTest04 {

    private static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    private static HashSet<String> hashSet = new HashSet();

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        String format = null;
        // 获取到SimpleDateFormat对象副本, 每个对象都是独有一份的SimpleDateFormat对象, 互不干扰
        SimpleDateFormat dateFormat = threadLocal.get();
        System.out.println(dateFormat);
        format = dateFormat.format(date);
        if (!hashSet.add(format))
            throw new IllegalArgumentException("出现重复值了...");
        return format;
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService =
                Executors.newFixedThreadPool(10);

        /***
         *      使用ThreadLocal解决了多个线程共享同一个SimpleDateFormat实例
         *      可以看到输出的对象地址虽然相同, 但其实每个线程都是拿到该对象的副本, 不同的线程无法修改对方
         *      从而达到解决SimpleDateFormat多个线程不安全的方案
         */

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(() -> {
                String date =
                        new ThreadLocalSimpleDateFormatTest04().date(finalI);
                System.out.println(date);
            });

        }

        executorService.shutdown();
    }
}
