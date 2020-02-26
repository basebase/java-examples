package com.moyu.example.algorithm;

/**
 * Created by Joker on 20/2/26.
 */
public class QuickSort {


    public void quickSort(Integer[] nums, int n) {
        quickSort(nums, 0, n);
    }

    private void quickSort(Integer[] nums, int l, int r) {
        if (l >= r) // 递归结束条件
            return ;

        int p = partition(nums, l, r);
        quickSort(nums, l, p - 1);
        quickSort(nums, p + 1, r);
    }

    private int partition(Integer[] nums, int l, int r) {
        int v = nums[l];
        int j = l;
        for (int i = l + 1; i <= r ; i ++) {
            if (nums[i] < v) {
                swap(nums, i, j + 1);
                j ++;
            }
        }

        swap(nums, l, j);
        return j;
    }

    private void swap(Integer[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private String get(Integer[] nums) {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < nums.length; i ++) {
            if (i == nums.length - 1)
                res.append(nums[i]);
            else
                res.append(nums[i]).append(", ");
        }

        return res.toString();
    }

    private void testMain() {
        Integer[] nums = {15, 2, 30, 45, 80, 9};
        System.out.println("Quick Sort Before: " + get(nums));
        quickSort(nums, nums.length - 1);
        System.out.println("Quick Sort After: " + get(nums));
    }

    public static void main(String[] args) {
        new QuickSort().testMain();
    }
}
