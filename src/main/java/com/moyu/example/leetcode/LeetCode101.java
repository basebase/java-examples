package com.moyu.example.leetcode;

import java.util.*;

/**
 * Created by Joker on 20/2/9.
 */
public class LeetCode101 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSymmetric(TreeNode root) {

        /***
         * 迭代写法
         */
//        Queue<TreeNode> q = new LinkedList<>();
//
//        q.add(root);
//        q.add(root);
//
//        while (!q.isEmpty()) {
//
//            TreeNode t1 = q.poll();
//            TreeNode t2 = q.poll();
//
//            if (t1 == null && t2 == null) continue;
//            if (t1 == null || t2 == null) return false;
//
//            if (t1.val != t2.val) return false;
//
//            q.add(t1.left);
//            q.add(t2.right);
//
//            q.add(t1.right);
//            q.add(t2.left);
//        }
//
//        return true;

        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode t1, TreeNode t2) {
        /***
         *   递归写法
         */
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        if (t1.val != t2.val) return false;

        return isSymmetric(t1.left, t2.right) && isSymmetric(t1.right, t2.left);
    }




    private void testMain() {
        TreeNode root = new TreeNode(1);
        TreeNode l1 = new TreeNode(2);
        TreeNode r1 = new TreeNode(2);
        TreeNode ll1 = new TreeNode(3);
        TreeNode lr1 = new TreeNode(4);
        TreeNode rl1 = new TreeNode(4);
        TreeNode rr1 = new TreeNode(3);

        root.left = l1;
        root.right = r1;

        l1.left = ll1;
        l1.right = lr1;

        r1.left = rl1;
        r1.right = rr1;

        System.out.println(isSymmetric(root));

    }

    public static void main(String[] args) {
        new LeetCode101().testMain();
    }
}
