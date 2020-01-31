package com.moyu.example.structure.queue;

/**
 * Created by Joker on 19/9/14.
 */
public class QueueTest {
    public static void main(String[] args) {
//        Queue<Integer> queue = new ArrayQueue<>();
//        for (int i = 0; i < 10; i ++) {
//            queue.enqueue(i);
//            System.out.println(queue);
//
//            if (i % 3 == 2) {
//                queue.dequeue();
//                System.out.println(queue);
//            }
//        }
//
//        System.out.println(queue.getFront());

        LoopQueue<Integer> loopQueue = new LoopQueue();
        for (int i = 0; i < 10; i ++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue);

            if (i % 3 == 2) {
                loopQueue.dequeue();
                System.out.println(loopQueue);
            }
        }
    }
}
