package com.moyu.example.leetcode.array;

/**
 * Created by Joker on 20/2/15.
 */
public class LeetCode80 {


    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1 || nums.length == 2) {
            return nums.length == 1 ? 1 : 2;
        }

        int k = 2;
        for (int i = 2; i < nums.length; i ++) {
            if (nums[i] != nums[k - 1] || nums[i] != nums[k - 2]) {
                nums[k ++] = nums[i];
            }
        }

        return k;
    }

    private String get(int[] nums) {
        StringBuffer res = new StringBuffer();
        for (int i = 0 ; i < nums.length; i ++) {
            if (i == nums.length - 1)
                res.append(nums[i]);
            else
                res.append(nums[i]).append(", ");
        }

        return res.toString();
    }


    private void testMain() {
        int[] nums = {1,1};
        System.out.println("修改前nums = " + get(nums));
        System.out.println(removeDuplicates(nums));
        System.out.println("修改后nums = " + get(nums));

    }

    public static void main(String[] args) {
        new LeetCode80().testMain();
    }
}
