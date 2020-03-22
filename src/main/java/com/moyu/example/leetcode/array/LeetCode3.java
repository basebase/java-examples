package com.moyu.example.leetcode.array;


/***
 * LeetCode 3: 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LeetCode3 {


    /****
     * 解题思路:
     *   利用滑动窗口的思想, 设置指针l和指针j, 一个区间内进行判断是否出现重复元素, 如果没有则指针j向前进, 否则指针l向前进
     *
     *   1. 首先定义指针l和指针j的初始值
     *   2. 如果区间的字符串不包含的话, 则将该字符累加并且指针j往后移动, 否则指针l向后移动。
     *   3. 如果当前字符串包含该字符, 则获取当前字符串长度并取最大的长度值。
     *   4. 如果指针l和指针r都要末尾了, 但是依旧没有重复元素, 那么就把当前已存储的字符串长度进行比较, 取出最长的字符串。
     * @param s
     * @return
     */

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals(""))
            return 0;
        if (s.length() == 1)
            return 1;

        char[] chs = s.toCharArray();

        int l = 0;
        int r = 0;
        String res = "";
        int len = 0;
        while (l < chs.length - 1) {

            if (r + 1 < chs.length && !res.contains(String.valueOf(chs[r])))
                res += chs[r++];
            else
                ++ l;

            if (res.contains(String.valueOf(chs[r]))) {
                len = len < res.length() ? res.length() : len;
                res = res.substring(1, res.length());
            } else if (l == chs.length - 1 && !res.contains(String.valueOf(chs[r]))) {
                res += chs[l];
                len = len < res.length() ? res.length() : len;
            }
        }

        return len;
    }

    private void testMain() {
        String s = "auu";
        int t = lengthOfLongestSubstring(s);
        System.out.println(t);
    }

    public static void main(String[] args) {
        new LeetCode3().testMain();
    }
}
