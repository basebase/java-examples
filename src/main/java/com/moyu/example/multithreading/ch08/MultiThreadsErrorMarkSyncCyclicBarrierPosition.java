package com.moyu.example.multithreading.ch08;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/***
 *      描述:     多个线程运行结果出错, 运行后的结果会小于预期结果, 找出问题并解决
 */
public class MultiThreadsErrorMarkSyncCyclicBarrierPosition {

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
            marked[0] = true;
            for (int i = 0; i < 10000; i++) {


                try {
                    cyclicBarrier2.reset();
                    cyclicBarrier1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                value ++;


                try {
                    cyclicBarrier1.reset();
                    cyclicBarrier2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                realCount.incrementAndGet();

                synchronized (lock) {

                    /***
                     *  上面就算加入了各种等待条件, 但是由于两个线程都统一的完成累加后, 两个线程看到的值都是一样的
                     *  所以, 我们在判断条件的时候需要判断前一位是否为true
                     *
                     *  为什么这么判断呢?
                     *      思考一下, 如果value是正常累加的话, 肯定每次都加2, 所以上一个标记为为false
                     *      但是, 如果value累加异常后, 就会发生碰撞。
                     *
                     *      但是, 有一个点需要注意的就是, 第一个位置(数组0的位置)需要默认为true
                     *      为什么呢? 如果value累加异常, 就会为1, 那么value-1就为0,发现为true
                     *      就表示value累加异常, 否则就会在下标2的位置设置为true。
                     *
                     *      如果是正常的话, true标签依次是: 2, 4, 6, 8, 10...
                     */

                    if (marked[value] && marked[value - 1]) {
                        System.out.println("发生了错误: " + value);
                        wrongCount.incrementAndGet();
                    }
                    marked[value] = true;
                }
            }
        };
    }
}
