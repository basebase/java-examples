package com.moyu.example.structure.array;

/**
 *      测试Array类
 */
public class Main {
    public static void main(String[] args) {
        Array<Integer> array = new Array();
        // 赋值10个元素
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }

        System.out.println(array);

        // 指定位置添加元素
        array.add(2, 200);
        System.out.println(array);

        // 头部添加元素
        array.addFirst(999);
        System.out.println(array);


        array.remove(1);
        System.out.println(array);

        array.removeLast();
        System.out.println(array);
    }
}
