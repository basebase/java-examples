package com.moyu.example.multithreading.juc.ch05;

/***
 *
 *      描述:     非CAS累加变量, 基于synchronized
 */

public class Counter1 {

    private int value = 0;

    /***
     *
     * 使用synchronized将导致过多的上下文切换, 性能消耗大。
     */

    public synchronized int getValue() {
        return value;
    }

    public synchronized int increment() {
        return ++ value;
    }
}
