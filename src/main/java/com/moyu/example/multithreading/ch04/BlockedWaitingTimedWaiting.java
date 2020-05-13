package com.moyu.example.multithreading.ch04;

/***
 *      展示:     展示 Blocked, Waiting, TimedWaiting三种状态
 */
public class BlockedWaitingTimedWaiting {

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting blockedWaitingTimedWaiting = new BlockedWaitingTimedWaiting();
        Runnable runnable = blockedWaitingTimedWaiting.getRunnable();


        /***
         *      需要创建两个线程, 否则无法模拟出BLOCKED的状态, 毕竟只有一个线程的话永远都能获取到锁
         *      两个线程始终有一个线程需要等待获取到锁从而进入BLOCKED状态。
         */

        Thread t1 = new Thread(runnable);
        t1.start();

        Thread t2 = new Thread(runnable);
        t2.start();

        // 当线程t1执行时, 进入休眠状态, 但是有时间限定, 所以, 此时输出线程t1的状态是TIMED_WAITING
        System.out.println(t1.getState());
        // 而线程t2没有获取到锁(此时锁还在线程t1上面), 所以会进入BLOCKED状态
        System.out.println(t2.getState());

        Thread.sleep(3000);
        // 当我们执行wait()方法时, 线程就会进入WAITING状态, 等待被唤醒。
        System.out.println(t1.getState());
    }

    public Runnable getRunnable() {
        return () -> {

            synchronized (this) {
                try {

                    Thread.sleep(2000);

                    // 在等待睡眠结束时, 我们调用wait()方法, 线程会进入WAITING状态
                    wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
