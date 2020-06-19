package com.moyu.example.multithreading.juc.ch02;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *      描述:     多个线程共同使用SimpleDateFormat对象, 引发数据错误
 */

public class ThreadLocalSimpleDateFormatTest02 {

    /***
     *  注意这里一定要使用static, 在使用线程池提交任务的时候, 我们每次都是new出ThreadLocalSimpleDateFormatTest02对象的
     *  这还是会导致每个线程都是独立的SimpleDateFormat对象。
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        return dateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService =
                Executors.newFixedThreadPool(10);

        /***
         *     运行下面的代码段的话就会出现时间有重复的效果了, 刚才ThreadLocalSimpleDateFormatTest01例子可没有出现重复数据
         *     这也就意味着多个线程下使用同一个SimpleDateFormat实例就会导致数据错误。
         *
         */

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(() -> {
                String date =
                        new ThreadLocalSimpleDateFormatTest02().date(finalI);
                System.out.println(date);
            });

        }

        executorService.shutdown();
    }
}
