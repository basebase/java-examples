package com.moyu.example.jbase.innerclasses.example06;

/****
 * 内部类方法和作用域
 *   1. 一个定义在方法中的类。
 *
 *   展示在方法的作用域内(而不是在类的其他作用域内)创建一个完整的类。这被称为局部内部类
 */
public class Parcel1 {

    public Destination destination(String s) {

        /****
         * PDestination类是destination()方法的一部分, 而不是Parcel1的一部分。
         * 所以, 在destination()方法之外不能访问PDestination。
         *
         * 注意出现在return语句中的向上转型-返回的是Destination的引用, 它是PDestination的基类。
         * 当然, 在destination()方法中定义了内部类PDestination, 并不意味着一旦destination()方法执行完毕, PDestination就不可用了。
         */

        final class PDestination implements Destination {
            private String label;
            private PDestination(String whereTo) {
                label = whereTo;
            }
            public String readLabel() {
                return label;
            }
        }

        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        Destination d = p.destination("嘿嘿");
        System.out.println(d.readLabel());
    }
}
