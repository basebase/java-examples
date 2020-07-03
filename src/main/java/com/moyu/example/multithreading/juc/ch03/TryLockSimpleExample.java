package com.moyu.example.multithreading.juc.ch03;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *      描述:     tryLock()方法避免死锁的发生, 对比例子LockSimpleExample2.java
 *               本例中解决死锁的问题, tryLock()之所以解决了, 正是因为其获取不到锁不会无休止的等待, 而是会退出等待
 */
public class TryLockSimpleExample {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {


        /****
         *      使用tryLock()方法也有一套模板代码, 在if获取到锁后, 里面的代码必须使用try/finally保证获取到的锁能被释放掉。
         *      没有获取到锁的话else中执行想要的业务代码即可。
         *          if (lock.tryLock() || lock.tryLock(timeout, unit)) {
         *              try {
         *
         *                  // ...
         *              } finally {
         *                  lock.unlock()
         *              }
         *          } else {
         *              // ...
         *          }
         */


        /****
         *
         *     使用tryLock()避免出现死锁, 线程A获取lock1而B线程获取lock2,
         *     当线程A尝试获取lock2发现被B线程持有, 此时返回false并输出线程A获取lock2失败, 等待重新获取, 并且释放lock1。
         *
         *     此时线程B尝试获取lock1发现线程A已经释放了lock1, 输出相关信息退出循环体, 并将lock1和lock2全部释放。
         *     线程A再次执行顺利的获取到lock1和lock2两把锁, 打印出先关信息, 退出循环体, 并将lock1和lock2全部释放。
         */

        /***
         *
         *      如果此例子使用lock()来实现, 则会出现死锁, 当线程A和线程B分别获取到lock1和lock2,
         *      线程A想获取lock2则会一直等待线程B释放lock2, 线程B想要获取lock1则会一直等待线程A释放lock1,
         *      这就会形成相互等待一直阻塞下去...
         *
         *      使用tryLock()恰好就解决了此类问题, 获取不到锁在指定时间内就退出, 毫不犹豫。
         */

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) {

                        if (lock1.tryLock(1, TimeUnit.SECONDS)) {

                            try {

                                System.out.println(Thread.currentThread().getName() + " 获取到lock1");
                                Thread.sleep(new Random().nextInt(1000));

                                /***
                                 *
                                 *     尝试获取lock2, 在指定时间内没有获取到lock2则退出并执行else语句,
                                 *     此时, 程序会执行lock1的finally方法释放lock1的锁, 以便其它线程获取lock1。
                                 *
                                 *     记住, 只有获取到lock在使用unlock()方法, 所以我们的else方法中都没有unlock()方法
                                 */

                                if (lock2.tryLock(1, TimeUnit.SECONDS)) {

                                    try {
                                        System.out.println(Thread.currentThread().getName() + " 获取到lock2");
                                        break;
                                    } finally {
                                        lock2.unlock();
                                        System.out.println(Thread.currentThread().getName() + " 释放lock2");
                                    }
                                } else {
                                    System.out.println(Thread.currentThread().getName() + " 获取lock2失败, 重新获取");
                                }
                            } finally {
                                lock1.unlock();
                                System.out.println(Thread.currentThread().getName() + " 释放lock1");
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName() + " 获取lock1失败, 重新获取");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread-A").start();

        new Thread(() -> {
            try {
                while (true) {

                    if (lock2.tryLock(1, TimeUnit.SECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " 获取到lock2");
                            Thread.sleep(new Random().nextInt(1000));

                            /***
                             *      尝试获取lock1, 如果在指定时间内没有获取到lock1则退出, 执行else,
                             *      此时, 程序会执行lock2的finally方法释放lock2的锁, 以便其它线程获取并执行。
                             *
                             *      记住, 只有获取到lock在使用unlock()方法, 所以我们的else方法中都没有unlock()方法
                             */

                            if (lock1.tryLock(1, TimeUnit.SECONDS)) {

                                try {
                                    System.out.println(Thread.currentThread().getName() + " 获取到lock1");
                                    break;
                                } finally {
                                    lock1.unlock();
                                    System.out.println(Thread.currentThread().getName() + " 释放lock1");
                                }
                            } else {
                                System.out.println(Thread.currentThread().getName() + " 获取lock1失败, 重新获取");
                            }
                        } finally {
                            lock2.unlock();
                            System.out.println(Thread.currentThread().getName() + " 释放lock2");
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 获取lock2失败, 重新获取");
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-B").start();
    }
}
