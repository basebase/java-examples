package com.moyu.example.multithreading.ch03;


/***
 *      描述:     演示使用volatile用来停止线程,
 */
public class WrongWayVolatile {

    private volatile boolean canceled = false;

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Runnable runnable = wrongWayVolatile.getRunnable();
        Thread t1 = new Thread(runnable);

        t1.start();
        Thread.sleep(5000);
        wrongWayVolatile.canceled = true;
    }

    public Runnable getRunnable() {
        return () -> {
            try {
                int num = 0;
                while (num < 100000 && !canceled) {
                    System.out.println("当前的值为: " + num);
                    Thread.sleep(10);
                    num ++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
