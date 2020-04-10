package com.moyu.example.jbase.innerclasses.example06;

/***
 * 内部类方法和作用域
 *   4. 一个匿名类, 它扩展了没有默认构造器的类
 */
public class Parcel4 {

    /****
     * [1]: 将核实的参数传递给基类的构造器
     * [2]: 在匿名内部类末尾的分号, 并不是用来标记此内部类结束的。它标记的是表达式的结束, 只不过这个表达式正巧包含了匿名内部类罢了。因此, 这与别的地方使用的分号是一致的。
     */

    public Wrapping wrapping(int x) {
        return new Wrapping(x) { // [1]
            @Override
            public int value() {
                return super.value() * 47;
            }
        }; // [2]
    }

    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Wrapping w = p.wrapping(10);
        System.out.println(w.value());
    }
}
