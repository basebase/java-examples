package com.moyu.example.jbase.innerclasses.example01;


/****
 * 创建一个内部类.
 *  和普通类一样, 只不过内部类的名字是嵌套在Parcel中的。
 */

public class Parcel {

    class Contents {
        private int i = 11;
        public int value() {
            return i;
        }
    }


    class Destination {
        private String label;
        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }

    /***
     * 当在ship()方法中使用内部类的时候, 与使用普通类没什么不同。
     * 在这里, 明显的区别只是内部类的名字是嵌套在Parcel中的。
     * @param dest
     */
    public void ship(String dest) {
        Contents c = new Contents();
        Destination d = new Destination(dest);
        System.out.print(d.readLabel());
    }


    public static void main(String[] args) {
        Parcel p = new Parcel();
        p.ship("测试.");
    }
}
