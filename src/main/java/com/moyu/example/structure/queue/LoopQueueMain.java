package com.moyu.example.structure.queue;

/**
 * Created by Joker on 20/10/5.
 */
public class LoopQueueMain {
    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();

        for (int i = 0; i < 19; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }

        queue.enqueue(19);
        System.out.println(queue);

        queue.dequeue();
        System.out.println(queue);

        queue.enqueue(20);
        System.out.println(queue);
    }
}
