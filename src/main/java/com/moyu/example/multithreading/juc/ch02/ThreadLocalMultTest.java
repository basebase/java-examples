package com.moyu.example.multithreading.juc.ch02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *      描述:     一个线程中持有多个ThreadLocal变量
 */
public class ThreadLocalMultTest {
    private static ThreadLocal<Test1> test1ThreadLocal = ThreadLocal.withInitial(() -> {
        // 会被执行10次, 线程池中有10个线程
//        System.out.println("Test1 init...");
        return new Test1();
    });
    private static ThreadLocal<Test2> test2ThreadLocal = ThreadLocal.withInitial(() -> {
        // 会被执行10次, 线程池中有10个线程
//        System.out.println("Test2 init...");
        return new Test2();
    });


    private static ThreadLocal<Test3> test3ThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(10);
        for (int i = 0; i < 50; i++) {
            final String NAME = "FINAL-" + i;
            final Integer SCORE = i;
            final Integer AGE = i * 10;
            executorService.execute(() -> {

                /***
                 *      由于这里也不是传递参数什么的, 所以直接get到对象进行set赋值,
                 *      仅仅只是测试方便使用, 不推荐这样写
                 */

                // 调用remove()方法完全没有效果, 程序依旧照样运行
                test1ThreadLocal.remove();
                test2ThreadLocal.remove();

                Test1 t1 = test1ThreadLocal.get();
                Test2 t2 = test2ThreadLocal.get();


                t1.setName(NAME);
                t2.setScore(SCORE);


                Test3 t3 = new Test3();
                t3.setAge(AGE);
                test3ThreadLocal.set(t3);


                /****
                 *  // remove方法仅对set()的值有效果, 对于重写initialValue()的方法的对象没有效果
                 *
                 *  // 当我们调用remove方法时, 在使用get()就不会获取到对象了, 调用tmp3.xxx就会抛出空指针异常
                        test3ThreadLocal.remove();
                 *  // 如果要解决remove()方法的清除效果, 只需要在次重新赋值即可
                        t3.setAge(100);
                        test3ThreadLocal.set(t3);
                 *
                 */



                Test3 tmp3 = test3ThreadLocal.get();

                System.out.println("NAME : " + t1.getName() + " SCORE : " + t2.getScore() + " AGE : " + tmp3.getAge());
            });
        }

        executorService.shutdown();
    }
}

class Test1 {
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Test2 {
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

class Test3 {
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}