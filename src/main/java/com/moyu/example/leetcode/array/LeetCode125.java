package com.moyu.example.leetcode.array;


/***
 * LeetCode 125: 验证回文串
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 *  说明:
 *      本题中，我们将空字符串定义为有效的回文串。
 */
public class LeetCode125 {

    private boolean isPal(char c) {
        if (c >= '0' && c <= '9')
            return true;
        else if (c >= 'a' && c <= 'z')
            return true;
        return false;
    }


    public boolean isPalindrome(String s) {

        s = s.toLowerCase();
        char[] chs = s.toCharArray();
        int i = 0;
        int j = chs.length - 1;

        /****
         * 解题思路:
         *   利用对撞指针来完成回文串的验证。
         *   1. 首先i在第一个字符上, 判断是否为数字或者字母, 如果不是则不符合我们要的字符, 指针i前进。
         *   2. 指针j在字符数组的尾部, 也是判断是否符合当前条件, 要么是数字, 要么是字母。如果不是则指针j往回退。
         *   3. 需要注意的是, 如果指针和指针j两个位置的值都不是正确的字符, 我们需要把指针i前进, 指针j回退。
         *   4. 如果两边都满足字符条件, 进行判断, 如果不相同, 则直接返回false, 后面不需要进行访问。
         *   5. 当指针i大于指针j的时候, 我们就遍历结束了, 此时如果没有返回false就指针返回true
         *
         * 以上, 就是对回文串的验证过程。
         */

        while (i < j) {
           if (!isPal(chs[i])) {
               i ++;
           } else if (!isPal(chs[j])) {
               j --;
           } else if (!isPal(chs[i]) && !isPal(chs[j])) {
               i ++;
               j --;
           } else {
               if (chs[i] != chs[j])
                   return false;
               else {
                   i ++;
                   j --;
               }
           }
       }


       return true ;
    }

    private void testMain() {
        String s = " . . . ";
        System.out.println(isPalindrome(s));
    }

    public static void main(String[] args) {
        new LeetCode125().testMain();
    }
}
