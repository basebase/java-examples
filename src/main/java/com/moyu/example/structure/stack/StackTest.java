package com.moyu.example.structure.stack;

/**
 * Created by Joker on 19/9/14.
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i ++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);

        Integer peek = stack.peek();
        System.out.println("peek = " + peek);
        System.out.println(stack);


    }
}
