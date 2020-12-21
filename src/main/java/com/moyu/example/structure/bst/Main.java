package com.moyu.example.structure.bst;

public class Main {
    public static void main(String[] args) {
        Integer[] nums = {50, 18, 20, 88, 77, 100};
        BST<Integer> bst = new BST<>();
        for (Integer num : nums) {
            bst.add(num);
        }

        System.out.println(bst.toString());
    }
}
