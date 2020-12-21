package com.moyu.example.structure.bst;

public class Main {
    public static void main(String[] args) {
        Integer[] nums = {50, 18, 20, 88, 77, 100};
        BST<Integer> bst = new BST<>();
        for (Integer num : nums) {
            bst.add(num);
        }

        System.out.println(bst.toString());

        System.out.println("======================================");


        System.out.println(bst.find(100));
        System.out.println(bst.find(1));

        System.out.println("二分搜索树最小值: " + bst.findMin() + " 二分搜索树最大值: " + bst.findMax());
    }
}
