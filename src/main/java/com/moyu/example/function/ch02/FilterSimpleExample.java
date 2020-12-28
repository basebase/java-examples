package com.moyu.example.function.ch02;

import java.util.stream.Stream;

/**
 * filter函数例子
 */
public class FilterSimpleExample {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5)
                .filter(val -> val > 3)
                .map(val -> String.valueOf(val + " Yes !"))
                .forEach(val -> System.out.println(val));
    }
}
