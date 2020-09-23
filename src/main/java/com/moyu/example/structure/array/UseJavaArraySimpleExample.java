package com.moyu.example.structure.array;

/***
 *      使用Java数组
 */

public class UseJavaArraySimpleExample {
    public static void main(String[] args) {

        /***
         *      数组的三种创建方式:
         *          1. 创建数组并开辟连续的内存空间, 默认值为0, 等待赋值
         *          2. 创建数组并开辟连续的内存空间, 并赋值
         *          3. 声明并直接赋值，创建连续的内存空间
         */

        float[] moneys = new float[10];
        String[] students = new String[] {"", "", ""};
        int[] arrays = {1, 2, 3, 4, 5};

        for (int i = 0; i < moneys.length; i++) {
            moneys[i] = (i + 1) * 0.5f;
        }

        for (int i = 0; i < moneys.length; i++) {
            System.out.println(moneys[i]);
        }
    }
}
