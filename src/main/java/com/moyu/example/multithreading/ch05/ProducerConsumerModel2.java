package com.moyu.example.multithreading.ch05;

import java.util.LinkedList;

/***
 *      描述:         用wait/notify实现生产消费模型
 */
public class ProducerConsumerModel2 {

    private Object obj = new Object();
    // 生产者写入此数据结构中, 消费者读取此数据结构中数据
    private LinkedList<Integer> storage = new LinkedList<>();
    private static final Integer CONTAIN = 100;
    private static final Integer MAX_SIZE = 10;


    public Runnable producerTask() {
        return () -> {
            synchronized (obj) {
                for (int i = 0; i < CONTAIN; i ++) {
                    try {
                        // 如果队列已满, 进入阻塞
                        if (storage.size() == MAX_SIZE)
                            obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    storage.add(i);
                    System.out.println(Thread.currentThread().getName() + " 生产数据 : " + i + " 加入队列队列大小 : " + storage.size());
                    // 队列已经有数据了, 唤醒消费者消费数据
                    obj.notify();
                }
            }
        };
    }



    public Runnable consumerTask() {
        return () -> {
            synchronized (obj) {
                for (int i = 0; i < CONTAIN; i ++) {
                    try {
                        // 如果没有数据可以消费, 则进入阻塞等待生产者将其唤醒
                        if (storage.size() == 0)
                            obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 消费数据 : " + storage.poll() + " 队列还剩下 " + storage.size() + " 元素没有被消费");
                    // 队列现在有空闲, 唤醒生产者生产数据
                    obj.notify();
                }
            }
        };
    }

    public static void main(String[] args) {

        ProducerConsumerModel2 producerConsumerModel2 = new ProducerConsumerModel2();

        Thread producerThread = new Thread(producerConsumerModel2.producerTask(), "producer");
        Thread consumerThread = new Thread(producerConsumerModel2.consumerTask(), "consumer");

        producerThread.start();
        consumerThread.start();

    }



}
