package com.moyu.example.multithreading.ch08;

import java.util.HashMap;
import java.util.Map;

/***
 *      描述:     构造函数中新建线程
 */
public class MultiThreadsErrorConstruction {

    private Map<String, String> states ;

    public MultiThreadsErrorConstruction() {

        /***
         *      当在构造函数中使用线程可能会导致取不到数据值, 当程序执行完start()方法时
         *      我们的构造函数就结束了, 而线程中的配置数据什么时候写入我们并不清楚
         *
         *      当子线程在没写入之前我们调用可能会造成空指针异常, 如果等待子线程写入后在取就不会有此问题。
         *
         *      这种根据时间等待获取是极其不稳定的, 也是不安全的。
         *
         *      但是, 我们可能在平时开发中也会在构造程序中"隐式"的用到线程, 比如数据库连接池可能底层源码会使用多线程的方式去创建。
         */

        new Thread(() -> {
            this.states = new HashMap<>();
            states.put("K1", "赛车手");
            states.put("A1", "中门对狙");
            states.put("C4", "炸弹爆炸");
            states.put("F4", "东北F4");
        }).start();
    }

    public static void main(String[] args) {
        MultiThreadsErrorConstruction multiThreadsErrorConstruction =
                new MultiThreadsErrorConstruction();
        System.out.println(multiThreadsErrorConstruction.states.get("F4"));
    }
}
