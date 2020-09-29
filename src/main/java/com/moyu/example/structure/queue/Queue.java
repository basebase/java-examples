package com.moyu.example.structure.queue;

/**
 * 队列接口
 * @param <E>
 */
public interface Queue<E> {
    void enqueue(E e);  // 入队
    E dequeue();    // 出队
    E getFront();   // 获取队首元素
    boolean isEmpty();
    int getSize();
}
