package com.moyu.example.algorithm;

/**
 * Created by Joker on 20/2/26.
 */
public class QSort {

    public void testMain() {
        int[] nums = {5, 10, 8, 22, 30};
        System.out.println("Quick Sort Before: " + get(nums, 0, nums.length - 1));
        quickSort(nums);
        System.out.println("Quick Sort After: " + get(nums, 0, nums.length - 1));
    }

    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int l, int r) {
        if (l >= r)
            return ;

        int p = partition(nums, l, r);
        quickSort(nums, l, p - 1);
        System.out.println("p - 1: " + get(nums, l, p - 1));
        quickSort(nums, p + 1, r);
        System.out.println("p + 1: " + get(nums, p + 1, r));
    }

    private int partition(int[] nums, int l, int r) {
        int v = nums[l];
        int j = l;
        for (int i = l + 1; i <= r; i ++) {
            if (nums[i] < v) {
                swap(nums, i, j + 1);
                j ++;
            }
        }

        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private String get(int[] nums, int l, int r) {
        StringBuffer res = new StringBuffer();
        for (int i = l; i <= r; i ++) {
            if (i == r)
                res.append(nums[i]);
            else
                res.append(nums[i]).append(", ");
        }

        return res.toString();
    }

    public static void main(String[] args) {
        new QSort().testMain();
    }
}
