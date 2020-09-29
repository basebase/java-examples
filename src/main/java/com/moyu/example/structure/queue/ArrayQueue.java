package com.moyu.example.structure.queue;


import com.moyu.example.structure.array.Array;

/***
 * 数组实现队列
 * @param <E>
 */

public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array = null;

    public ArrayQueue(int capacity) {
        this.array = new Array<>(capacity);
    }

    public ArrayQueue() {
        this.array = new Array<>();
    }

    /***
     * 添加元素到队列中
     * @param e
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 从队列中取出元素
     * @return
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获取队首的数据
     * @return
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }


    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer("Queue: front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i < array.getSize() - 1)
                res.append(", ");
        }

        res.append("] tail");
        return res.toString();
    }
}
