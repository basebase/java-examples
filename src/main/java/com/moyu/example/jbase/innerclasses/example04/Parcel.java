package com.moyu.example.jbase.innerclasses.example04;

/***
 * 使用.new创建一个内部类的实例
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

    public static void main(String[] args) {
        Parcel p = new Parcel();

        /***
         * 必须使用外部类实例创建内部类实例。
         * 在拥有外部类对象之前是不可能创建内部类对象的。这是因为内部类对象会暗暗的连接到创建它的外部类对象上。
         * 但是, 如果你创建的是嵌套类(静态内部类), 那么它就不需要对外部类对象的引用。
         */
        Contents c = p.new Contents();
        Destination d =
                p.new Destination("美食家, 老八!");
    }
}
