package com.moyu.example.algorithm;

/**
 * Created by Joker on 20/2/15.
 *   二分搜索算法
 */
public class BinarySearch {

    public static int binarySearch(int[] nums, int target) {

        if (nums == null)
            return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;

        int l = 0;
//        int r = nums.length - 1;
        int r = nums.length;


        /***
         *   注意:
         *     1. 如果我们使用 l <= r 这种方式的话就是在[l...r]这种前闭后闭区间查找, 所以可以<= (l = 0, r = nums.length - 1)
         *     2. 但是, 有些时候有人写 l < r这种方式, 这样的话就是在[l...r)前闭后开的区间查找, 所以使用< (l = 0, r = nums.length)
         *
         *     如果, 我们在使用方法2的时候, 却使用<=方式查找一个不存在的元素, 这个时候就会抛出一个异常
         *       java.lang.ArrayIndexOutOfBoundsException
         *     需要大家注意一下。
         */
        while (l < r) {

            /***
             *   注意:
             *     如果使用(l + r) / 2来计算mid值的话, 会出现一个bug值, 当这两个int型数据(l和r)足够大的时候
             *     l + r可能会产生整形溢出。
             *
             *     所以, 我们要避免这个问题的话, 不直接使用加法, 而是使用 l + (r - l) / 2
             */
            int mid = l + (r - l) / 2;

            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                r = mid; // [l...mid)区间中, 这里就不应在减1个元素了, 否则对丢掉数据
//                r = mid - 1; // [l...mid-1]区间中

            else if (nums[mid] < target)
                l = mid + 1; // [mid+1...r)区间中
//                l = mid + 1; // [mid+1...r]区间中
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int i = binarySearch(nums, 71);
        System.out.println("i = " + i);
    }


}
