package com.moyu.example.leetcode.array;

/**
 * Created by Joker on 20/2/15.
 */
public class LeetCode27 {


//    public int removeElement(int[] nums, int val) {
//
//        /***
//         * 写法1
//         */
//
//        int k = 0;
//        for (int i = 0 ; i < nums.length; i ++) {
//            if (nums[i] != val)
//                nums[k++] = nums[i];
//        }
//
//        return k;
//    }

    public int removeElement(int[] nums, int val) {

        /***
         * 写法2
         */

        int i = 0;
        int n = nums.length ;

        while (i < n) {
            if (nums[i] == val)
                nums[i] = nums[--n];
            else
                i++;
        }

        return n;
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
        int[] nums = {0,1,2,2,3,0,4,2};
        System.out.println("修改前nums = " + get(nums));
        System.out.println(removeElement(nums, 2));
        System.out.println("修改后nums = " + get(nums));

    }

    public static void main(String[] args) {
        new LeetCode27().testMain();
    }
}
