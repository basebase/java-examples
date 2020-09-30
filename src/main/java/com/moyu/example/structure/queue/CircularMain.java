package com.moyu.example.structure.queue;

public class CircularMain {
    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<>();
//        for (int i = 1; i < 10; i++) {
//            if (i % 4 == 0) {
//                queue.dequeue();
//            } else {
//                queue.enqueue(i);
//            }
//
//            System.out.println(queue);
//        }

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
