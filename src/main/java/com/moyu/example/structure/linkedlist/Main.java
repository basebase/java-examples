package com.moyu.example.structure.linkedlist;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        System.out.println(linkedList.getLaster());

        linkedList.set(linkedList.getSize() - 1, 1000);
        System.out.println(linkedList);

        linkedList.add(1, 999);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);

        System.out.println(linkedList.getLaster());
    }
}
