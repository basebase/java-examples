package com.moyu.example.structure.array;

/**
 *      测试Array类
 */
public class Main {
    public static void main(String[] args) {
        Array array = new Array(20);
        // 赋值10个元素
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }

        System.out.println(array);

        // 指定位置添加元素
        array.add(2, 200);
        System.out.println(array);

        array.add(5, 666);
        System.out.println(array);

        // 头部添加元素
        array.addFirst(999);
        System.out.println(array);

        System.out.println(array.get(12));


        array.set(12, 985);
        System.out.println(array);
    }
}
