package com.moyu.example.structure.queue;

public class LinkedListQueueMain {
    public static void main(String[] args) {
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
//        for (int i = 0; i < 5; i++) {
//            linkedListQueue.enqueue(i);
//            System.out.println(linkedListQueue);
//        }

        System.out.println("==============dequeue==============");

        for (int i = 0; i < 5; i++) {
            linkedListQueue.dequeue();
            System.out.println(linkedListQueue);
        }

    }
}