package com.moyu.example.multithreading.ch08;

/***
 *      描述:     隐式逸出(利用工厂模式来解决对象未初始化完成就发布的逸出情况)
 */
public class MultiThreadsErrorImplicit2 {

    int count ;

    private static EventListener listener = null;

    private MultiThreadsErrorImplicit2() {

        /***
         *  旧版直接在此处注册了监听, 导致在调用的时候对象还未初始化完成就被发布出去了,
         *  现在通过工厂方法, 如果还没有注册监听表示初始化为完成, 从而进行避免
         */
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n我获取的数是: " + count);
            }
        };

        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }

        count = 1000;
    }

    /***
     * 使用工厂来构建对象
     */
    public static MultiThreadsErrorImplicit2 getInstance(MySource mySource) {
        /***
         *  1. 将MultiThreadsErrorImplicit2构造方法设置为private修饰
         *  2. 构造方法中注册监听和其它逻辑都在一起, 我们进行拆分, 利用变量获取到EventListener
         *  3. 当我们在工厂方法中创建MultiThreadsErrorImplicit2对象, 就和普通对象一样, 从上到下按照流程执行
         *     完成初始化的工作, 不用担心中途被发发布出去
         *  4. 当MultiThreadsErrorImplicit2对象初始化完成后, 我们将监听注册上去, 就不会再出现count为0的现象。
         */

        MultiThreadsErrorImplicit2 safeObj =
                new MultiThreadsErrorImplicit2();
        // MultiThreadsErrorImplicit2对象初始化完成之后, 我们注册上监听对象, 在进行发布, 这个时候是安全的。
        mySource.registerListener(listener);
        return safeObj;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {});
        }).start();

        MultiThreadsErrorImplicit2 multiThreadsErrorImplicit = getInstance(mySource);
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
