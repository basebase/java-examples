package com.moyu.example.jbase.innerclasses.example06;

/****
 * 内部类方法和作用域
 *   2. 一个定义在作用域内的类, 此作用域在方法的内部
 *
 *   展示在任意的作用域内嵌入一个内部类
 */
public class Parcel2 {

    private void internalTracking(boolean b) {

        /***
         * TrackingSlip类被嵌入在if语句的作用域内, 这并不是说该类的创建是有条件的。
         * 它与别的类一起编译过了。
         */

        if (b) {
            class TrackingSlip {
                private String id;
                TrackingSlip(String s) {
                    id = s;
                }

                String getSlip() {
                    return id;
                }
            }

            TrackingSlip ts = new TrackingSlip("slip");
            String s = ts.getSlip();
        }

        // 不能在这里使用, 超出范围。
       //  TrackingSlip ts = new TrackingSlip();
    }

    public void track() {
        internalTracking(true);
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        p.track();
    }
}
