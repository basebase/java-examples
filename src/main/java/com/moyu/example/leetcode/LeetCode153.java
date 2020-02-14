package com.moyu.example.leetcode;

/**
 * Created by Joker on 20/2/14.
 *
 */
public class LeetCode153 {

    public int findMin(int[] nums) {
        return findMin(nums, 0);
    }

    private int findMin(int[] nums, int k) {
        if (nums.length == k + 1)
            return nums[k];

        int res = findMin(nums, k + 1);
        return Math.min(nums[k], res);
    }

    private void testMain() {
        int[] nums = {4,5,6,7,0,1,2};
        int min = findMin(nums);
        System.out.println(min);
    }

    public static void main(String[] args) {
        new LeetCode153().testMain();
    }
}
