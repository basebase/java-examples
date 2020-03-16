package com.moyu.example.leetcode.array;


/***
 * LeetCode 11: 盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，
 * 使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 *  说明:
 *    你不能倾斜容器，且 n 的值至少为 2。
 */
public class LeetCode11 {

    /***
     *  暴力解法
     */
//    public int maxArea(int[] height) {
//        int j = height.length;
//
//        int v = 0;
//
//        for (int i = 0 ; i < j; i ++) {
//            for (int k = i + 1; k < j; k ++) {
//                int m = Math.min(height[i], height[k]);
//                v = v < (m * (k - i)) ? m * (k - i) : v;
//            }
//        }
//
//        return v;
//    }


    /***
     * 解题思路:
     *   使用对撞指针, 分别为指针i和指针j, 一个在数组头部, 一个在数组尾部。
     *
     *   1. 如果指针i的值小于指针j的值, 则指针i向前进。因为我们距离还是一样长的, 但我们要保留最大的值
     *   2. 当指针j小于指针i的值, 也是一样, 指针j往回退。间距还是一样大, 但是要保留最大的值。
     *   3. 经过上边的比较, 我们保留最大的面积值。
     *   4. 当前指针i大于指针j的时候, 我们就结束了。
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int maxArae = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {

            int m = Math.min(height[i], height[j]) * (j - i);

            if (height[i] < height[j])
                i ++;
            else
                j --;

            maxArae = m > maxArae ? m : maxArae;
        }

        return maxArae;
    }

    private void testMain() {
        int[] nums = {1,1};
        int t = maxArea(nums);
        System.out.println(t);
    }

    public static void main(String[] args) {
        new LeetCode11().testMain();
    }
}
