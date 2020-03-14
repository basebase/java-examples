package com.moyu.learn;

import java.util.Random;

/**
 * Created by Joker on 20/3/12.
 */
public class ShellSort {

    public void shellSort(int[] nums) {
        int n = nums.length;
        int k = n / 2;

        // k == 1, 按照正常的插入排序算法执行
        while (k >= 1) {

            for (int i = 0; i < n - k; i ++) {
                if (nums[i] > nums[i + k])
                    swap(nums, i, i + k);
                for (; (i - k >= 0 && nums[i] < nums[i - k]); i -=k)
                    swap(nums, i, i - k);
            }

            System.out.println("第 k = " + k + " 次排序结果: " + get(nums));

            // 维护k
            k /= 2;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private String get(int[] nums) {
        StringBuffer res = new StringBuffer("[ ");
        for (int i = 0 ; i < nums.length; i ++) {
            if (i == nums.length - 1)
                res.append(nums[i]).append("]");
            else
                res.append(nums[i]).append(", ");
        }

        return res.toString();
    }

    public void testMain() {
//        int[] nums = {61, 109, 149, 111, 34, 2, 24, 119, 122, 125, 27, 145};
        Random r = new Random();
        int[] nums = new int[100];

        for (int i = 0; i < 100; i ++)
            nums[i] = r.nextInt(1000);

        System.out.println("初始化数组: " + get(nums));
        shellSort(nums);
        System.out.println("排序后结果: " + get(nums));

        for (int i = 1 ; i < nums.length ; i ++) {
            if (nums[i - 1] > nums[i])
                throw new IllegalArgumentException("出错了...");
        }

        System.out.println("排序成功...");
    }

    public static void main(String[] args) {
        new ShellSort().testMain();
    }
}
