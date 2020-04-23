package com.moyu.example.multithreading.ch03;

/***
 *      描述:     try/catch捕获InterruptedException后
 *               优先选择: 在方法签名中抛出异常, 那么在run()方法就会强制try/catch
 */
public class RightWayStopThreadInProd {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(getRunnable());
        t1.start();

        // 休眠1s后, t1线程休眠2s, 这样就能触发阻塞异常
        Thread.sleep(1000);
        t1.interrupt();

    }

    public static Runnable getRunnable() {
        return () -> {
            int num = 0;
            while (num < 10000) {
                System.out.println("当前值为: " + num);
                num ++;

                // ① 调用异常方法, 但是该方法自行处理了异常, 并没有抛出任何异常信息到run方法, 而是吞掉了异常信息 (不推荐)
//                throwInMethod1();


                // ② 推荐在方法签名上抛出异常信息, 这样run()方法可以捕获到异常,
                //    注意run()方法里面只能使用try/catch, 不能使用throws, 这是因为其父类方法并没有任何异常抛出。 (推荐)
                try {
                    throwInMethod2();
                } catch (InterruptedException e) {
                    // 响应中断信息
                    // 保存日志, 记录状态等等
                    System.out.println("当前状态已记录...");
                    e.printStackTrace();
                }
            }
        };
    }

    /***
     *      直接try/catch异常后, 并不做任何处理。直接吞掉异常信息。(不推荐)
     */
    private static void throwInMethod1() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * 在方法签名中抛出异常 。(推荐)
     * @throws InterruptedException
     */
    private static void throwInMethod2() throws InterruptedException {
        Thread.sleep(2000);
    }
}