package com.moyu.example.structure.queue;

public class CircularQueue<E> implements Queue<E> {

    private E[] data = null;
    private int front;
    private int tail;
    private int size;

    public CircularQueue(int capacity) {
        // 这里需要加1一个多余的空间进行判断
        this.data = (E[]) new Object[capacity + 1];
        this.front = 0;
        this.tail = 0;
    }

    public CircularQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1; // 真实的数组容量
    }

    public boolean isFull() {
        return (tail + 1) % data.length == front;
    }

    @Override
    public void enqueue(E e) {
        if (isFull())
            resize(getCapacity() * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity + 1];
        for (int i = 0; i < size; i++) {
            // size总共有多少个元素
            // 但是0不一定是起始位置, front才是起始位置, 所以需要对数组取余计算
            newData[i] = data[(i + front) % data.length];
        }

        // 从新复位
        this.front = 0;
        this.tail = size;
        this.data = newData;
    }

    @Override
    public E dequeue() {

        if (isEmpty())
            throw new IllegalArgumentException("队列为空...");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        return ret;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("数组元素个数为: %d 数组容量为: %d \n", size, data.length));
        buffer.append("front [");
        // front开始遍历, front是首节点
        // (i + 1) % data.length 算出下一个位置
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            buffer.append(data[i]);
            if ((i + 1) % data.length != tail)
                buffer.append(", ");
        }

        buffer.append("] tail");
        return buffer.toString();
    }
}
