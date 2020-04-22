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

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();

        System.out.println(Thread.currentThread().getName() + " 线程结束");
    }
}
