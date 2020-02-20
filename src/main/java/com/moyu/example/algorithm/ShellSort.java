package com.moyu.example.algorithm;

/**
 * Created by Joker on 20/2/18.
 *   强烈推荐可视化视频: https://www.bilibili.com/video/av17062242/
 */
public class ShellSort<T extends Comparable> {


    public void shellSort(T[] nums) {
        int n = nums.length;
        for (int k = n / 2; k > 0; k /= 2) {
            insertSort(nums, k);
            System.out.println("out: " + get(nums));
            System.out.println("=========================");
        }
    }

    private void insertSort(T[] nums, int k) {
        int j ;
        for (int m = k; m < nums.length; m ++) {
            j = m;
            T e = nums[j];
            for (int n = j - k ; n >= 0 && nums[j - k].compareTo(e) == 1; n = j - k) {
                nums[j] = nums[j - k];
                j = j - k;
            }
            nums[j] = e;
        }
    }





    private String get(T[] nums) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1)
                res.append(nums[i]);
            else
                res.append(nums[i]).append(", ");
        }

        return res.toString();
    }


    private void testMain() {
        Integer[] nums = {61, 109, 149, 111, 34, 2, 24, 119, 122, 125, 27, 145};
        System.out.println("排序前: " + get((T[]) nums));

        System.out.println("======================");

        shellSort((T[]) nums);
        System.out.println("排序后: " + get((T[]) nums));
    }

    public static void main(String[] args) {
        ShellSort<Integer> insertionSort = new ShellSort();
        insertionSort.testMain();
    }

}
