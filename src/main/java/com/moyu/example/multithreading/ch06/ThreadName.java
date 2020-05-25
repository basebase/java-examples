package com.moyu.example.multithreading.ch06;

/***
 *      描述:     线程名称, 如果不给定一个名称后, 默认会用Thread-(ID)设置名称, ID从0开始
 */
public class ThreadName {
    public static void main(String[] args) throws InterruptedException {

        /***
         *      可以看到, 在初始化的时候如果我们不设置名称, 会默认写入"Thread-" + nextThreadNum()
         *      来看看nextThreadNum()方法
         *      private static synchronized int nextThreadNum() {
                    return threadInitNumber++;
                }
         */

        for (int i = 0 ; i < 10; i ++) {
            Thread thread = new Thread();
            thread.start();
            System.out.println(thread.getName());
        }

        Thread loveThread = new Thread();
        System.out.println(loveThread.getName());

        /***
         *      可以在创建线程的时候不给定一个线程名称, 通过调用setName()方法设置线程名称
         *      setName()方法有一个name还有一个setNativeName()方法, 我们先来说说name就是我们Java线程的名称
         *      即使Java的线程启动了, 我们依然可以修改线程的名称。
         *
         *      而想要设置NativeName, 只能在线程还没有start之前设置, setNativeName是一个本地方法
         *
         *      this.name = name;
         *      if (threadStatus != 0) {
                    setNativeName(name);
                }
         */
        loveThread.setName("love");
        loveThread.start();
        System.out.println(loveThread.getName());
    }
}
