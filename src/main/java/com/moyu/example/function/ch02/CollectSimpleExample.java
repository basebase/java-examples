package com.moyu.example.function.ch02;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Stream collect api使用例子
 */
public class CollectSimpleExample {
    public static void main(String[] args) {
//        Stream.of("a", "b", "c", "d", "e")
//                .filter(x -> {
//                    System.out.println(x);
//                    return x.equals("a");
//                }).count();

        // 如果返回的是Stream那么就是一个惰性求值, 如果返回的是另外一个值或者是空值则是一个及早求值
        List<String> collect = Stream.of("a", "b", "c", "d", "e")
                .collect(Collectors.toList());
        System.out.println(collect);

    }
}
