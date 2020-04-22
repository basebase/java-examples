package com.moyu.example.multithreading.ch03;

/***
 *      描述:     run方法内没有sleep或wait方法时停止线程。
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 0;
        // 在不加入Thread.currentThread().isInterrupted()判断和没事人一样。
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0)
                System.out.println(num + "是10000的倍数");
            num ++;
        }

        System.out.println("任务运行结束...");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new RightWayStopThreadWithoutSleep());
        t1.start();

        // 用来等待1s后再进行中断
        Thread.sleep(1000);
        // main线程中断t1线程
        t1.interrupt();
    }
}
