package com.moyu.example.function.ch02;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * FlatMap函数例子
 */
public class FlatMapSimpleExample {
    public static void main(String[] args) {
        String data = "a,b,c,d,e,a";
        Stream.of(data)
                .flatMap(str -> Arrays.stream(str.split(",")))
                .distinct()
                .forEach(str -> System.out.println(str));

        System.out.println("=======");

        Stream.of(data)
                .map(str -> str.split(","))
                .distinct()
                .forEach(str -> {
                    for (String s : str) {
                        System.out.println(s);
                    }
                });
    }
}
