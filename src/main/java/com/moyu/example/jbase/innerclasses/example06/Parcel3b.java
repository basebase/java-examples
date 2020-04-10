package com.moyu.example.jbase.innerclasses.example06;

/****
 * 内部类方法和作用域
 *   3. 一个实现了接口的匿名类
 */
public class Parcel3b {

    class MyContents implements Contents {
        private int i = 11;
        public int value() {
            return i;
        }
    }

    public Contents contents() {
        return new MyContents();
    }


    public static void main(String[] args) {
        Parcel3b p = new Parcel3b();
        Contents c = p.contents();
    }
}
