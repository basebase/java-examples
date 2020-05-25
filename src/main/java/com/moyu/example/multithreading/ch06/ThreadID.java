package com.moyu.example.multithreading.ch06;

/***
 *      描述:     线程ID从1开始, 但是JVM运行起来后, 我们自己创建的线程ID早已不是2
 */

public class ThreadID {
    public static void main(String[] args) {
        Thread thread = new Thread();


        /***
         *      private static synchronized long nextThreadID() {
                    return ++threadSeqNumber;
                }

                这个是线程id的逻辑, threadSeqNumber初始化为0, 但是由于++在前面
                所以是先加1然后在返回, 所以当我们的mian线程启动运行ID值为1
         */
        System.out.println("线程 " + Thread.currentThread().getName() + " ID: "
                + Thread.currentThread().getId());


        /***
         *     线程id既然是自增的, 那么我们自己创建的子线程为什么不是2呢? 而是其它的数值了呢?
         *     当我们debug程序(进入idea的threads界面, 选中main线程右键选中Export Threads), 会发现在启动子线程之前JVM就已经启动了其它线程了
         *          * Finalizer@
         *          * Reference Handler@
         *          * Signal Dispatcher@
         *     我们会发现这些线程后面都有一个@符号, 其实就是对应的ID, 我们也可以看到main@1信息
         *     所以, 这就是我们的子线程为什么后续的线程ID不是2的原因。
         *
         */
        System.out.println("线程 " + thread.getName() + " ID: "
                + thread.getId());
    }
}
