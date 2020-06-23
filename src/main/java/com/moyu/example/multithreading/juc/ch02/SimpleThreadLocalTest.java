package com.moyu.example.multithreading.juc.ch02;

/***
 *      描述:     用于debug源码分析
 */
public class SimpleThreadLocalTest {
    private static ThreadLocal<City> threadLocal = new ThreadLocal<City>(){
        @Override
        protected City initialValue() {
            return new City();
        }
    };

    private static ThreadLocal<Province> provinceThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            City c1 = threadLocal.get();
            System.out.println(c1);

        }, "Thread-A");
        t1.start();
//
//        Thread.sleep(1000);




//        Thread t2 = new Thread(() -> {
//            Province p = new Province();
//            p.setName("西藏");
//
//            Province p1 = provinceThreadLocal.get();
//            System.out.println(p1);
//
//            provinceThreadLocal.set(p);
//            Province p2 = provinceThreadLocal.get();
//            System.out.println(p2.getName());
//        }, "Thread-B");
//
//        t2.start();

    }
}


class City {
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Province {
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
