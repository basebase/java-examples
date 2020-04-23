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
            // ①    加入线程中断状态也无效, 已经被sleep清空了状态
            while (num < 100000 /* && !Thread.currentThread().isInterrupted()*/) {
                System.out.println("当前值为: " + num);
                try {
                    Thread.sleep(300);
//                    System.out.println("当前值为: " + num);   ②   不会输出
                } catch (InterruptedException e) {
                    e.printStackTrace();

//                    int i = 1 / 0;                           ③   抛出异常后终止
//                    return ;                                 ④   退出
                }

                num ++;
            }
        };
    }
}
