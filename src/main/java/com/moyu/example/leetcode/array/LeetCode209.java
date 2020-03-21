package com.moyu.example.leetcode.array;


/***
 * LeetCode 209: 长度最小的子数组
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
 * 如果不存在符合条件的连续子数组，返回 0。
 */
public class LeetCode209 {


    /****
     * 解题思路:
     *   利用滑动窗口来解决此问题, 那么滑动窗口如何解决呢?
     *
     *   1. 首先设置两个指针, 指针l和指针r, 初始化的时候不包含任何区间, 指针r初始值为-1
     *   2. 使用一个变量记录指针l和指针r区间内的值是否大于s, 如果大于s, 则更新为最小的连续子数组长度
     *   3. 如果[l..r]区间的值大于s, 则先减去l的值, 并将指针l向前移动一位, 反之指针r向前移动一位累加值
     *   4. 如果数组遍历结束都么有找到大于等于s的值, 则直接返回0, 否则返回最小连续子数组长度
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int l = 0;
        int r = -1;
        int sum = 0;
        int len = nums.length + 1;

        while (l < nums.length) {

            /***
             *   使用r + 1 < nums.length来解决下面一大段的if判断, 这是很优雅的操作。
             *   不过, 如果使用这种方式, 就不能再else中直接获取最小的长度值了。
             *
             *   问题: 为什么加入r + 1 < nums.length就不能再else中获取最小长度了呢
             *        ? 自己可以先思考一下, 或者debug一下, 再来看我写的答案。
             *
             *   答: 其实很简单, 当指针r已经到最后的位置时候, 我们就会进入else, 间接导致当前的sum值是大于等于s的
             *      所以, 不管怎么做, 最后我们的长度会为1, 指针l和指针r一定会碰到的。。。
             *      所以需要在最下方加入一个判断。
             *
             */
            if ( r + 1 < nums.length && sum < s) {
                sum += nums[++r];

                /****
                 *
                 * 这里是可以优化的一个点, 而且代码基本上重复了
                 *
                 * if (r < nums.length - 1)
                 *   sum += nums[++r];
                 * else
                 *   sum -= nums[l++];
                 */

            } else {
//                len = len < r - l + 1 ? len : r - l + 1;
                sum -= nums[l++];
            }

            if (sum >= s)
                len = len < r - l + 1 ? len : r - l + 1;
        }

        if (len == nums.length + 1)
            return 0;
        return len;
    }

    private void testMain() {
        int[] nums = {2,3,1,2,4,3};
        int t = minSubArrayLen(7, nums);
        System.out.println(t);
    }

    public static void main(String[] args) {
        new LeetCode209().testMain();
    }
}
