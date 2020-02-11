package com.moyu.example.leetcode;

import java.util.*;

/**
 * Created by Joker on 20/2/9.
 */
public class LeetCode102 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {


        // Map<Integer, List<Integer>> map = new TreeMap<>();
        // levelOrder(map, root, 0);

        // List<List<Integer>> n = new ArrayList<List<Integer>>();
        // for (Integer key : map.keySet()) {
        //     n.add(map.get(key));
        // }

        // return n;

        List<List<Integer>> nums = new ArrayList<>();
        levelOrder(root, nums, 0);
        return nums;
    }

    private void levelOrder(TreeNode node, List<List<Integer>> res, int depath) {

        if (node == null) return ;

        List<Integer> ins = res.size() <= depath  ? new ArrayList<>() : res.get(depath);

        if (ins.size() == 0) {
            ins.add(node.val);
            res.add(ins);
        } else {
            ins.add(node.val);
        }

        levelOrder(node.left, res, depath + 1);
        levelOrder(node.right, res, depath + 1);
    }


    private void levelOrder(Map<Integer, List<Integer>> map, TreeNode node, int depath) {
        if (node == null) return ;

        List<Integer> n  = map.get(depath);
        if (n == null) {
            n = new LinkedList<>();
            n.add(node.val);
        } else {
            n.add(node.val);
        }

        map.put(depath, n);
        levelOrder(map, node.left, depath + 1);
        levelOrder(map, node.right, depath + 1);
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

        System.out.println(levelOrder(root));


    }

    public static void main(String[] args) {
        new LeetCode102().testMain();
    }
}
