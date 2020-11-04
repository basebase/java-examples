package com.moyu.example.structure.recursion;

/***
 *   第一个递归例子:  累加数组之和
 *         了解递归结构, 如何阅读一个递归程序
 */
public class CalculatingNumbersSum {

    public int add(Integer[] numbers) {
        return add(numbers, 0);
    }

    private int add(Integer[] numbers, int index) {
        if (numbers.length == index)
            return 0;       // 问题不可以在分解, 已经是最小的子任务

        /**
         * 这里的add方法在调用自己, 并将index位置移动, 使整个数组缩小
         * 就会形成将原有的问题分解为更小的子问题
         *
         *      numbers[0] + add(numbers, 0 + 1)
         *      numbers[1] + add(numbers, 1 + 1)
         *      numbers[2] + add(numbers, 2 + 1)
         *      numbers[3] + add(numbers, 3 + 1)
         *      numbers[4] + add(numbers, 4 + 1)
         *      numbers[5] // 问题不可以在分解, 此时退出
         *
         */
        int res = numbers[index] + add(numbers, index + 1);     // 更好的观察递归程序, 使用变量来接收
        return res;
    }

    public static void main(String[] args) {
        Integer[] numbers = {1, 3, 5, 7, 9};
        System.out.println(new CalculatingNumbersSum().add(numbers));
    }
}
