package com.moyu.example.leetcode;

/**
 * Created by Joker on 20/2/14.
 */
public class LeetCode27 {

    public int removeElement(int[] nums, int val) {
        if (nums == null)
            return 0;

        int index = 0;
        for (int i = 0; i < nums.length ; i ++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                ++index;
            }
        }

        return index ;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        System.out.println("修改前...");
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < nums.length; i ++) {
            if (i == nums.length)
                res.append(nums[i]);
            else
                res.append(nums[i]).append(",");
        }

        System.out.println("res = " + res);

        int k = new LeetCode27().removeElement(nums, 2);

        System.out.println("修改后...");
        res = new StringBuffer();
        for (int i = 0; i < nums.length; i ++) {
            if (i == nums.length)
                res.append(nums[i]);
            else
                res.append(nums[i]).append(",");
        }

        System.out.println("res = " + res);

        System.out.println(k);
    }
}
