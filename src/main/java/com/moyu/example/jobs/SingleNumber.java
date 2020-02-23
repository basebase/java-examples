package com.moyu.example.jobs;

import java.util.*;

/**
 * Created by Joker on 20/2/23.
 *   LeetCode136: 只出现一次的数字
 *     这里的话，我觉得就推荐使用hash或者列表来实现。
 *     虽然位运算很牛逼，但我真的看不懂。。。
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < nums.length; i ++) {
            if (list.contains(nums[i]))
                list.remove((Object) nums[i]);
            else
                list.add(nums[i]);
        }

        return list.get(0);
    }

    private void testMain() {
        int[] nums = {1, 0, 1};
        int i = singleNumber(nums);
        System.out.println(i);
    }

    public static void main(String[] args) {
        new SingleNumber().testMain();
    }
}
