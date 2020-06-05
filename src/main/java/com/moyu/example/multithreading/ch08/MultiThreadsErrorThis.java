package com.moyu.example.multithreading.ch08;

/***
 *      描述:     初始化未完成, 进行this赋值
 */
public class MultiThreadsErrorThis {
    static Point point = null;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            try {
                new Point(1, 2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        /***
         *      我们通过子线程去创建Point对象, 如果在主线程中等待的时间不一致获取到的对象值也不一样
         *      正确数据对象应该是: 在对象不进行更新修改, 我们看到的数据始终一致。
         *
         *      所以, 在初始化未完成的情况进行this赋值会造成this引用逸出。
         */

        // 如果等待50毫秒的话, 可能输出就是1, 0或者其他
//        Thread.sleep(50);

        // 但是如果我们等待时间比较长的话, 输出是1, 2这样的值
        Thread.sleep(120);
        System.out.println(point);
    }
}

class Point {
    private final int x, y;

    Point(int x, int y) throws InterruptedException {
        this.x = x;

        /**
         *     在中间过程进行this赋值操作
         */
        MultiThreadsErrorThis.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
