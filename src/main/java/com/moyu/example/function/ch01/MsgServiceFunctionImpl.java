package com.moyu.example.function.ch01;

/**
 * Created by Joker on 20/9/19.
 */
public class MsgServiceFunctionImpl {
    public static void main(String[] args) {
        MsgService msgService = msg -> System.out.println("小墨鱼3发送消息: " + msg);
        msgService.sendMsg("你好");
    }
}
