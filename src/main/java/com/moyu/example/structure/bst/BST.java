package com.moyu.example.structure.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Joker on 19/12/22.
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        E e ;
        Node left ;
        Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root ;
    private int size ;

    public BST() {
        this.root = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size ++ ;
            return new Node(e);
        }

        if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }

        return node;
    }


    public boolean contains(E e) {
        return contains(root, e) ;
    }

    private boolean contains(Node node, E e) {
        if (node == null)
            return false ;

        if (e.compareTo(node.e) > 0)
            return contains(node.right, e);
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else
            return true ;
    }



    public void preOrder() {
        preOrder(root);
    }

    // 前序遍历二分搜索树
    private void preOrder(Node node) {
        if (node == null)
            return ;
        System.out.println(node.e);
        preOrder(node.left); // 遍历左子树
        preOrder(node.right); // 遍历右子树
    }


    public void preOrderNR() {
        preOrderNR(root);
    }

    // 利用栈实现前序遍历
    private void preOrderNR(Node node) {
        Stack<Node> stack = new Stack();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }


    public void inOrder() {
        inOrder(root);
    }

    // 中序遍历节点数据
    private void inOrder(Node node) {
        if (node == null) return ;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }


    public void postOrder() {
        postOrder(root);
    }

    // 后序遍历
    private void postOrder(Node node) {
        if (node == null) return ;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 层序遍历
    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }


    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is Empty !");
        return minimum(root).e;
    }

    // 查询二分搜索树最小值
    // 其实这种完全就破坏树结构了, 和链表没区别了, 一直扫左边数据
    private Node minimum(Node node) {
        // 当前节点的left如果为空就表示当前节点为叶子节点退出递归条件
        if (node.left == null)
            return node ;
        // 否则一直往左查询
        return minimum(node.left);
    }

    public E maximun() {
        if (size == 0)
            throw new IllegalArgumentException("BST is Empty !");
        return maximun(root).e;
    }

    private Node maximun(Node node) {
        if (node.right == null)
            return node;
        return maximun(node.right);
    }

    public E removeMin() {
        E e = minimum();
        root = removeMin(root);
        return e ;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            size -- ;
            Node rightNode = node.right;
            node.right = null;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E e = maximun();
        root = removeMax(root);
        return e ;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            size --;
            Node leftNode = node.left;
            node.left = null;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }


    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (e.compareTo(node.e) == 0) {



            // 第一种情况: 删除只有左孩子的节点(简单)
            if(node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 第二种情况: 删除只有右孩子的节点(简单)
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }


            /***
             * 待删除节点左右子树都不为空的情况
             */
            // 1. 找到比待删除节点大的最小节点, 即待删除节点右子树中最小的节点(找到后继节点)
            // 2. 用后继节点顶替待删除节点的位置
            Node succeed = minimum(node.right);
            // 返回删除最小值后的一个新树, 最小值已经被我们记录住了, 然后设置左右子树
            succeed.right = removeMin(node.right);
            succeed.left = node.left;
            // 这里之所以没有size--, 是因为removeMin方法已经做了
            node.left = node.right = null;
            return succeed;

        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            node.left = remove(node.left, e);
            return node;
        }
    }


    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuffer res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return ;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    // depth深度, 这样加入--就能知道在不在一个层级
    private String generateDepthString(int depth) {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < depth; i ++) {
            res.append("--");
        }

        return res.toString();
    }


}
