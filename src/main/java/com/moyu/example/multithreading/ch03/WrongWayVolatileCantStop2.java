package com.moyu.example.multithreading.ch03;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/***
 *      描述:     使用volatile停止线程, 当遇到阻塞还能停止吗?
 */

public class WrongWayVolatileCantStop2 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue storage = new ArrayBlockingQueue(10);
        Producer p = new Producer(storage);

        Thread producerThread = new Thread(p);
        producerThread.start();

        Thread.sleep(3000);
        Consumer c = new Consumer(storage);
        while (c.needMoreNums()) {
            System.out.println(c.storage.take() + "被消费了 !");
            Thread.sleep(100);
        }

        System.out.println("消费完成...");
        // 当消费完后, 我们更新生产者的状态, 让其停止线程执行
        p.canceled = true;

        System.out.println("状态: " + p.canceled);
        System.out.println("当前生产者量级: " + p.storage.size());
    }
}


class Producer implements Runnable {

    public volatile boolean canceled = false;
    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        try {

            int num = 0;
            while (num < 100000 && !canceled) {
                System.out.println("当前值: " + num + " 放入队列中了!");

                /***
                 *      当队列满了之后, 线程就会再这里被挂起, 如果没有被唤醒, 那么就算我们的canceled变量更新
                 *      当前的while判断也是读取不到的, 从而无法终止当前的循环条件, 而是会一直阻塞再此。
                 */
                storage.put(num); // 当队列满了, 线程在这里就会被挂起
                Thread.sleep(10);
                num ++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行");
        }
    }
}

class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95)
            return false;
        return true;
    }
}