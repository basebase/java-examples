package com.moyu.example.multithreading.ch03;

/***
 *      描述:     使用volatile停止线程, 当遇到阻塞还能停止吗?
 */
public class WrongWayVolatileCantStop {

    private volatile boolean canceled = false;

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatileCantStop wrongWayVolatileCantStop = new WrongWayVolatileCantStop();

        Thread t1 = new Thread(wrongWayVolatileCantStop.createThreadN1());
        t1.start();

        Thread.sleep(4000);

        Thread t2 = new Thread(wrongWayVolatileCantStop.createThreadN2(t1));
        t2.start();

        Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName() + " 线程canceled状态: " + wrongWayVolatileCantStop.canceled);
    }


    public Runnable createThreadN1() {
        return () -> {
            try {

                /**
                 *     该程序主要模拟WrongWayVolatileCantStop2的阻塞情况, 阻塞Integer的最大值时间
                 *     理论上这里是可以被终止的, 只需要等待睡眠时间过期, 读取到更新后的volatile变量值即可
                 *     但是, 如果是阻塞队列, 我们的队列是满的没有被消费就一直挂起, 没有失效时间。
                 */
                while ( /* !canceled */ !Thread.currentThread().isInterrupted()/**/  ) {
                    System.out.println("数据处理完成...");
                    System.out.println("开始更新状态...");
                    Thread.sleep(Integer.MAX_VALUE);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("当前线程: " + Thread.currentThread().getName() + " 终止! canceled状态 : " + canceled);
            }
        };
    }


    public Runnable createThreadN2(Thread t) {
        return () -> {
            try {
                Thread.sleep(3000);
                canceled = true;
                // 中断线程
                t.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

}
