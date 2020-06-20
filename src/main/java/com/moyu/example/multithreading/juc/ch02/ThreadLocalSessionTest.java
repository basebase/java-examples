package com.moyu.example.multithreading.juc.ch02;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 *      描述:     通过ThreadLocal解决传递用户Session信息
 */
public class ThreadLocalSessionTest {

    /***
     *      这里, 我们的threadlocal不在实现任何方法, 但是可以看到下面使用的threadlocal的set方法来设置对象信息。
     */
    public static ThreadLocal<Session> threadLocal = new ThreadLocal();
    public static HashSet<String> hashSet = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService =
                Executors.newFixedThreadPool(10);

        /***
         *      每一个线程都是一次请求, 我们将用户信息传递给Server1
         */
        for (int i = 0; i < 100; i++) {
            final String USER_NAME = "USER-" + i;
            executorService.execute( () -> {
                new Service1().process(USER_NAME);
            });
        }

        executorService.shutdown();

        /***
         *  这里等待线程池中的线程执行完, 如果set中有一条信息, 就表示出现了线程安全问题。
         */
        Thread.sleep(1000);
        if(hashSet.size() > 0)
            throw new IllegalArgumentException("线程不安全啦, 快跑啊!!!");

    }
}

/***
 *      service1服务生产出用户session信息, 保存到threadlocal中, 不用传递参数到service2和service3
 *      而是通过读取threadlocal.get()方法来获取session信息
 */

class Service1 {
    public void process(String userName) {
        Session session = new Session(userName);

        /***
         *      这里我们将每次请求过来的用户信息保存, 然后通过ThreadLocal的set方法将session信息塞入
         */

        ThreadLocalSessionTest.threadLocal.set(session);

        // 调用service2来处理其它业务逻辑
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        // service2可以做一些特有的处理
        // ....


        /***
         *      由于server1方法已经把用户session信息写入到了threadlocal中,
         *      我们只需要通过get()方法来获取对应的信息即可。
         */
        Session session = ThreadLocalSessionTest.threadLocal.get();
        System.out.println("Service2 Session Info : " + session.name);

        ThreadLocalSessionTest.hashSet.add(session.name);

        // 调用service3
        new Service3().process();
    }
}

class Service3 {
    public void process() {
        // ... 做一些特有的处理

        Session session = ThreadLocalSessionTest.threadLocal.get();
        System.out.println("Service3 Session Info : " + session.name);

        ThreadLocalSessionTest.hashSet.remove(session.name);
    }
}

class Session {
    String name ;
    public Session(String name) {
        this.name = name;
    }
}