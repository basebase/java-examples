package com.moyu.example.leetcode;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

/**
 * Created by Joker on 20/2/9.
 */
public class LeetCode110 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isBalanced(TreeNode root) {
        int i = height(root, 0);
        return Math.abs(i) > 1 ? false : true;
    }


    private int height(TreeNode node, int h) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;

        int l = height(node.left, h + 1);
        int r = height(node.right, h + 1);


        if ((Math.abs(l) >= 1000 || Math.abs(r) >= 1000) && h == 0)
            return -9999;


        if (Math.abs(l - r) > 1) {
            System.out.println("no...");
            return -9999;
        } else {
            if (h == 0)
                return Math.abs(l - r);
            else
                return Math.max(l , r) + 1;
        }

    }


    private void testMain() {
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);

        root.left = t1;
        root.right = t2;

        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(5);

        t1.left = t3;
        t2.right = t4;

        TreeNode t5 = new TreeNode(6);
        TreeNode t6 = new TreeNode(7);

//        t3.left = t5;
//        t4.right = t6;





        System.out.println(isBalanced(root));

    }

    public static void main(String[] args) {
        new LeetCode110().testMain();
    }
}
