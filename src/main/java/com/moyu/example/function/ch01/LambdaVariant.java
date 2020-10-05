package com.moyu.example.function.ch01;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 *      描述:    如何辨别Lambda表达式
 */
public class LambdaVariant {
    public static void main(String[] args) {


        // 第一种, 没有参数的话需要使用()表示没有参数
        Runnable runnable = () -> System.out.println("Hello");

        // 第二种写法: 有一个参数可以省略我们的括号(), 直接把参数写上
        ActionListener listener = evnet -> System.out.println("button click" + evnet.toString());

        // 第三种写法: 要做多个动作, i++, 抛出
        Runnable runnable1 = () -> {
            //
            System.out.println("dadasd");
            int i = 0;
            i++;
            throw new IllegalArgumentException("参数值出错了...");
        };

        // 第四种写法: add是一个方法
        BinaryOperator<Long> add = (x, y) -> x + y;
        Long result = add.apply(1L, 2L);
        System.out.println("reslut: " + result);

        // 第五种写法
        BinaryOperator<Long> addExp = (Long x, Long y) -> x + y;
    }
}
