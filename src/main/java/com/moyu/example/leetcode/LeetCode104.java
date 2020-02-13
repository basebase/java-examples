package com.moyu.example.leetcode;

/**
 * Created by Joker on 20/2/9.
 */
public class LeetCode104 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root != null && root.left == null && root.right == null)
            return 1;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }



    private void testMain() {
        TreeNode root = new TreeNode(3);
        TreeNode l1 = new TreeNode(9);
        TreeNode r1 = new TreeNode(20);
        TreeNode ll1 = new TreeNode(3);
        TreeNode lr1 = new TreeNode(4);
        TreeNode rl1 = new TreeNode(15);
        TreeNode rr1 = new TreeNode(3);

        root.left = l1;
        root.right = r1;

        l1.left = ll1;
        l1.right = lr1;

//        r1.left = rl1;
//        r1.right = rr1;

        System.out.println(maxDepth(root));

    }

    public static void main(String[] args) {
        new LeetCode104().testMain();
    }
}
