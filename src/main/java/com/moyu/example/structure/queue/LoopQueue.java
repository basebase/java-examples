package com.moyu.example.structure.queue;

/**
 * Created by Joker on 20/10/5.
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data = null;
    private int front ;
    private int tail ;

    public LoopQueue(int capacity) {
        // 需要多余一个空余的内存空间
        this.data = (E[]) new Object[capacity + 1];
        this.front = 0;
        this.tail = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public boolean isFull() {
        boolean b = (tail + 1) % data.length == front;
        return b;
    }

    public int getCapacity() {
        return this.data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        if (isFull()) {
            resize(this.getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % this.data.length;
    }

    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity + 1];
        int newTail = 0;
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            newData[newTail] = data[i];
            newTail ++;
        }

        this.data = newData;
        this.front = 0;
        this.tail = newTail;
    }

    @Override
    public E dequeue() {

        if (isEmpty())
            throw new IllegalArgumentException("队列为空!");

        E ret = data[front];
        data[front] = null;

        front = (front + 1) % this.data.length;
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("队列为空");
        return data[front];
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            size ++;
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer("LoopQueue: 队列大小为: " + this.data.length + " 队列元素个数为: " + getSize() + " front = " + front + " tail = " + tail + " \nfront [");
        int tmpFront = front;

        while (front != tail) {
            res.append(data[front]);
            if ((front + 1) % data.length != tail)
                res.append(", ");
            front = (front + 1) % data.length;
        }

        res.append("] tail");

        front = tmpFront;
        return res.toString();
    }
}
