package com.moyu.example.multithreading.juc.ch04;

import java.util.concurrent.atomic.AtomicReference;

/***
 *      描述:     原子引用例子使用
 */
public class AtomicRefExample {

    private AtomicReference<Student> s = new AtomicReference<Student>();

    public static void main(String[] args) throws InterruptedException {
        Student s1 = new Student("A1", 11);
        Student s2 = new Student("A2", 22);
        Student s3 = new Student("A3", 33);
        Student s4 = new Student("A4", 44);
        Student s5 = new Student("A5", 55);

        AtomicRefExample atomicRefExample = new AtomicRefExample();

        Thread t1 = new Thread(atomicRefExample.task(null, s1), "Thread-A");
        Thread t2 = new Thread(atomicRefExample.task(s1, s2), "Thread-B");
        Thread t3 = new Thread(atomicRefExample.task(s2, s3), "Thread-C");
        Thread t4 = new Thread(atomicRefExample.task(s3, s4), "Thread-D");
        Thread t5 = new Thread(atomicRefExample.task(s4, s5), "Thread-E");


        /***
         *
         *      注意:
         *            在创建AtomicReference原子引用类的时候, 默认值就是null, 如果不给初始值的话
         *            当我们不启动t1.start()的时候, 其它线程的当前值都不为null, 所以原子引用对象的值就会一直为null
         *            后面程序会抛出空指针异常。
         *
         */

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }

    public Runnable task(Student o, Student n) {
        return () -> {

            /***
             *
             *      这里可以直接调用也可以使用循环, 使用循环或许在后面想要的值来了就会更新了
             */

            s.compareAndSet(o, n);
//            while (!s.compareAndSet(o, n)) {
//                System.out.println(Thread.currentThread().getName() + " 快速更新中...");
//            }

//            System.out.println("出错了...");

            System.out.println(Thread.currentThread().getName() +
                    " 更新后引用原子类数据为: [" + s.get().name + ", " + s.get().age + " ]" );
        };
    }
}


class Student {
    String name;
    Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
