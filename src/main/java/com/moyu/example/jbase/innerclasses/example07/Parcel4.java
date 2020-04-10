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
 * 多层嵌套类中访问外部类的成员
 */


/****
 * 可以看到, MNA.A.B中, 调用方法g()和f()不需要任何条件(即使被设置为private)。这个例子同时展示了
 * 如何从不同的类里创建多层嵌套的内部类对象的基本语法。"。new"语法能产生正确的作用域, 所以不必再调用构造器时限定类名。
 */
class MNA {
    private void f() {
        System.out.println("MNA f()");
    }

    class A {
        private void g() {
            System.out.println("MNA A g()");
        }

        public class B {
            void h() {
                System.out.println("MNA A B h()");
                g();
                f();
            }
        }
    }
}

public class Parcel4 {
    public static void main(String[] args) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();
    }
}
