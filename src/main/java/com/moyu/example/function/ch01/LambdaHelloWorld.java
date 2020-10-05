package com.moyu.example.function.ch01;

/**
 * Created by Joker on 20/8/23.
 */
public class LambdaHelloWorld {



    public static void main(String[] args) {
        /***
         *      JDK8之前使用匿名内部类创建多线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World.");
            }
        }).start();

        /**
         *      JDK8 Lambda表达式创建线程
         *          (params) -> { //... }
         */
        new Thread(() -> {
            System.out.println("Hello Lambda.");
        }).start();
    }
}
