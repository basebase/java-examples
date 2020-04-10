package com.moyu.example.jbase.innerclasses.example06;

/***
 * 内部类方法和作用域
 *   5. 一个匿名类, 它执行字段初始化
 *
 */
public class Parcel5 {

    /***
     * 匿名内部类使用参数必须是最终的, 所以必须使用final
     *
     * 如果定义一个匿名内部类, 并希望它使用一个在其外部定义的对象, 那么编译器会要求其参数引用是final(也就是初始化后不会改变)
     */
    public Destination destination(final String dest) {
        return new Destination() {
            private String label = dest;
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("GG");
        System.out.println(d.readLabel());
    }
}
