package com.moyu.example.threads.example01;

import java.util.Date;

/**
 * Created by Joker on 20/2/24.
 */
public class TimePrinterRunnable implements Runnable {
    int pauseTime;
    String name;

    public TimePrinterRunnable(int x, String n) {
        this.pauseTime = x;
        this.name = n;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(name + ":" + new
                        Date(System.currentTimeMillis()));
                Thread.sleep(pauseTime);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread (new TimePrinter(1000, "Fast Guy"));
        t1.start();

        Thread t2 = new Thread (new TimePrinter(3000, "Slow Guy"));
        t2.start();
    }


}
