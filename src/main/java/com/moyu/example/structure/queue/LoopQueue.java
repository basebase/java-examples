package com.moyu.example.structure.queue;

/**
 * Created by Joker on 19/9/15.
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        // 在计算循环数组的时候, 我们需要浪费一个空间, 这样在计算tail + 1 == front的时候就可以用到或者说(tail + 1) % capacity == front
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public void enqueue(E e) {

        // 这里的取余计算使用的是data.length, 但是resize方法使用的是getCapacity()方法
        // 区别就是getCapacity里面长度-1, 毕竟我们多开了一个数组空间
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2) ;
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0 ; i < size; i ++) {
            newData[i] = data[(i + front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {

        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);

        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return tail == front;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("Queue size = %d , capacity = %d\n", size, getCapacity()));
        buffer.append("front [");
        for (int i = front ; i != tail; i = (i + 1) % data.length) {
            buffer.append(data[i]);
            if ((i + 1) % data.length != tail) {
                buffer.append(", ");
            }
        }

        buffer.append("] tail");
        return buffer.toString();
    }
}
