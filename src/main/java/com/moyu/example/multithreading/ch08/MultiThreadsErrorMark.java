package com.moyu.example.multithreading.ch08;

import java.util.concurrent.atomic.AtomicInteger;

/***
 *      描述:     多个线程运行结果出错, 运行后的结果会小于预期结果, 找出问题并解决
 */
public class MultiThreadsErrorMark {

    static int value = 0;
    static final boolean[] marked = new boolean[20000];

    static AtomicInteger realCount = new AtomicInteger(0);
    static AtomicInteger wrongCount = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Runnable r = task();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

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



                /***
                 *      既然数据是在这里消失的, 那么每次累加完后就去检查value的值
                 *      从而确定是在哪里消失的。
                 */
                value ++;
                // 真实累加次数
                realCount.incrementAndGet();

                /***
                 *      具体如何实现呢, 我们利用一个数组存入每次累加的值, 如果不存在设置为true
                 *      否则我们打印出错误信息
                 */

                // 如果该位置已经被标记过了, 则发生了数据错误。
                if (marked[value]) {
                    System.out.println("发生了错误: " + value);
                    wrongCount.incrementAndGet(); // 出错次数
                }

                /**
                 *  当线程1累加完value后当前的value就设置为true, 如果线程2读取并累加的值和线程1一样
                 *  那么, 就出现问题
                 */
                marked[value] = true;
            }
        };
    }
}
