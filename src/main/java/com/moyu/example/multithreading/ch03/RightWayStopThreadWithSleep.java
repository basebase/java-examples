package com.moyu.example.multithreading.ch03;

/***
 *      描述：     run方法带有sleep的中断线程的写法
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
          try {
              int num = 0;
              while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                  if (num % 100 == 0)
                      System.out.println(num + "是100的倍数");
                  num ++;
              }
              Thread.sleep(2000);
              System.out.println(Thread.currentThread().getName() + " 线程结束");
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
        };

        Thread t1 = new Thread(runnable);
        t1.start();

        // 等待1s让线程t1优先执行
        Thread.sleep(1000);
        // t1线程休眠2s, main线程执行中断线程, 但是t1处于阻塞状态
        // 那么t1退出阻塞状态并抛出一个java.lang.InterruptedException异常
        t1.interrupt();

        System.out.println(Thread.currentThread().getName() + " 线程结束");
    }
}
