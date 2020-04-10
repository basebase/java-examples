package com.moyu.example.jbase.innerclasses.example04;

/****
 * 使用.this和.new
 *   如果需要生成对外部类对象的引用, 可以使用外部类的名字后面紧跟.this
 *   这样产生的引用自动的具有正确的类型, 这一点在编译期就被知晓并受到检查。
 */
public class DotThis {
    void f () {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        public DotThis outer() {
            return DotThis.this;
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        Inner dti = dt.inner();
        dti.outer().f();
    }
}
