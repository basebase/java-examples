package com.moyu.example.leetcode.today;

import java.util.*;

/**
 * Created by Joker on 20/3/16.
 *
 * LeetCode 409: 最长回文串
 *
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。

 * 注意：假设字符串的长度不会超过 1010。
 *
 */
public class LeetCode409 {


    /***
     * 解题思路:
     *   其实要构建回文串, 只要发现能成双成对的出现, 那么一定能翻转回去的。所以可以利用栈, 利用链表等数据结构存储, 然后碰到相同的值就移除。
     *   底部的+1是因为, 回文串中间的值可以不进行判断, 所以可以多加入一个字符上去。
     *
     *   1. 判断字符串是否为空或者为null, 如果是则直接返回
     *   2. 如果字符串长度小于2, 则可以直接返回当前字符串长度。
     *   3. 利用链表记录当前字符, 如果有相同的则移除, 并且长度加2
     *   4. 遍历结束之后, 如果当前长度不为整个字符串总长度, 则可以加1个字符上去作为分界点, 否则不需要。
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        if (s == null || s.equals(""))
            return 0;
        if (s.length() == 1)
            return 1;

        int len = 0;
        List<Character> characterList = new LinkedList<>();
        char[] chs = s.toCharArray();
        for (int i = 0 ; i < chs.length; i ++) {
            if (characterList.contains(chs[i])) {
                Character c = chs[i];
                characterList.remove(c);
                len += 2;
            } else {
                characterList.add(chs[i]);
            }
        }

        if (s.length() == len)
            return len;
        else
            return len + 1;
    }

    public void testMain() {
        String s = "abccccdd";
        int len = longestPalindrome(s);
        System.out.println(len);
    }

    public static void main(String[] args) {
        new LeetCode409().testMain();
    }
}
