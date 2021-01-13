package com.moyu.example.algorithms.sort.ins;

import com.moyu.example.algorithms.sort.Sort;

import java.util.Random;

public class Ins<E extends Comparable<E>> implements Sort<E> {

    @Override
    public void sort(E[] e) {
        for (int i = 1; i < e.length; i ++) {
            int m = i;
            for (int j = m - 1; j >= 0; j --) {
                if (e[m].compareTo(e[j]) < 0) {
                    swap(e, m, j);
                    m = j;
                }
            }
        }
    }

    private void swap(E[] nums, int i, int j) {
        E e = nums[i];
        nums[i] = nums[j];
        nums[j] = e;
    }


    public static void main(String[] args) {
//        Integer[] nums = {10, 5, 8, 1, 3, 7};
        Sort<Integer> ins = new Ins<>();
        Integer[] nums = new Integer[1000];

        Random r = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(10000);
        }

        System.out.println("排序前: ");
        ins.show(nums);

        ins.sort(nums);
        System.out.println("排序后: ");
        ins.show(nums);

        ins.isSorted(nums);
    }
}
