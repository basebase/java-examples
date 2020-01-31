package com.moyu.example.structure.stack;

/**
 * Created by Joker on 19/9/14.
 */
public interface Stack<E> {
    // 获取栈的长度
    int getSize();
    // 判断栈是否为空
    boolean isEmpty();
    // 数据压栈(add)
    void push(E e);
    // 数据弹栈(remove)
    E pop();
    // 获取栈顶元素
    E peek();
}
