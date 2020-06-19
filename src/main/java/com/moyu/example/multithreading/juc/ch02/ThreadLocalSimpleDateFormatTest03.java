package com.moyu.example.multithreading.juc.ch02;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *      描述:     多个线程共同使用SimpleDateFormat对象, 使用synchronized解决数据错误问题
 */

public class ThreadLocalSimpleDateFormatTest03 {

    /***
     *  注意这里一定要使用static, 在使用线程池提交任务的时候, 我们每次都是new出ThreadLocalSimpleDateFormatTest02对象的
     *  这还是会导致每个线程都是独立的SimpleDateFormat对象。
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static HashSet<String> hashSet = new HashSet();

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);

        /***
         *      由于出错的点是在格式化的时候, 所以我们对dateFormat.format进行加锁保护
         */
        String format = null;
        synchronized (ThreadLocalSimpleDateFormatTest03.class) {
            format = dateFormat.format(date);
            if (!hashSet.add(format))
                throw new IllegalArgumentException("出现重复值了...");
        }
        return format;
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService =
                Executors.newFixedThreadPool(10);

        /***
         *     使用synchronized加锁保护后, 确实不会出现重复的时间数据。
         *     但是这意味着, 每个线程都需要等待其它线程处理完释放锁, 假设我们有N多任务, 那么等待的时间可能会很长。
         *     这并不是一个非常理想的解决方案。
         */

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(() -> {
                String date =
                        new ThreadLocalSimpleDateFormatTest03().date(finalI);
                System.out.println(date);
            });

        }

        executorService.shutdown();
    }
}
