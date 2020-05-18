package com.moyu.example.multithreading.ch05;

import java.util.LinkedList;

/***
 *      描述:         用wait/notify实现生产消费模型
 *                   对比ProducerConsumerModel2类, 观察一下其输出结果, 有什么问题?
 */
public class ProducerConsumerModel {

    private Object obj = new Object();
    // 生产者写入此数据结构中, 消费者读取此数据结构中数据
    private LinkedList<Integer> storage = new LinkedList<>();
    private static final Integer CONTAIN = 100;
    private static final Integer MAX_SIZE = 10;


    /***
     *            生产者线程任务:
     *                 生产者任务上限是10条数据, 当超过后就会进入阻塞状态, 需要其它线程将其唤醒。
     */
    public Runnable producerTask() {
        return () -> {
            for (int i = 0; i < CONTAIN; i ++) {
                synchronized (obj) {
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


    /***
     *            消费者线程任务:
     *                 消费者消费数据, 如果当前队列数据为空则进入阻塞, 需要其它线程将其唤醒
     */
    public Runnable consumerTask() {
        return () -> {
            for (int i = 0; i < CONTAIN; i ++) {
                synchronized (obj) {
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
        ProducerConsumerModel producerConsumerModel2 = new ProducerConsumerModel();
        Thread producerThread = new Thread(producerConsumerModel2.producerTask(), "producer");
        Thread consumerThread = new Thread(producerConsumerModel2.consumerTask(), "consumer");

        producerThread.start();
        consumerThread.start();

    }
}
