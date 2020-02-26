package com.moyu.example.threads.example01;

/**
 * Created by Joker on 20/2/24.
 */
public class AccountThread {

    Account account ;
    public AccountThread(Account account) {
        this.account = account;
    }



    public static void main(String[] args) throws InterruptedException {
        Account account = new Account("moyu", 1f);

        for (int i = 1 ; i <= 10; i ++) {
            new Thread(() -> {
                account.deposit(1);
            }, "T" + i).start();
        }

        for (int i = 1 ; i <= 10; i ++) {
            new Thread(() -> {
                account.withdraw(1);
            }, "G" + i).start();
        }
    }
}
