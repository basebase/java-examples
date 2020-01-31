package com.moyu.example.structure.stack;

import com.moyu.example.structure.array.Array;

/**
 * Created by Joker on 19/9/14.
 * 利用数组来实现栈的功能
 */
public class ArrayStack<E> implements Stack<E> {

    Array<E> array ;


    public ArrayStack(int capacity) {
        this.array = new Array<>(capacity);
    }

    public ArrayStack() {
        this.array = new Array<>();
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
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("Stack: [");
        for (int i = 0; i < array.getSize(); i ++) {
            buff.append(array.get(i));
            if (i < array.getSize() - 1)
                buff.append(",");
        }

        buff.append("] top");
        return buff.toString();
    }
}
