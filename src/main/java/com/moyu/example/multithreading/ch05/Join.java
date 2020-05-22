package com.moyu.example.multithreading.ch05;

/***
 *      描述:     join例子展示
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 执行完成");
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 执行完成");
        });

        /***
         *      执行顺序如下:
         *          1. 首先输出 main 开始执行
         *          2. 线程0和线程1启动
         *          3. 等待线程1和线程0执行完成后继续执行main线程后续逻辑
         */

        System.out.println(Thread.currentThread().getName() + " 开始执行");
        t1.start();
        t2.start();

        // 如果我们把t2.join(), t1.join()注释后, 那么输出顺序可能就是main线程全部输出来了, 然后在输出线程0和线程1的内容
        t2.join();
        t1.join();

        System.out.println(Thread.currentThread().getName() + " 执行完成");
    }
}
