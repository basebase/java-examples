package com.moyu.example.structure.stack;

public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();

//        System.out.println("peek: " + stack.peek());
//        System.out.println(stack);

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        System.out.println("peek: " + stack.peek());
        System.out.println(stack);
    }
}
