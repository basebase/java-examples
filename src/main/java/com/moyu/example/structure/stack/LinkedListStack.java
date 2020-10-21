package com.moyu.example.structure.stack;

import com.moyu.example.structure.linkedlist.LinkedList;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        this.linkedList = new LinkedList<>();
    }

    public LinkedListStack(LinkedList<E> linkedList) {
        this.linkedList = linkedList;
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer("Stack Top: ");
        res.append(linkedList);
        return res.toString();
    }
}
