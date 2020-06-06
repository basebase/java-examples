package com.moyu.example.multithreading.ch08;

import java.util.HashMap;
import java.util.Map;

/***
 *      描述:    对象的发布与逸出(副本解决private对象逸出)
 */
public class MultiThreadsErrorStates2 {
    public static void main(String[] args) {

        MultiThreadsErrorStatesInner statesInner
                = new MultiThreadsErrorStatesInner();



        new Thread(() -> {
            System.out.println(statesInner.getStatesImproved().get("K1"));
        }).start();

        new Thread(() -> {
            statesInner.getStatesImproved().remove("K1");
        }).start();

        new Thread(() -> {
            System.out.println(statesInner.getStatesImproved().get("K1"));
        }).start();

    }


    static class MultiThreadsErrorStatesInner {
        private Map<String, String> states ;


        public MultiThreadsErrorStatesInner() {
            this.states = new HashMap<>();
            states.put("K1", "赛车手");
            states.put("A1", "中门对狙");
            states.put("C4", "炸弹爆炸");
            states.put("F4", "东北F4");
        }

        public Map<String, String> getStates() {
            return states;
        }


        /***
         *  通过构造一个副本返回一个新的对象, 这样原有的对象不会被修改,
         *  每个线程都是获取一个新的对象, 无论是删除还是修改, 丝毫不影响原有的对象
         */
        public Map<String, String> getStatesImproved() {
            return new HashMap<>(states);
        }
    }
}


