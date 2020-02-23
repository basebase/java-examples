package com.moyu.example.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Joker on 20/2/23.
 */
public class CompleteBinaryTree {

    private class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public boolean isComplete(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (flag && (node.left != null || node.right != null)) {
                return false;
            }
            if (node.left == null && node.right != null) {
                return false;
            } else if (node.left == null || node.right == null) {
                flag = true;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return true;
    }


    public void testMain() {
        TreeNode root = new TreeNode(1);

        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);

        root.left = t1;
        root.right = t2;

        TreeNode t3 = new TreeNode(4);
//        TreeNode t4 = new TreeNode(5);

        t1.left = t3;
//        t1.right = t4;


//        TreeNode t5 = new TreeNode(6);
//        t2.left = t5;
//
//        TreeNode t6 = new TreeNode(7);
//        TreeNode t7 = new TreeNode(8);
//
//        t3.left = t6;
//        t3.right = t7;



        boolean complete = isComplete(root);
        System.out.println("is Complete Tree => " + complete);
    }

    public static void main(String[] args) {
        new CompleteBinaryTree().testMain();
    }
}
