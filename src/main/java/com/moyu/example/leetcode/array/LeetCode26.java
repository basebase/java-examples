package com.moyu.example.leetcode.array;

/**
 * Created by Joker on 20/2/15.
 */
public class LeetCode26 {


    public int removeDuplicates(int[] nums) {

        if (nums == null)
            return 0;
        if (nums.length <= 1)
            return nums.length == 0 ? 0 : 1;

        int k = 1;
        /***
         *  如果下一个元素依旧和上一个元素相同, 这里我们的k变量是不会更新的, 等到下一个不同的元素我们会更新k的索引位置信息
         */
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] != nums[k - 1])
                nums[k++] = nums[i];
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
        int[] nums = {1,1,1,2,2,2,3};
        System.out.println("修改前nums = " + get(nums));
        System.out.println(removeDuplicates(nums));
        System.out.println("修改后nums = " + get(nums));

    }

    public static void main(String[] args) {
        new LeetCode26().testMain();
    }
}
