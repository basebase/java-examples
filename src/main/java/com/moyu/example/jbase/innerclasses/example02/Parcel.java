package com.moyu.example.jbase.innerclasses.example02;


/****
 * 通过外部类方法返回一个指向内部类的引用
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


    // 返回内部类Destination引用
    public Destination to(String s) {
        return new Destination(s);
    }

    // 返回内部类Contents引用
    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel p = new Parcel();
        p.ship("测试");

        Parcel q = new Parcel();


        /****
         * 如果想从外部类的非静态方法之外的任意位置创建某个内部类对象, 那么必须如下方式
         * 具体的指明这个对象的类型: OuterClassName.InnerClassName
         */
        Contents c = q.contents();
        Destination d = q.to("嘿嘿, 来啦!");

        Destination d2 = q.to("老八.");

        System.out.println(d.readLabel());
        System.out.println(d2.readLabel());
    }

}
