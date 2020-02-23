package com.moyu.example.algorithm;

/**
 * Created by Joker on 20/2/20.
 */
public class MergeSort<T extends Comparable> {

    public void mergeSort(T[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(T[] nums, int l, int r) {
        if (l >= r) return ;

        int mid = (l + r) / 2;
        // 左右两边切分
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);

        // 进行合并
        merge(nums, l, mid, r);
    }

    private void merge(T[] nums, int l, int mid, int r) {
        // 1. 创建数组用来接收
        Object[] aux = new Object[r - l + 1];
        for (int i = l; i <= r; i ++)
            aux[i - l] = nums[i];

        // 2. 设置i,j两个指针, 分别指向左和右初始第一个的值
        int i = l, j = mid + 1;

        for (int k = l; k <= r; k ++) {
            if ( i > mid ) {
                nums[k] = (T) aux[j - l];
                j ++;
            } else if ( j > r ) {
                nums[k] = (T) aux[i - l];
                i ++;
            } else if (((T) aux[i - l]).compareTo((T) aux[j - l]) == -1) {
                nums[k] = (T) aux[i - l];
                i ++;
            } else {
                nums[k] = (T) aux[j - l];
                j ++;
            }
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
        Integer[] nums = {1, 5, 7, 2, 4, 8};
        System.out.println("排序前: " + get((T[]) nums));
        mergeSort((T[]) nums);
        System.out.println("排序后: " + get((T[]) nums));
    }

    public static void main(String[] args) {
        MergeSort<Integer> m = new MergeSort<>();
        m.testMain();
    }
}
