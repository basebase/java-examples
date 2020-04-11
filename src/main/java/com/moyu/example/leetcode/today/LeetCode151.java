package com.moyu.example.leetcode.today;

/****
 * LeetCode 151: 翻转字符串里的单词
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 *
 */

public class LeetCode151 {

    /***
     * 解题思路:
     *   这里我用的是暴力解决的方式, 利用额外的空间来记录切分后的字符串进行倒叙输出, 但是其实可以头部和尾部进行交换
     *   有兴趣的同学可以改进一下。
     *
     *   1. 首先对字符串进行切分, (注意, 我这里并没有判断输入进来的字符串是否空值, 而是在尾部判断返回值)
     *   2. 将切分后的字符数组进行倒序输出, 并使用新的空间存储
     *   3. 返回组装后的字符串
     * @param s
     * @return
     */
    public String reverseWords(String s) {

        String[] strs = s.split(" ");
        StringBuffer res = new StringBuffer();
        for (int i = strs.length - 1; i >= 0; i --) {
            if (strs[i].equals(""))
                continue;
            else
                res.append(strs[i]).append(" ");
        }

        if (res.length() == 0)
            return "";
        else
            return res.deleteCharAt(res.length() - 1).toString();
    }

    public void testMain() {
        String s = "    ";
        String res = reverseWords(s);
        System.out.println(res);
    }

    public static void main(String[] args) {
        new LeetCode151().testMain();
    }
}
