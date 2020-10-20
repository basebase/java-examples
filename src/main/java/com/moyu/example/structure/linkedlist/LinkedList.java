package com.moyu.example.structure.linkedlist;

/***
 * 实现链表数据结构
 * @param <E>
 */
public class LinkedList<E> {

    /**
     * Node属于链表内部结构, 不需要对外开放避免外部创建节点破坏结构
     * @param <E>
     */
    private class Node<E> {
        public E e; // 存放的数据
        public Node next; // 下一个节点的引用

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

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        this.size = 0;
    }

    /**
     * 获取链表元素个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断链表是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素到头结点
     * @param e
     */
    public void addFirst(E e) {
//        Node node = new Node(e);      // 创建一个新的待添加节点
//        node.next = head;             // 创建出来的节点指向当前的头结点
//        head = node;                  // 当前的头结点更新为新添加的节点

        // 上面三句等价于下面这一句
//        head = new Node(e, head);
        add(0, e);
//        size ++;                       // 维护当前节点数量
    }

    /**
     * 指定位置添加节点
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("请输入正确的索引值.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size ++;

        // 1. 处理头结点问题
//        if (index == 0)
//            addFirst(e);
//        else {
//            // 1. 获取要添加位置的前一个位置
//            Node prev = head;
//            for (int i = 0; i < index - 1; i++) {
//                prev = prev.next;
//            }
//
////            // 2. 创建节点
////            Node node = new Node(e);
////
////            // 3. 指向前一个位置的next节点
////            node.next = prev.next;
////
////            // 4. 前一个位置的节点next指向node
////            prev.next = node;
//
//            prev.next = new Node(e, prev.next);
//            size ++;
//        }
    }

    /**
     * 在链表末尾添加元素
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }
}
