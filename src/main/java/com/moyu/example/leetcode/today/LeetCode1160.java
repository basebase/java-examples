package com.moyu.example.leetcode.today;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joker on 20/3/16.
 *
 * LeetCode 1160: 拼写单词
 *
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 *
 */
public class LeetCode1160 {

    private HashMap<Character, Integer> getWordCharMap(char[] chs) {
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int i = 0 ; i < chs.length; i ++)
            charMap.put(chs[i], charMap.get(chs[i]) == null ? 1 : charMap.get(chs[i]) + 1 );
        return charMap;
    }


    /****
     *
     * 解题思路:
     *   暴力解法, 首先记住chars出现是字符次数, 然后依次和单词进行比较,
     *   如果chars字符出现的次数小于单词次数或者就没有单词,
     *   则直接退出循环并不记录该单词长度, 继续一下遍历。
     *
     *
     *   1. 遍历字母表, 并记录出现次数。
     *   2. 遍历单词, 并记录出现次数。
     *   3. 判断, 字母表中是否存在对应单词, 或者大于单词出现次数, 如果是继续遍历下一个单词, 否则退出本次循环
     *   4. 根据条件3判断是否要把该单词长度记录
     *   5. 单词遍历完成后, 将把值重新初始化, 继续循环2~4步骤, 直到单词数组遍历完毕。
     * @param words
     * @param chars
     * @return
     */
    public int countCharacters(String[] words, String chars) {
        char[] chs = chars.toCharArray();
        HashMap<Character, Integer> charMap = getWordCharMap(chs);

        boolean f = true;
        int len = 0;

        for (int i = 0 ; i < words.length; i ++) {
            String s = words[i];
            HashMap<Character, Integer> wordMap = getWordCharMap(s.toCharArray());

            for (Map.Entry<Character, Integer> m : wordMap.entrySet()) {

                Character key = m.getKey();

                if (charMap.get(key) == null || charMap.get(key) < m.getValue()) {
                    f = false;
                    break;
                }
            }

            if (f)
                len += s.length();
            f = true;


        }

        return len;
    }

    public void testMain() {
        String[] words = {"hello","world","leetcode"};
        String chars = "welldonehoneyr";
        int len = countCharacters(words, chars);
        System.out.println(len);
    }

    public static void main(String[] args) {
        new LeetCode1160().testMain();
    }
}
