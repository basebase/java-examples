package com.moyu.example.multithreading.ch03;

/***
 *      描述：     while体内加入try/catch, 会导致中断失效
 */
public class CanInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(getRunnable());
        t1.start();
        Thread.sleep(5000);
        t1.interrupt();
    }

    public static Runnable getRunnable() {
        return () -> {
            int num = 0;
            while (num < 100000) {
                System.out.println("当前值为: " + num);
                try {
                    Thread.sleep(300);
//                    System.out.println("当前值为: " + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                num ++;
            }
        };
    }
}
