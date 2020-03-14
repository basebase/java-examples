package com.moyu.learn;

/**
 * Created by Joker on 20/3/10.
 */
public class InsertSort {

    public void insertSort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i ++) {
            for (int j = i; (j > 0 && nums[j] < nums[j - 1]); j --) {
                swap(nums, j, j - 1);
            }

            System.out.println("第 " + i + " 次排序后的结果: " + get(nums));
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
        int[] nums = {6, 5, 3, 1, 8, 7, 2, 4};
        System.out.println("初始化数组: " + get(nums));
        insertSort(nums);
        System.out.println("排序后的结果: " + get(nums));
    }

    public static void main(String[] args) {
        new InsertSort().testMain();
    }
}
