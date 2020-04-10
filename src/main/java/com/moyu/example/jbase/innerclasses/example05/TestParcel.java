package com.moyu.example.jbase.innerclasses.example05;

/****
 * 在Parcel中, 内部类PContents是private的, 所以除了Parcel没有人可以访问它。
 * 普通(非内部)类的访问权限不能设为private或者protected;
 * 他们只能设置为public或者Package访问权限。
 *
 *
 * PDestination是protected的, 所以只有Parcel及其子类还有与Parcel同一个包中的类(因为protected也给予了包访问权限)能访问PDestination,
 * 其它类都不能访问PDestination, 这意味着, 如果客户端程序员想了解或者访问这些成员, 是有限制的。
 *
 *
 */
class Parcel {
    private class PContents implements Contents {
        private int i = 11;
        public int value() {
            return i;
        }
    }

    protected final class PDestination implements Destination {
        private String label;
        private PDestination(String whereTo) {
            label = whereTo;
        }
        public String readLabel() {
            return label;
        }
    }

    public Destination destination(String s) {
        return new PDestination(s);
    }

    public Contents contents() {
        return new PContents();
    }
}

public class TestParcel {
    public static void main(String[] args) {
        Parcel p = new Parcel();
        Contents c = p.contents();
        Destination d = p.destination("老八吃粑粑.");
        System.out.println(d.readLabel());
    }
}