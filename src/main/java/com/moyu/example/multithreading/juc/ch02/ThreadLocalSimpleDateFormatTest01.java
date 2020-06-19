package com.moyu.example.multithreading.juc.ch02;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *      描述:     通过线程池创建N多个线程打印出指定的时间
 */

public class ThreadLocalSimpleDateFormatTest01 {

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService =
                Executors.newFixedThreadPool(10);

        /****
         *      从输出上来看, 数据是没有任何问题的。
         *      是的, 这个例子中是使用SimpleDateFormat是没有问题的, 因为每个线程都会new一个SimpleDateFormat对象
         *      所以每个对象都持有自己的SimpleDateFormat对象互不干扰。
         *
         *      但是请注意, 我们现在是循环遍历提交1000次, 这就意味着我们要创建1000个SimpleDateFormat对象
         *      如果是1w个任务呢? 要创建1w个SimpleDateFormat对象? 显然这是不行的
         */

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(() -> {
                String date =
                        new ThreadLocalSimpleDateFormatTest01().date(finalI);
                System.out.println(date);
            });

//            Thread.sleep(100);
        }

        executorService.shutdown();
    }
}
