package com.moyu.example.leetcode.today;

/**
 * Created by Joker on 20/3/16.
 *
 * LeetCode 面试题 01.06. 字符串压缩
 *
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
 * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，
 * 则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 *
 */
public class LeetCode0106 {


    /***
     *
     * 解题思路:
     *   首先记录一个字符, 然后循环遍历数组, 判断是否和记录的字符一样, 如果不一样表示是不同的值了, 需要写入数据, 并且重新初始化值
     *
     *   1. 判断当前传递过来的字符串是否为空字符串或者只有一个字符, 直接返回就可以。
     *   2. 满足条件1之后, 我们需要记录第一个字符, 然后遍历字符数组, 若当前的值和记录的字符相同则累加, 否则写入值并初始化数据
     *   3. 最后的字符由于后面没有不同的值了, 所以没法进入else条件, 所以需要在循环之外记录。
     *   4. 判断发现, 如果输入的字符串比我们压缩后的字符串要小或者等于我们都应该使用压缩前的字符串
     *
     * 以上是此题的一个解法思路。
     *
     * @param S
     * @return
     */

    public String compressString(String S) {

        if (S.equals(""))
            return "";
        if (S.length() == 1)
            return S;

        char[] chs = S.toCharArray();
        int count = 1;
        char c = chs[0];
        String res = "";
        for (int i = 1; i < chs.length; i ++) {
            if (chs[i] == c) {
                ++ count;
            } else {
                res += "" + c + count;
                c = chs[i];
                count = 1;
            }
        }

        res += "" + c + count;

        return S.length() <= res.length() ? S : res;
    }

    public void testMain() {
        String s = "abbccd";
        s = compressString(s);
        System.out.println(s);
    }

    public static void main(String[] args) {
        new LeetCode0106().testMain();
    }
}
