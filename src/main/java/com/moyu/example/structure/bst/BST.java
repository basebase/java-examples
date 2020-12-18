package com.moyu.example.structure.bst;

/***
 * 实现一棵二分搜索树
 * @param <E>
 */
public class BST<E extends Comparable<E>> {     // 对于这里的泛型我们需要继承一个Comparable类, 这样我么的对象就可以进行比较

    private class Node {
        Node left;      // 左孩子
        Node right;     // 右孩子
        E e;          // 元素值

        public Node(Node left, Node right, E e) {
            this.left = left;
            this.right = right;
            this.e = e;
        }

        public Node(E e) {
            this(null, null, e);
        }
    }

    private Node root;      // 根节点
    private int size;       // 有多少节点

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 插入元素
     * @param e
     */
    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size ++;
        } else
            add(root, e);
    }

    /***
     * 插入元素, 从节点node开始查找并插入
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {

        if (node == null) {
            size ++;
            return new Node(e);
        }

        if (node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);

//            Node addNode = add(node.left, e);
//            node.left = addNode;
        } else if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);

//            Node addNode = add(node.right, e);
//            node.right = addNode;
        } else if (node.e.compareTo(e) == 1) {
           // 对于重复数据, 这里不考虑;
           return node;
        }

        return node;
    }

    public void echo(Node node, StringBuffer buffer) {
        if (node == null)
            return ;

        buffer.append(node.e).append(", ");
        echo(node.left, buffer);
        echo(node.right, buffer);
    }


    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("[ ");
        echo(root, buffer);
        buffer.append("]");
        return buffer.toString();
    }
}
