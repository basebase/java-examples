package com.moyu.example.structure.bst;

public class Main {
    public static void main(String[] args) {
        Integer[] nums = {70,60,80,50,65,75,85,40,45,68,90,88,95};
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

        System.out.println("=================递归前序遍历=======================");
        bst.preOrder();

        System.out.println("=================非递归前序遍历=====================");
        bst.preOrderNonRecursive();

        System.out.println("=================递归中序遍历=======================");
        bst.inOrder();

        System.out.println("=================递归后序遍历=======================");
        bst.postOrder();

        System.out.println("====================层序遍历=======================");
        bst.levelOrder();


        System.out.println("删除元素 " + bst.removeMin() + " 删除后结果: ");
        bst.levelOrder();

        System.out.println("删除元素 " + bst.removeMin() + " 删除后结果: ");
        bst.levelOrder();

        System.out.println("删除元素 " + bst.removeMin() + " 删除后结果: ");
        bst.levelOrder();

        System.out.println("删除元素 " + bst.removeMin() + " 删除后结果: ");
        bst.levelOrder();


        System.out.println("删除元素 " + bst.removeMax() + " 删除后结果: ");
        bst.levelOrder();

        System.out.println("删除元素 " + bst.removeMax() + " 删除后结果: ");
        bst.levelOrder();
    }
}