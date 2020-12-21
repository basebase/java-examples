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
     * 递归插入元素
     * @param e
     */
    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size ++;
//        } else
//            add(root, e);

        // 由于递归已经判断为空的情况, 所以不需要在上述情况下判断root节点是否为空
        root = add(root, e);
    }

    /***
     * 递归插入元素, 从节点node开始查找并插入
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {

        if (node == null) {
            size ++;
            // 这里返回一个新的Node节点, 如果根节点为空后就使用root来接收, 如果root不为空的情况下则返回原有的root节点
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

    /***
     * 非递归插入元素
     * @param e
     */
    public void nonRecursiveAdd(E e) {
        if (root == null)
            root = new Node(e);
        else {

            Node curNode = root;
            while (curNode != null) {
                if (curNode.e.compareTo(e) > 0) {
                    if (curNode.left == null) {
                        curNode.left = new Node(e);
                        break;
                    } else
                        curNode = curNode.left;
                } else if (curNode.e.compareTo(e) < 0) {
                    if (curNode.right == null) {
                        curNode.right = new Node(e);
                        break;
                    } else
                        curNode = curNode.right;
                }
            }
        }

        size ++;
    }


    /***
     * 查找二分搜索树元素
     * @param e
     * @return
     */
    public boolean find(E e) {
        return find(root, e);
    }

    /**
     * 递归查找二分搜索树元素
     * @param node
     * @param e
     * @return
     */
    private boolean find(Node node, E e) {
        if (node == null)
            return false;

        if (node.e.compareTo(e) > 0) {
            return find(node.left, e);
        } else if (node.e.compareTo(e) < 0) {
            return find(node.right, e);
        } else /*(node.e.compareTo(e) == 0)*/ {
            return true;
        }
    }

    /**
     * 查找最小元素
     * @return
     */
    public E findMin() {
        Node n = findMin(root);
        return n.e;
    }

    /**
     * 递归查找最小元素
     * @param node
     * @return
     */
    private Node findMin(Node node) {
        if (node.left == null)
            return node;

        Node n = findMin(node.left);
        return n;
    }

    /***
     * 查找最大元素
     * @return
     */
    public E findMax() {
        Node n = findMax(root);
        return n.e;
    }

    /**
     * 递归查找最大元素
     * @param node
     * @return
     */
    private Node findMax(Node node) {
        if (node.right == null)
            return node;
        Node n = findMax(node.right);
        return n;
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
