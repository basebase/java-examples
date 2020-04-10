package com.moyu.example.jbase.innerclasses.example08;

/****
 * 如果不需要解决"多重继承"的问题, 那么自然可以用别的方式编码, 而不需要使用内部类。
 * 但如果使用内部类, 还可以获得以下一些特性:
 *    1. 内部类可以有多个实例, 每个实例都有自己的状态信息, 并且与其外部类对象的信息相互独立
 *    2. 在单个外部类中, 可以让多个内部类以不同的方式实现一个接口, 或者继承同一个类。
 *    3. 创建内部类对象的时刻并不依赖外部类对象创建
 *    4. 内部类并没有令人迷惑的"is-a"关系, 他就是一个独立的实体。
 */

class D {}

abstract class E {}

class Z extends D {
    E makeE() {
        return new E() {};
    }
}

public class MultiImplementation {
    static void takesD(D d) {}
    static void takesE(E e) {}

    public static void main(String[] args) {
        Z z = new Z();
        takesD(z);
        takesE(z.makeE());
    }
}
