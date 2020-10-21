package com.moyu.example.structure.queue;

/***
 * 通过尾指针的链表实现一个高效的队列
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
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

    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void enqueue(E e) {

        // 如果还没有节点添加, 则初始化head = tail
        if (size == 0) {
            tail = new Node(e, tail);
            head = tail;
        } else {
            // 新添加的节点是一直往tail之后的, 所以tail.next = n
            Node n = new Node(e);
            tail.next = n;      // 添加新的元素
            tail = n;       // 更新尾结点
        }

        size ++;
    }

    @Override
    public E dequeue() {

        // 需要判断当前还有可以被删除的节点
        if (head != null) {
            E e = head.e;
            head = head.next;   // 从新指定新的头节点
            size --;
            return e;
        } else
            tail = null;

        return null;
    }

    @Override
    public E getFront() {
        return head.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer("Queue front: ");
        Node cur = head;
        while (cur != null) {
            res.append(cur.e).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        res.append(" tail");
        return res.toString();
    }
}
