package com.moyu.example.multithreading.juc.ch02;

/***
 *      描述:     使用ThreadLocal抛出空指针异常?
 */

public class ThreadLocalNPE {

    ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public void set() {
        threadLocal.set(Thread.currentThread().getId());
    }

    /**
     *      如果调用此方法, 由于返回是long, 而ThreadLocal是Long类型, 所以会拆箱
     *      但由于没有重写相关方法或者set值, 返回是一个null, 在拆箱的过程中会发生空指针异常。
     */
    public long get() {
        return threadLocal.get();
    }

    public Long get2() {
        return threadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();



        new Thread(() -> {
            threadLocalNPE.set();
            System.out.println(threadLocalNPE.get());
        }, "Thread-A").start();

        System.out.println(threadLocalNPE.get2());

        /***
         *      当运行到这段代码的时候, 就抛出了java.lang.NullPointerException空指针异常了呢?
         *      上面两端代码都没有任何异常信息?
         *
         *      1. 子线程Thread-A先进行了set然后获取到对应的线程ID值, 所以不会抛出异常
         *      2. 至于主线程调用get2()方法没有抛出空指针异常这是因为, 我们ThreadLocal的泛型写的是Long类型,
         *         在没有重写initialValue初始值就是null进行打印输出, 而我们get()方法则是返回long这个关键字
         *         由于ThreadLocal是Long类型, 就会进行拆箱, 由于没有重写方法在进行拆箱的时候抛出了空指针异常。
         *
         *      所以, 并不是说get之前必须set, 否则可能抛出异常。这句话不是正确的。而是装箱拆箱导致的问题。
         */
        System.out.println(threadLocalNPE.get());
    }
}
