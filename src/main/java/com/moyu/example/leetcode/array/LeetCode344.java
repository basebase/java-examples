package com.moyu.example.leetcode.array;


/***
 * LeetCode 344: 反转字符串
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 */
public class LeetCode344 {

    public void reverseString(char[] s) {

        /***
         * 解题思路:
         *   利用对撞指针, 分别为指针i和指针j, 一个在数组头部, 一个在数组尾部。
         *
         *   1. 首先, 如果我们数组是偶数个, 那么所有的字符都需要交换位置, 如果是奇数个中间一个是不需要交换的。
         *   2. 指针i在数组头部, 指针j在数组尾部。而我们题目要求就是把字符反转, 头和尾交换, 那么可以把i和j的位置交换, 利用O(1)的空间保存指针i的数据, 用于写入指针j中
         *   3. 交换完成后, 指针向前推进, 指针j往回退。
         *   4. 当指针i大于指针j的时候, 意味着他们已经全部交换完成了。
         */

        int i = 0;
        int j = s.length - 1;

        while (i < j) {
            char c = s[i];
            s[i] = s[j];
            s[j] = c;
            i ++;
            j --;
        }
    }

    private String get(char[] nums) {
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
        char[] s = {'H','a','n','n','a', 'h'};
        System.out.println("初始化: " + get(s));
        reverseString(s);
        System.out.println("反转后: " + get(s));
    }

    public static void main(String[] args) {
        new LeetCode344().testMain();
    }
}
