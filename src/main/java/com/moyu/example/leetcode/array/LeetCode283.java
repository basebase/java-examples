package com.moyu.example.leetcode.array;

/**
 * Created by Joker on 20/2/15.
 */
public class LeetCode283 {

//    public void moveZeroes(int[] nums) {
//        /***
//         *  写法1: 暴力解法
//         */
//
//        int[] newNums = new int[nums.length];
//        int index = 0;
//
//        // 获取到所有非0的元素
//        for (int i = 0 ; i < nums.length ; i ++)
//            if (nums[i] != 0) {
//                newNums[index] = nums[i];
//                index ++;
//            }
//
//
//        // 重新对nums数组赋值
//        for (int i = 0; i < nums.length; i ++) {
//            if (i < index) {
//                nums[i] = newNums[i];
//            } else {
//                nums[i] = 0;
//            }
//        }
//
//    }


//    public void moveZeroes(int[] nums) {
//        /***
//         *  写法2: 原地移动
//         */
//
//        int k = 0;
//        for (int i = 0; i < nums.length; i ++) {
//            if (nums[i] != 0)
//                nums[k++] = nums[i]; // 这里我直接使用nums[k++]来写入元素位置并维护k要写入的下一个索引位置, 如果看不明白, 可以分成两段来写
//        }
//
//        for (int i = k; i < nums.length; i ++)
//            nums[i] = 0;
//    }

    public void moveZeroes(int[] nums) {
        /***
         *  写法3: 原地移动
         */
        int k = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != 0) {
                if (i != k) { // 如果整个数组都为0的话, 就不进行交换
                    nums[k++] = nums[i];
                    nums[i] = 0;
                } else {
                    k ++;
                }
            }
        }
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


    private void testMain() {
        int[] nums = {0,1,0,3,12};
        System.out.println("修改前nums = " + get(nums));
        moveZeroes(nums);
        System.out.println("修改后nums = " + get(nums));

    }

    public static void main(String[] args) {
        new LeetCode283().testMain();
    }
}
