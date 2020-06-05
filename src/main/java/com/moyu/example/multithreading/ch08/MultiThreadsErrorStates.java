package com.moyu.example.multithreading.ch08;

import java.util.HashMap;
import java.util.Map;

/***
 *      描述:    对象的发布与逸出
 */
public class MultiThreadsErrorStates {
    public static void main(String[] args) {
        MultiThreadsErrorStatesInner statesInner =
                new MultiThreadsErrorStatesInner();

        Map<String, String> states = statesInner.getStates();

        /***
         *  假设现在有两个线程执行先后顺序我们不确定, 它们就有能力去修改这个不应该被发布的map对象
         *  甚至还可以修改map中存储的对象数据, 以及删除数据。 导致数据不一致等
         */

        new Thread(() -> {
            System.out.println(states.get("K1"));
        }).start();


        new Thread(() -> {
            states.remove("K1");
        }).start();
    }
}

class MultiThreadsErrorStatesInner {
    // 当前设置一个私有变量map作为我们的配置信息项
    private Map<String, String> states ;

    /**
     * 通过构造方法初始化我们的配置项数据
     */
    public MultiThreadsErrorStatesInner() {
        this.states = new HashMap<>();
        states.put("K1", "赛车手");
        states.put("A1", "中门对狙");
        states.put("C4", "炸弹爆炸");
        states.put("F4", "东北F4");
    }

    /**
     * 但是, 我们在一个public修饰的方法中把我们私有变量发布出去了, 可能导致其它类获取到
     */
    public Map<String, String> getStates() {
        return states;
    }
}
