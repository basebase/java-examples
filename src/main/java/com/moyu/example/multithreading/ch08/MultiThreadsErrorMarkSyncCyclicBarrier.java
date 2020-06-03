package com.moyu.example.multithreading.ch08;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/***
 *      描述:     多个线程运行结果出错, 运行后的结果会小于预期结果, 找出问题并解决
 */
public class MultiThreadsErrorMarkSyncCyclicBarrier {

    static int value = 0;
    static final boolean[] marked = new boolean[30000];

    static AtomicInteger realCount = new AtomicInteger(0);
    static AtomicInteger wrongCount = new AtomicInteger(0);

    // 所有线程都等待完成后才会继续下一步行动, 这里设置为等待2个线程
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = task();
        Thread t1 = new Thread(r, "Thread-A");
        Thread t2 = new Thread(r, "Thread-B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("最终的结果为: " + value);
        System.out.println("真正运行的次数: " + realCount.get());
        System.out.println("错误次数: " + wrongCount.get());

    }

    public static Runnable task() {
        return () -> {
            for (int i = 0; i < 10000; i++) {


                try {

                    /***
                     *      等待两个线程都进入之后, 同时执行下面的代码, 模拟同时执行可能会造成数据异常
                     */
                    cyclicBarrier2.reset();
                    cyclicBarrier1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                value ++;


                /***
                 *      刚才使用sync就是因为value++的问题, 线程1在执行sync的时候, 线程2进行了value++
                 *      导致value的值变大了。所以, 这里我们也统一等待两个线程执行完value++后再进行判断
                 */

                try {
                    cyclicBarrier1.reset();
                    cyclicBarrier2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                realCount.incrementAndGet();


                /***
                 *      但是使用CyclicBarrier之后也没有和我们预想的一样啊?
                 *          思考一下:
                 *              我们等待两个线程同时进入for循环, 并且同时要求两个线程都进行value++后
                 *              才能进行sync的判断, 这是不是就导致value的值始终都是一致的, 我们都要等待
                 *              两个线程执行完value++后才执行后面的代码块, 而代码块中由于sync的可见性
                 *              就会出现异常信息的打印, 但实际上不是一个异常
                 */


                synchronized (lock) {
                    if (marked[value]) {
                        System.out.println("发生了错误: " + value);
                        wrongCount.incrementAndGet();
                    }
                    marked[value] = true;
                }
            }
        };
    }
}
