package com.moyu.example.structure.queue;

import com.moyu.example.structure.array.Array;

/**
 * Created by Joker on 19/9/14.
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity) {
        this.array = new Array<>(capacity);
    }

    public ArrayQueue() {
        this.array = new Array<>();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("Queue: front [");
        for (int i = 0; i < array.getSize(); i ++) {
            buff.append(array.get(i));
            if (i < array.getSize() - 1)
                buff.append(",");
        }

        buff.append("] tail");
        return buff.toString();
    }
}
