package com.moyu.example.multithreading.juc.ch03;


import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 *
 *      描述:     读写锁, 读不可以插队
 */
public class ReadWriteLockExample3 {

    // 其实可以不用设置false, 默认就是非公平的, 这里只不过显示设置方便看的更清楚
    private static ReentrantReadWriteLock reentrantReadWriteLock =
            new ReentrantReadWriteLock(false);


    // 读锁
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    // 写锁
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    // 共享资源
    private static int amount = 0;


    public static void main(String[] args) {

        /***
         *
         *     注意一下这里的顺序, 按照我们的说法, 如果我们队列中的头结点不是读结点(Read)的话
         *     那么, 就不允许读锁线程插队的。
         *
         *     所以, 我们在大脑中先构思一下输出的结果
         *
         *          1. 首先Thread-A获取到写锁, 执行过程中, Thread-B, Thread-C, Thread-D, Thread-E分别启动
         *             但是发现Thread-A持有写锁, 纷纷进入等待队列中;
         *
         *          2. 当Thread-A线程释放写锁后, 紧接着Thread-B和Thread-C可以一起使用读锁, 而由于当前等待队列中的
         *             头结点是写锁, 所有Thread-E是无法插队执行的;
         *
         *          3. 当Thread-B和Thread-C释放读锁后, 紧接着Thread-D获取写锁;
         *
         *          4. 当Thread-D释放写锁后, 我们的读锁线程Thread-E才可以去执行读操作;
         *
         *     更具上面分析的流程, 我们来运行程序看看是不是和我们设想的结果一直呢?
         *         输出的顺序是:
         *              Thread-A -> Thread-B -> Thread-C -> Thread-D -> Thread-E
         *         还是输出:
         *              Thread-A -> Thread-B -> Thread-C -> Thread-E -> Thread-D
         *
         *     如果认为是第二种的输出结果的话, 请在参考对应文章内容.
         *
         */

        new Thread(writeTask(), "Thread-A").start();
        new Thread(readTask(), "Thread-B").start();
        new Thread(readTask(), "Thread-C").start();
        new Thread(writeTask(), "Thread-D").start();
        new Thread(readTask(), "Thread-E").start();
    }


    private static Runnable readTask() {
        return () -> {
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到读锁");
                int random = new Random().nextInt(4) + 1;
                System.out.println(Thread.currentThread().getName() + " 读取数据, 需要" + random + " 秒");
                Thread.sleep(random * 1000);
                System.out.println(Thread.currentThread().getName() + " 读取数据值为: " + amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        };
    }


    private static Runnable writeTask() {
        return () -> {
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到写锁");
                int random = new Random().nextInt(4) + 1;
                System.out.println(Thread.currentThread().getName() + " 更新数据, 需要" + random + " 秒");
                Thread.sleep(random * 1000);
                amount += 10;
                System.out.println(Thread.currentThread().getName() + " 更新数据完成...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        };
    }

}
