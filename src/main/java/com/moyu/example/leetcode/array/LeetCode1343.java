package com.moyu.example.leetcode.array;

public class LeetCode1343 {

    /***
     *
     *  暴力解法
     */
//    public int numOfSubarrays(int[] arr, int k, int threshold) {
//        int len = arr.length;
//        int count = 0;
//
//        for (int i = 0; i < len; i++) {
//            int sum = 0;
//            int tmp = k;
//            int j = i;
//            while (tmp -- > 0) {
//                sum += arr[j ++];
//            }
//
//            if (sum / k >= threshold) {
//                ++ count;
//            }
//
//
//            if (i + k > arr.length - 1)
//                break;
//        }
//
//        return count;
//    }


    /***
     *      滑动窗口思想:
     *          1. 首先从数组0...k个位置进行累加一个sum值, 如果一开始就大于等于threshold变量进行count累加
     *          2. 之后sum的值就是0...k的值, 我们只需要每次往前加1位就可以并减去最前面的值就得到我们想要的值了
     */
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int len = arr.length;
        int count = 0;

        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        if (sum / k >= threshold)
            ++ count;

        for (int i = k; i < len; i++) {
            sum += arr[i] - arr[i - k];
            if (sum / k >= threshold)
                ++ count;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {11,13,17,23,29,31,7,5,2,3};
        int i = new LeetCode1343().numOfSubarrays(arr, 3, 5);
        System.out.println(i);
    }
}
