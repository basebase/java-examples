package com.moyu.example.jbase.innerclasses.example07;

/****
 * 嵌套类
 *   如果不需要内部类对象与其外部类对象之间有联系, 那么可以将内部类声明为static,
 *   通常称为"嵌套类"。
 *
 *   想要理解static应用于内部类时的含义, 就必须要记住, 普通的内部类对象隐式的保存了一个引用,
 *   指向创建它的外部类对象。然而, 当内部类是static时, 就不是这样了。
 *
 *   嵌套类:
 *     1. 要创建嵌套类的对象, 并不需要其外部类的对象。
 *     2. 不能从嵌套类的对象中访问非静态的外部类对象。
 *
 * 利用嵌套类来实现代码测试
 */
public class Parcel3 {
    public void f() {
        System.out.println("f()");
    }

    /***
     * 不用再额外写一个java文件对其进行测试, 直接通过嵌套类的方式进行调用。
     */
    public static class Tester {
        public static void main(String[] args) {
            Parcel3 p = new Parcel3();
            p.f();
        }
    }
}
