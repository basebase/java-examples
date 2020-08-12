package com.moyu.example.multithreading.juc.ch05;

/***
 *      描述:     模拟CAS执行
 */
public class EmulatedCAS {

    private int value = 0;

    public synchronized int getValue() {
        return value;
    }

    /***
     * 此方法就是一个CAS的实现算法, 判断传入的预期值是否和当前的value值一样, 如果一样则更新value并返回上一次内存结果值
     * 这里需要使用synchronized来完成原子和可见性的操作, 否则多个线程执行会出现问题。
     *
     * 我们假设底层是非synchronized实现的即可。
     * @param expectedValue
     * @param newValue
     * @return
     */
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int readValue = value;
        if (expectedValue == readValue) {
            value = newValue;
        }

        return readValue;
    }
}
