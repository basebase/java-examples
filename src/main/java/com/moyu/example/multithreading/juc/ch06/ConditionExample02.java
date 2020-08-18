package com.moyu.example.multithreading.juc.ch06;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *      描述:     Condition生产者消费者模式
 */
public class ConditionExample02 {

    private Lock lock = new ReentrantLock();
    Condition p = lock.newCondition();

    private int size = 10;
    LinkedList<Integer> queue = new LinkedList<>();


    public Runnable producer() {
        return () -> {

            /***
             *     如果是:
             *        lock.lock();
             *        try {
             *            for(int i = 0; i < 100; i ++) {
             *                // ...
             *
             *                p.signal()
             *            }
             *        } finally {
             *            lock.unlock();
             *        }
             *
             *        就算是被唤醒了, 但是由于lock是在最外层, 被唤醒的线程无法获取到lock, 只有等到线程调用await()方法,
             *        所以无法展示交叉的效果, 而是生产完了之后才能进行消费, 消费完了之后才能生产
             */

            for (int i = 0; i < 100; i++) {

                lock.lock();

                try {
                    if (queue.size() == size) {     // 队列满了之后, 线程进入阻塞
                        System.out.println(Thread.currentThread().getName() + " 生产者队列已满, 进入等待...");
                        p.await();
                    }
                    queue.add(i);
                    System.out.println(Thread.currentThread().getName() + " 生产剩余容量为: " + (size - queue.size()));
                    p.signal();     // 每次生产完数据后, 唤醒消费线程进行消费
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
    }

    public Runnable consumer() {
        return () -> {

            for (int i = 0; i < 100; i++) {
                lock.lock();

                try {
                    if (queue.size() == 0) {        // 队列全部被消费后, 线程进入阻塞
                        System.out.println(Thread.currentThread().getName() + " 队列空啦, 进入阻塞");
                        p.await();
                    }

                    Integer v = queue.poll();
                    System.out.println(Thread.currentThread().getName() + " 消费数据为: " + v +
                            " 队列大小为: " + queue.size());

                    p.signal();     // 每次消费完后, 都唤醒生产线程可以进行生产了
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
    }

    public static void main(String[] args) {
        ConditionExample02 conditionExample02 = new ConditionExample02();
        new Thread(conditionExample02.producer(), "Producer").start();
        new Thread(conditionExample02.consumer(), "Consumer").start();
    }
}
