package com.moyu.example.function.ch01;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 *      描述:     Lambda表达式类型推断
 */
public class LambdaTypeInference {
    public static void main(String[] args) {

        // 创建方式1
        Map<String, Integer> map1 = new HashMap<String, Integer>();

        // 创建方式2
        Map<String, Integer> map2 = new HashMap<>();

        Map map = func1(new HashMap<>());
        System.out.println(map.get("1"));

        Predicate<Integer> p1 = x -> x > 10;
        System.out.println(p1.test(1));
        System.out.println(p1.test(11));

        BinaryOperator<Long> add = (x, y) -> x + y;
        System.out.println(add.apply(1L, 2L));

//        BinaryOperator add2 = (x, y) -> x + y;

    }

    public static Map func1(Map<String, Integer> m) {
        m.put("1", 1);
        return m;
    }
}
