package com.moyu.example.leetcode.array;

/**
 * Created by Joker on 20/2/15.
 */
public class LeetCode80 {


//    public int removeDuplicates(int[] nums) {
//        if (nums == null || nums.length == 0)
//            return 0;
//
//        if (nums.length == 1 || nums.length == 2) {
//            return nums.length == 1 ? 1 : 2;
//        }
//
//        int k = 2;
//        for (int i = 2; i < nums.length; i ++) {
//            if (nums[i] != nums[k - 1] || nums[i] != nums[k - 2]) {
//                nums[k ++] = nums[i];
//            }
//        }
//
//        return k;
//    }


    private void remove(int[] nums, int index) {
        // 所有数据向左移一位
        for (int i = index + 1; i < nums.length; i++)
            nums[i - 1] = nums[i];
    }

    public int removeDuplicates(int[] nums) {
        int i = 1; // 从索引1位置开始遍历
        int length = nums.length; // 数组长度
        int count = 1; // 重复元素出现次数

        while (i < length) {
            // 如果两个元素相同, count累加
            if (nums[i] == nums[i - 1]) {
                count ++;

                // 如果相同元素出现超过2次及以上, 进行逻辑上的删除
                if (count > 2) {
                    remove(nums, i);
                    i --; // 这里, 必须要i--, 因为移动回来的元素, 是否也为相同元素, 如果没有这步操作, 就会丢掉数据
                    length --; // 数组长度也必须减去, 相同元素进行了删除
                }
            } else {
                // 如果两个元素不相同, count初始化为1
                count = 1;
            }

            i ++;
        }

        return length;
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
        int[] nums = {1,1,1,1,1,2,3,3,3,4};
        System.out.println("修改前nums = " + get(nums));
        System.out.println(removeDuplicates(nums));
        System.out.println("修改后nums = " + get(nums));

    }

    public static void main(String[] args) {
        new LeetCode80().testMain();
    }
}
