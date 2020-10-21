package com.moyu.example.structure.stack;


public class LinkedListStackMain {
    public static void main(String[] args) {
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        for (int i = 0; i < 10; i++) {
            linkedListStack.push(i);
            System.out.println(linkedListStack);
        }

        System.out.println("==============pop==============");

        for (int i = 0; i < 10; i++) {
            linkedListStack.pop();
            System.out.println(linkedListStack);
        }

    }
}
