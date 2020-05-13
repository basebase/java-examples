package com.moyu.example.multithreading.ch04;

/***
 *      描述:     展示线程    NEW -> RUNNABLE -> TERMINATED状态
 */
public class NewRunnableTerminated {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(getRunnable());

        // 在还没有调用start()方法的时候, 线程状态一定是NEW
        System.out.println("还没调用start()方法时候的状态: " + t.getState());

        /***
         *     注意:  这里可能在调用start()方法后, 线程可能会立即获取到CPU执行
         *           如果输出在线程中间, 多运行几遍
         *
         *           不过, 即使在等待被执行或者已在执行中其状态都是RUNNABLE, 不用过于纠结。
         */
        // 调用start()方法, 可能当前线程不会被立即执行, 但是这个时候也会是RUNNABLE而不会出现我们文章说的ready和running两种状态
        t.start();
        System.out.println("调用start()线程还未执行的状态: " + t.getState());

        // 休眠一些时间, 让线程t有机会执行, 执行过程也是RUNNABLE状态
        Thread.sleep(10);

        System.out.println("线程执行时候的状态: " + t.getState());

        // 我们等待线程t运行完成后, 在输出线程状态
        Thread.sleep(100);
        System.out.println("线程结束状态: " + t.getState());

    }

    public static Runnable getRunnable() {
        return () -> {
            for (int i = 0; i < 1000; i ++) {
                System.out.println("当前值为: " + i + " 当前线程 " + Thread.currentThread().getName() + " 状态: " + Thread.currentThread().getState());
            }

        };
    }
}
