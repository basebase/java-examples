package com.moyu.example.jbase.innerclasses.example06;

/***
 * 内部类方法和作用域
 *   6. 一个匿名类, 它通过实例初始化实现构造(匿名内部类不可能有构造器)
 *
 */
public class Parcel6 {

    public Destination destination(final String dest, final float price) {

        /***
         * 在实例初始化操作的内部, 可以看到有一段代码, 他们不能作为字段初始化动作的一部分来执行(就是if语句)。
         * 所以对于匿名类而言, 实例初始化的实际效果就是构造器。当然它受到了限制, 你不能重载实例初始化方法, 所以仅有一个这样的构造器
         */
        return new Destination() {
            private int cost;

            {
                cost = Math.round(price);
                if (cost > 100)
                    System.out.println("Over budget");
            }

            private String label = dest;

            public String readLabel() {
                return label;
            }

        };
    }

    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        p.destination("哥哥", 101.395F);
    }
}
