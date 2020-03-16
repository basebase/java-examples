package com.moyu.example.leetcode.array;


/***
 * LeetCode 345: 反转字符串中的元音字母
 *
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 * 科普: a、e、i、o、u 为元音字母。
 * 元音字母不包含字母"y"。
 *
 */
public class LeetCode345 {

    private boolean isVowels(char c) {

        if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u' || c == 'U')
            return true;

        return false;
    }

    /***
     *
     * 解题思路:
     *   对撞指针来实现
     *   具体思路可以参考LeetCode125和LeetCode344的思路, 类似两种结合在一起的思路。
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        char[] chs = s.toCharArray();
        int i = 0;
        int j = chs.length - 1;
        while (i < j) {
            if (isVowels(chs[i]) && isVowels(chs[j])) {
                char c = chs[i];
                chs[i] = chs[j];
                chs[j] = c;

                i ++;
                j -- ;
            } else if (isVowels(chs[i]) && !isVowels(chs[j])) {
                j --;
            } else if (!isVowels(chs[i]) && isVowels(chs[j])) {
                i ++;
            } else {
                i ++ ;
                j -- ;
            }
        }

        return String.valueOf(chs);
    }



    private void testMain() {
        String s = "aA";
        s = reverseVowels(s);
        System.out.println(s);
    }

    public static void main(String[] args) {
        new LeetCode345().testMain();
    }
}
