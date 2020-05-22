package com.moyu.example.multithreading.ch05;

/***
 *      描述:     join期间被中断的演示
 */
public class JoinInterrupt {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始执行...");
            try {

                /***
                 *      在子线程中调用主线程main的中断方法, main线程正在等待子线程Thread-0
                 */
                mainThread.interrupt();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 执行结束...");
        });

        System.out.println(Thread.currentThread().getName() + " 开始");

        t1.start();
        try {

            /***
             *      当子线程调用主线程的中断方法, 被中断的是主线程
             *      如果我们没有把异常信息传递给线程0的话, 子线程0还是一个阻塞状态等待5s后才结束
             *      如果我们调用线程0的中断方法后, 可以立即终止子线程0
             */
            t1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 被中断了.");
            e.printStackTrace();
            t1.interrupt(); // 如果我们注释掉此语句后, 可以看到程序不会立即终止, 而是进入一段等待时间。
        }

        System.out.println(Thread.currentThread().getName() + " 结束");
    }
}
