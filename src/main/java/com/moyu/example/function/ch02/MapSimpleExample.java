package com.moyu.example.function.ch02;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * map函数例子
 */
public class MapSimpleExample {
    public static void main(String[] args) {
        List<Integer> datas = Stream.of("1", "2", "3", "4", "5")
                .map(str -> Integer.parseInt(str))
                .collect(Collectors.toList());

        datas.forEach(data -> System.out.println("data: " + data + " class " + data.getClass()));
    }
}
