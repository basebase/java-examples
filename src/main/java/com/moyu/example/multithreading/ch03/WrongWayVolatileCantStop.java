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
                t.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

}
