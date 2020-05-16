package com.moyu.example.multithreading.ch05;

/***
 *      描述:     证明wait()只释放当前的锁, 锁之间相互独立。
 */

public class WaitNotifyReleaseOwnMonitor {


    /***
     *     该测试类有两个线程A和B, 然后线程A优先获取到resourceA和resourceB, 并释放resourceA锁
     *     同时线程B获取到resourceA的锁, 但是却无法获取resourceB的锁, 证明每个锁都是独立的。
     */
    private static class WaitNotifyReleaseOwnMonitorTest1 {

        private static Object resourceA = new Object();
        private static Object resourceB = new Object();

        public void run() {
            Thread threadA = new Thread(() -> {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName() + " 获取到resourceA锁");
                    try {
                        // 模拟执行任务
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread().getName() + " 获取到resourceB锁");
                        try {
                            // 释放resourceA
                            System.out.println(Thread.currentThread().getName() + " 释放resourceA锁");
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });


            Thread threadB = new Thread(() -> {

                try {
                    // 等待线程A释放锁
                    Thread.sleep(500);

                    synchronized (resourceA) {
                        System.out.println(Thread.currentThread().getName() + " 获取到resourceA锁");

                        System.out.println(Thread.currentThread().getName() + " 尝试获取resourceB锁");
                        synchronized (resourceB) {
                            System.out.println(Thread.currentThread().getName() + " 获取到resourceB锁");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


            threadA.start();
            threadB.start();
        }

    }


    /***
     *      该类利用三个线程A,B,C进行测试, 线程A获取resourceA锁并将锁释放进入阻塞, 线程B获取resourceB锁并将锁释放进入阻塞,
     *      然后线程C证明两把锁是独立的, 毕竟resourceA锁和resourceB锁, 它们所持有的线程都不一样。
     *
     *      事实证明, 如果在resourceA锁上调用notifyAll()方法, resourceB锁上的线程不会被其唤醒。程序还是在等待状态。反之亦然。
     *      除非, resourceA和resourceB两把锁都调用notifyAll()才可将其上的线程都唤醒。
     */
    private static class WaitNotifyReleaseOwnMonitorTest2 {
        private static Object resourceA = new Object();
        private static Object resourceB = new Object();

        public void run() {

            Thread threadA = new Thread(() -> {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName() + " 获取到resourceA锁");
                    try {
                        // 模拟任务执行
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 释放resourceA锁");
                    try {
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 结束任务.");
                }
            });


            Thread threadB = new Thread(() -> {
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread().getName() + " 获取到resourceB锁");
                    try {
                        // 模拟任务执行
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 释放resourceB锁");
                    try {
                        resourceB.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 结束任务.");
                }
            });

            Thread threadC = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 *      为了证明锁之间是相互独立的, 这里我们用锁resourceA唤醒线程, 看看线程B是否也会将其唤醒
                 */
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName() + " 唤醒resourceA锁上的线程");
                    resourceA.notifyAll();
                }

//                synchronized (resourceB) {
//                    System.out.println(Thread.currentThread().getName() + " 唤醒resourceB锁上的线程");
//                    resourceB.notifyAll();
//                }

                /***
                 * 使用this相当于又是一把锁, 如果在this对象中直接调用notifyAll()方法, 则一个线程都不会被唤醒
                 * 毕竟this锁中没有一个线程进入了等待状态
                 */
//                synchronized (this) {
//                    notifyAll();
//                }
            });

            threadA.start();
            threadB.start();
            threadC.start();

        }
    }



    public static void main(String[] args) {
//        WaitNotifyReleaseOwnMonitorTest1 waitNotifyReleaseOwnMonitorTest1 =
//                new WaitNotifyReleaseOwnMonitorTest1();
//        waitNotifyReleaseOwnMonitorTest1.run();

        WaitNotifyReleaseOwnMonitorTest2 waitNotifyReleaseOwnMonitorTest2 =
                new WaitNotifyReleaseOwnMonitorTest2();
        waitNotifyReleaseOwnMonitorTest2.run();
    }
}
