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
 *   嵌套类和普通的内部类还有一个区别。普通内部类的字段与方法, 只能放在类的外部层次上, 所以普通的内部类不能有static数据和static字段。
 *   也不能包含嵌套类。但是嵌套类可以包含这些。
 */
public class Parcel1 {
    private static class ParcelContents implements Contents {
        private int i = 11;
        public int value() {
            return i;
        }
    }

    protected static final class ParcelDestination implements Destination {
        private String label;

        private ParcelDestination(String whereTo) {
            label = whereTo;
        }

        public String readLabel() {
            return label;
        }

        // 静态类可以包含其它静态元素
        public static void f() {}
        static int x = 10;
        static class AnotherLevel {
            public static void f() {}
            static int x = 10;
        }
    }

    public static Destination destination(String s) {
        return new ParcelDestination(s);
    }

    public static Contents contents() {
        return new ParcelContents();
    }

    public static void main(String[] args) {

        /***
         * main()方法中, Parcel1对象不是必须的, 而是使用static成员的普通方法来调用并返回Contents和Destination的引用。
         */

        Contents c = contents();
        Destination d = destination("XYZ");
        System.out.println(d.readLabel());
    }
}
