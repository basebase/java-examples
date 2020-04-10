package com.moyu.example.jbase.innerclasses.example06;

/****
 * 内部类方法和作用域
 *   3. 一个实现了接口的匿名类
 */
public class Parcel3 {

    /****
     * contents()方法将返回值的生成与表示这个返回值的类的定义结合在一起。另外, 这个类是"匿名的"
     * 它没有名字。更糟糕的是, 看起来似乎是你正要创建一个Contents对象。但是(在到达语句借宿的分号之前)却说: "等一等, 我想在这里插入一个类的定义"。
     *
     * 这种奇怪的语法指的是: "创建一个继承自Contents的匿名类对象"。通过new表达式返回的引用被自动向上转型为对Contents的引用。
     * 匿名内部的语法对应Parcel3b.java的写法
     */
    public Contents contents() {
        return new Contents() {
            private int i = 11;
            public int value() {
                return i;
            }
        }; // 分号结尾。
    }

    public static void main(String[] args) {
        Parcel3 p = new Parcel3();
        Contents c = p.contents();
        System.out.println(c.value());
    }
}
