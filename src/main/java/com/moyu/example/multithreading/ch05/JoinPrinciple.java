package com.moyu.example.multithreading.ch05;

/**
 *      描述:     了解join底层原理, 分析join的替代写法
 */
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 执行完成");
        });

        System.out.println(Thread.currentThread().getName() + " 开始执行");
        t1.start();

        /***
         *      可以看到join()方法底层调用的是wait()方法, 并且使用synchronized保护
         *      我们看到, join()方法调用完wait()后, 后面并没有notify/notifyAll方法
         *
         *      ε=(´ο｀*)))唉, 这就回到之前说的问题, 如果使用Thread类作为代码块的锁
         *      当线程结束后会自动唤醒正在等待的线程, 所以这就是join()方法没有显示调用notif/notifyAll方法的原因
         */
//        t1.join();

        /***
         *      通过这段代码就和join形成一种等价的关系, 当线程t1执行完后会唤醒其所有等待的线程
         */
        synchronized (t1) {
            t1.wait();
        }

        System.out.println(Thread.currentThread().getName() + " 执行完成");
    }
}
