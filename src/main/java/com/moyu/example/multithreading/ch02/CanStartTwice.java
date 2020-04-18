package com.moyu.example.multithreading.ch02;

/***
 *      描述:     调用两次start()方法会有什么问题
 */
public class CanStartTwice {
    public static void main(String[] args) {
        Thread t = new Thread();
        t.start();
        t.start();
    }
}
