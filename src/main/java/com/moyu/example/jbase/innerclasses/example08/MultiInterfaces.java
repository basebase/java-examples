package com.moyu.example.jbase.innerclasses.example08;

/***
 * 为什么需要内部类
 *   假设我们必须在一个类中以某种方式实现两个接口。由于接口的灵活性, 有两种选择
 *     1. 使用单一类
 *     2. 使用内部类
 *
 *   但是, 我们的类完全可以使用者两个接口, 可以不使用内部类啊。
 *   但是, 如果我们不是接口呢? 而是抽象类或者是具体的类呢? 哪我们只能使用内部类才能实现多继承, 参考:MultiImplementation.java
 */

interface A {}
interface B {}

class X implements A, B {}
class Y implements A {
    B makeB() {
        return new B() {};
    }
}

public class MultiInterfaces {
    static void takesA(A a) {}
    static void takesB(B b) {}

    public static void main(String[] args) {
        X x = new X();
        Y y = new Y();

        takesA(x);
        takesA(y);

        takesB(x);
        takesB(y.makeB());
    }
}
