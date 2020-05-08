package com.moyu.example.multithreading.ch03;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/***
 *      描述:     解决线程阻塞无法终止线程问题
 */

public class WrongWayVolatileFixed {

    public static void main(String[] args) throws InterruptedException {

        WrongWayVolatileFixed body = new WrongWayVolatileFixed();

        BlockingQueue storage = new ArrayBlockingQueue(10);
        Producer p = body.new Producer(storage);

        Thread producerThread = new Thread(p);
        producerThread.start();

        Thread.sleep(3000);
        Consumer c = body.new Consumer(storage);
        while (c.needMoreNums()) {
            System.out.println(c.storage.take() + "被消费了 !");
            Thread.sleep(100);
        }

        System.out.println("消费完成...");
//        p.canceled = true;
        producerThread.interrupt();

        System.out.println("当前生产者量级: " + p.storage.size());
    }



    class Producer implements Runnable {

        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            try {

                int num = 0;
                while (num < 100000 && !Thread.currentThread().isInterrupted()) {
                    System.out.println("当前值: " + num + " 放入队列中了!");
                    storage.put(num);
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
}


