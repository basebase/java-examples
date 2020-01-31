package com.moyu.example.structure.stack;

import java.util.Stack;

/**
 * Created by Joker on 19/9/14.
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
     1. 左括号必须用相同类型的右括号闭合。
     2. 左括号必须以正确的顺序闭合。
 */
public class ValidParenttheses {


    public boolean isValid(String s) {
        java.util.Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else {

                if (stack.isEmpty())
                    return false;

                Character character = stack.pop();
                if (c == ')' && character != '(')
                    return false;
                if (c == '}' && character != '{')
                    return false;
                if (c == ']' && character != '[')
                    return false;
            }
        }

        return stack.isEmpty();
    }


}
