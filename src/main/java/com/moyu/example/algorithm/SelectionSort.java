package com.moyu.example.algorithm;

/**
 * Created by Joker on 20/2/17.
 */
public class SelectionSort<T extends Comparable> {

    public void selectionSort(T[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i].compareTo(nums[j]) == 1)
                    index = j;
            }

            swap(nums, i, index);
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
//        int[] nums = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Float[] nums = {5.1f, 4.1f, 3.1f, 2.1f, 1.1f, 0.8f, 0.7f, 0.3f, 0.2f, 0f};
        System.out.println("排序前: " + get((T[]) nums));
        selectionSort((T[]) nums);
        System.out.println("排序后: " + get((T[]) nums));
    }

    public static void main(String[] args) {
        SelectionSort<Float> selectSort = new SelectionSort<>();
        selectSort.testMain();
    }
}
