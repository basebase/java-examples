package com.moyu.example.structure.queue;

/**
 * Created by Joker on 19/9/14.
 */
public interface Queue<E> {
    // 入队
    void enqueue(E e);
    // 出队
    E dequeue();
    // 获取队首
    E getFront();
    int getSize();
    boolean isEmpty();
}
