package com.moyu.example.leetcode.array;


/***
 * LeetCode 167: 两数之和 II - 输入有序数组
 *
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 *  说明:
 *      返回的下标值（index1 和 index2）不是从零开始的。
 *      你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 */
public class LeetCode167 {


    /***
     * 使用对撞指针的思路来实现该问题的解
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {

        int n = numbers.length;
        int i = 0 ;
        int j = n - 1;

        int [] res = new int[2];

        /***
         *  思路分析:
         *    首先我们知道数组是一个有序排列的, 以数组[2, 7, 11, 15]进行分析
         *    我们有指针i和指针j两个指针, 指针i在数组头部, 指针j在数组尾部。
         *
         *    第一次: i = 0, j = 3, 2 + 15 = 17 , 而我们的target为9, 这个时候, 由于比较大, 指针j的位置需要减小, j --
         *    第二次: i = 0, j = 2, 2 + 11 = 13, 发现还是比target大, 指针j继续减小
         *    第三次: i = 0, j = 1, 2 + 7 = 9, 此时发现和target相等, 返回对应的索引位置加1
         *
         *    也就是说, 如果大于指针j就往回退, 如果小于指针i就往前进。直到指针i大于指针j的时候, 也就说在这个数组中找不到target
         */

        while (i < j) {

            if (numbers[i] + numbers[j] == target) {
                res[0] = i + 1;
                res[1] = j + 1;
                break;
            } else if (numbers[i] + numbers[j] < target) {
                i ++ ;
            } else {
                j -- ;
            }
        }

        return res;
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
        int[] nums = {2, 7, 11, 15};
        System.out.println("nums = " + get(nums));
        System.out.println("获得索引为: " + get(twoSum(nums, 9)));
    }

    public static void main(String[] args) {
        new LeetCode167().testMain();
    }
}
