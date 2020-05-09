package com.moyu.example.multithreading.ch03;

/***
 *      描述:     Thread.interrupted()方法使用
 *               当调用interrupted()方法时, 对象是"当前线程", 而不是调用该方法的对象。
 */
public class RightWayInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (; ;) {}
        });

        // 启动线程
        thread.start();

        // 设置中断标志
        thread.interrupt();

        /***
         *      输出结果:
         *             当前线程 main isInterrupted : true
         *             当前线程 main isInterrupted : false
         *             当前线程 main isInterrupted : false
         *             当前线程 main isInterrupted : true
         *
         *      是不是很奇怪这个输出, 使用interrupted()方法, 理论上第一次应该要获取为true然后在是false啊?
         *      而最后thread线程的中断标志还是true?
         *
         *      这就是上面说的, interrupted()方法不是根据当前调用的对象, 而是当前的线程来设置。
         *      而调用虽然使用thread.interrupted()但是, 其实是获取当前执行线程main是否有被中断的标记而不是具体的thread对象被中断的标记
         *
         */

        // 获取中断标志
        System.out.println("当前线程 " + Thread.currentThread().getName() + " isInterrupted : " + thread.isInterrupted());

        // 获取中断标志
        System.out.println("当前线程 " + Thread.currentThread().getName() + " isInterrupted : " + thread.interrupted());


        // 获取中断标志并重置
        System.out.println("当前线程 " + Thread.currentThread().getName() + " isInterrupted : " + Thread.interrupted());

        // 获取中断标志
        System.out.println("当前线程 " + Thread.currentThread().getName() + " isInterrupted : " + thread.isInterrupted());

        thread.join();

        System.out.println("程序结束...");
    }
}
