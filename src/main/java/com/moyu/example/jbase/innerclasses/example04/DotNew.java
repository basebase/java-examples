package com.moyu.example.jbase.innerclasses.example04;

/***
 * 创建某个内部类的对象
 *   要实现的话需要在new表达式中提供对其外部类对象的引用
 */
public class DotNew {
    public class Inner {}

    public static void main(String[] args) {
        DotNew dn = new DotNew();

        /****
         * 想要直接创建内部类的对象, 不能按照自己想象的方式, 去引用外部类的名字DotNew
         * 而是必须使用外部类的对象来创建该内部类对象, 就像下面创建方式一样。
         */
        Inner dni = dn.new Inner();

        System.out.println("dni = " + dni);
    }
}
