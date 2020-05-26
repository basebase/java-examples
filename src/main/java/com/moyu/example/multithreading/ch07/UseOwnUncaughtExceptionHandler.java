package com.moyu.example.multithreading.ch07;

import java.util.logging.Level;
import java.util.logging.Logger;

/***
 *      描述:     使用自定义的线程异常处理器
 */

public class UseOwnUncaughtExceptionHandler {

    public static void main(String[] args) throws InterruptedException {

        /***
         *       自定义线程异常处理器, 这里使用lambda表达式进行书写
         */
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {

            /***
             *      当线程发生异常时, 就会回调此方法。
             *      在这个方法里面, 可以做一些处理, 例如对线程的重启, 发送报警短信等等...
             */

            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.WARNING, "线程出现异常, 终止中...", e);
            System.out.println("捕获线程 " + t.getName() + " 异常 " + e);
        });

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



        // 虽然main线程还是会继续执行, 但是我们的子线程却会被捕获到, 我们可以感知到子线程发生了异常从而进行处理。
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
