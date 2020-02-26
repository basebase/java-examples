package com.moyu.example.threads.example01;

/**
 * Created by Joker on 20/2/24.
 */
public class Account {

    String holderName;
    float amount;

    public Account(String name, float amt) {
        this.holderName = name;
        this.amount = amt;
    }

    // 如果不加sync的话, 多个线程同时修改会出问题

    public //synchronized
    void deposit(float amt) {
        amount += amt;
        System.out.println("Curr Thread Name : " + Thread.currentThread().getName() + " And deposit amount Value : " + amount);
    }

    public //synchronized
    void withdraw(float amt) {
        amount -= amt;
        System.out.println("Curr Thread Name : " + Thread.currentThread().getName() + " And withdraw amount Value : " + amount);
    }

    public  float checkBalance() {
        return amount;
    }
}
