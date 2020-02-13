package com.moyu.example.leetcode;

/**
 * Created by Joker on 20/2/9.
 */
public class LeetCode111 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int minDepth(TreeNode root) {

        if (root == null)
            return 0;

        int r = 0;
        int l = 0;
        if (root.left != null)
            l = minDepth(root.left);
        if (root.right != null)
            r = minDepth(root.right);

        return Math.min(root.left == null ? r : l, root.right == null ? l : r) + 1;
    }


    private void testMain() {
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(5);

        root.left = t1;
        t1.left = t2;
        t2.left = t3;
        t3.left = t4;



        System.out.println(minDepth(root));

    }

    public static void main(String[] args) {
        new LeetCode111().testMain();
    }
}
