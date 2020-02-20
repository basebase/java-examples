package com.moyu.example.algorithm;

/**
 * Created by Joker on 20/2/18.
 */
public class InsertionSort<T extends Comparable> {

    public void insertionSort(T[] nums) {
        for (int i = 1; i < nums.length ; i ++) {
            T e = nums[i]; // 当前元素的一份拷贝
            int j ; // 保存元素e所插入的索引位置

            /***
             * 如果当前元素大于之前的元素, 我们当前索引位置还是i, 所以不用担心索引位置会错位。
             */
            for (j = i ; j > 0 && e.compareTo(nums[j - 1]) == -1  ; j --) {
                nums[j] = nums[j - 1];
            }

            nums[j] = e;
        }
    }

    private void swap(T[] nums, int i, int j) {
        T tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
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
        Integer[] nums = {8, 6, 2, 3, 1, 5, 7, 4};
        System.out.println("排序前: " + get((T[]) nums));
        insertionSort((T[]) nums);
        System.out.println("排序后: " + get((T[]) nums));
    }

    public static void main(String[] args) {
        InsertionSort<Integer> insertionSort = new InsertionSort();
        insertionSort.testMain();
    }

}
