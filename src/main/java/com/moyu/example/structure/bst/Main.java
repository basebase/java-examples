package com.moyu.example.structure.bst;

public class Main {
    public static void main(String[] args) {
        Integer[] nums = {5, 3, 6, 2, 4, 8};
        BST<Integer> bst = new BST<>();
        for (Integer num : nums) {
            bst.add(num);
        }

        System.out.println(bst.toString());

        System.out.println("======================================");


        System.out.println(bst.find(100));
        System.out.println(bst.find(2));

        System.out.println(bst.nonRecursiveFind(20));
        System.out.println(bst.nonRecursiveFind(8));

        System.out.println("二分搜索树最小值: " + bst.findMin() + " 二分搜索树最大值: " + bst.findMax());

        System.out.println();
        bst.preOrder();
    }
}