package com.moyu.example.structure.linkedlist;

/**
 * Created by Joker on 19/9/16.
 */
public class LinkedList<E> {

    private class Node {
        private E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private int size;
    private Node head;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    // 获取链表元素个数
    public int getSize() {
        return size;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }


    // 在链表头添加新的元素
    public void addFirst(E e) {


//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        // 这一句话等价上面三句
        head = new Node(e, head);

        size++;
    }

    // 在链表末尾添加元素
    public void addLast(E e) {
        add(size, e);
    }

    // 在链表的index(0-based)位置添加元素
    public void add(int index, E e) {
        if (index > size || index < 0) {
            throw new IllegalArgumentException("添加失败, 请输入正确的索引位置");
        }

        if (index == 0) {
            addFirst(e);
        } else {
            Node prev = head;
            for (int  i = 0 ; i < index - 1; i ++) {
                prev = prev.next;
            }

//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;

            prev.next = new Node(e, prev.next);
            size ++;
        }
    }
}
