package com.moyu.example.structure.stack;

/**
 * 栈的接口
 * @param <E>
 */
public interface Stack<E> {
    void push(E e);
    E pop();
    E peek();
    boolean isEmpty();
    int getSize();
}
