package com.moyu.example.jobs;

/**
 * Created by Joker on 20/2/24.
 *   LeetCode 88: 合并两个有序数组
 */
public class Merge {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int length = m + n;

        int j = 0;
        int k = 0;

        int[] tmps = new int[length];

        for (int i = 0; i < length; i ++) {

            if (j >= m) {
                tmps[i] = nums2[k];
                k ++;
            } else if (k >= n) {
                tmps[i] = nums1[j];
                j ++;
            } else if (nums1[j] <= nums2[k]) {
                tmps[i] = nums1[j];
                j ++;
            } else if (nums2[k] < nums1[j]) {
                tmps[i] = nums2[k];
                k ++;
            }
        }

        for (int i = 0; i < length; i ++)
            nums1[i] = tmps[i];
    }

    private void testMain() {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        System.out.println("合并前: " + get(nums1));
        merge(nums1, 3, nums2, 3);
        System.out.println("合并后: " + get(nums1));
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

    public static void main(String[] args) {
        new Merge().testMain();
    }
}
