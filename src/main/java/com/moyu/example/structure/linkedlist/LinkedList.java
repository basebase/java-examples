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
}
