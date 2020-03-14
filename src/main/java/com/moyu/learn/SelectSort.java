package com.moyu.learn;

/**
 * Created by Joker on 20/3/9.
 */
public class SelectSort {


    public void selectSort(int[] nums) {
        int n = nums.length;
        for (int i = 0 ; i < n; i ++) {
            int mid = i;
            for (int j = i + 1; j < n; j ++) {
                if (nums[j] < nums[mid]) {
                    mid = j;
                }
            }

            swap(nums, i, mid);
            System.out.println("数组第" + (i + 1) + "次排序结果: " + get(nums));
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
        int[] nums = {5, 4, 3, 2, 1};
        System.out.println("数组初始化结果集: " + get(nums));
        selectSort(nums);
        System.out.println("排序后的结果: " + get(nums));
    }

    public static void main(String[] args) {
        new SelectSort().testMain();
    }
}
