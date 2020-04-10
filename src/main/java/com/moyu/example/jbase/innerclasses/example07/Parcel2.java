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
 * 接口的内部类:
 *   嵌套类可以作为接口的一部分。你放到接口中的任何类都自动的是public和static的。因为类是static的,
 *   只是将嵌套类置于接口的命名空间内, 这并不违反接口的规则。甚至可以在内部类中实现其外部类接口。
 */
public interface Parcel2 {
    void howdy();
    class Test implements Parcel2 {

        public void howdy() {
            System.out.println("Howdy!");
        }

        public static void main(String[] args) {
            new Test().howdy();
        }
    }
}
