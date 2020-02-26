package com.moyu.example.threads.example01;

import java.util.Date;

/**
 * Created by Joker on 20/2/24.
 */
public class TimePrinter extends Thread {
    int pauseTime;
    String name;

    public TimePrinter(int x, String n) {
        this.pauseTime = x;
        this.name = n;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(name + " : " + new Date(System.currentTimeMillis()));
                Thread.sleep(pauseTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TimePrinter tp1 = new TimePrinter(1000, "Fast Guy");
        tp1.start();

        TimePrinter tp2 = new TimePrinter(3000, "Slow Guy");
        tp2.start();
    }
}
