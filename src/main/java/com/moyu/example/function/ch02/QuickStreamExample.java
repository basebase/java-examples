package com.moyu.example.function.ch02;

import java.util.stream.Stream;

/**
 *    快速体验Stream编程
 */
public class QuickStreamExample {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 5, 100, 200, 500, 1000, 101)
                .filter(x -> x > 100)
                .forEach(System.out::println);
    }
}