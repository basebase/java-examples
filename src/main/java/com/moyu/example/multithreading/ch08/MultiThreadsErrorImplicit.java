package com.moyu.example.multithreading.ch08;

/***
 *      描述:     隐式逸出
 */
public class MultiThreadsErrorImplicit {

    int count ;

    public MultiThreadsErrorImplicit(MySource source) {

        /***
         *     1. 首先在构造函数中注册我们的事件监听, 子线程会触发此操作
         *     2. 由于我们的匿名内部类会隐式的包含外部对象(即:MultiThreadsErrorImplicit对象)的this引用,
         *        我们获取count不需要去构造对象, 直接count即可
         *     3. 注册监听事件后, 模拟构造函数还要完成其它工作等...
         */
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n我获取的数是: " + count);
            }
        });

        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }

        count = 1000;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {

            /***
             *  当我们的子线程去触发监听事件后, 主要是根据当前等待时间才能知道count输出的值,
             *  如果等待构造函数全部初始化完成后, 则count输出是1000, 否则count为0
             *
             *  这就会导致, 如果在构造函数未初始化完成的状态, 我们就把对象发布出去了, 导致对象逸出.
             */

            try {
                Thread.sleep(10);
                // 如果等待时间较长, 构造函数后面的逻辑已经完成
//                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mySource.eventCome(new Event() {});
        }).start();

        MultiThreadsErrorImplicit multiThreadsErrorImplicit =
                new MultiThreadsErrorImplicit(mySource);
    }


    static class MySource {
        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(Event e) {
            if (listener != null)
                listener.onEvent(e);
            else
                System.out.println("还未初始化完成...");
        }
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {}
}
