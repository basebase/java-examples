package com.moyu.example.structure.bst;

import com.moyu.example.structure.queue.LinkedListQueue;
import com.moyu.example.structure.queue.Queue;
import com.moyu.example.structure.stack.LinkedListStack;
import com.moyu.example.structure.stack.Stack;

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
        } else if (node.e.compareTo(e) == 0) {
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
     * 查找元素非递归写法
     * @param e
     * @return
     */
    public boolean nonRecursiveFind(E e) {
        Node node = root;
        while (node != null) {
            if (node.e.compareTo(e) > 0) {
                node = node.left;
            } else if (node.e.compareTo(e) < 0) {
                node = node.right;
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * 查找最小元素
     * @return
     */
    public E findMin() {
        if (size == 0)
            throw new IllegalArgumentException("当前二分搜索树为空");
        Node n = findMin(root);
        return n.e;
    }

    /**
     * 递归查找最小元素
     * @param node
     * @return
     */
    private Node findMin(Node node) {
        if (node.left == null)      // 已经到叶子节点, 即最小值
            return node;

        Node n = findMin(node.left);
        return n;
    }

    /***
     * 查找最大元素
     * @return
     */
    public E findMax() {
        if (size == 0)
            throw new IllegalArgumentException("当前二分搜索树为空");
        Node n = findMax(root);
        return n.e;
    }

    /**
     * 递归查找最大元素
     * @param node
     * @return
     */
    private Node findMax(Node node) {
        if (node.right == null)         // 已经到叶子节点, 即最大值
            return node;
        Node n = findMax(node.right);
        return n;
    }

    /**
     * 二分搜索树前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 二分搜索树递归前序遍历, 先访问当前节点, 然后访问左右子树节点
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null)
            return ;

        // 1. 访问当前节点
        System.out.println(node.e);
        // 2. 访问左子树
        preOrder(node.left);
        // 3. 访问右子树
        preOrder(node.right);
    }

    /**
     * 二分搜索树前序遍历非递归算法
     */
    public void preOrderNonRecursive() {
        // 利用栈实现前序遍历
        Stack<Node> stack = new LinkedListStack<>();
        stack.push(root);

        StringBuffer buffer = new StringBuffer("[");

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node != null) {
                buffer.append(node.e + ",");
                stack.push(node.right);
                stack.push(node.left);
            }
        }

        buffer = buffer.deleteCharAt(buffer.length() - 1);
        buffer.append("]");

        System.out.println(buffer);
    }

    /**
     * 二分搜索树中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /***
     * 二分搜索树递归中序遍历
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null)
            return ;

        // 1. 访问左子树
        inOrder(node.left);
        // 2. 访问当前节点
        System.out.println(node.e);
        // 3. 访问右子树
        inOrder(node.right);
    }

    /**
     * 二分搜索树后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 二分搜索树后序遍历, 递归算法
     * @param node
     */
    private void postOrder(Node node) {
        if (node == null)
            return ;

        // 1. 访问左子树
        postOrder(node.left);
        // 2. 访问右子树
        postOrder(node.right);
        // 3. 访问节点
        System.out.println(node.e);
    }

    /***
     * 二分搜索树层序遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedListQueue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            System.out.println(node.e);

            if (node.left != null)
                queue.enqueue(node.left);

            if (node.right != null)
                queue.enqueue(node.right);

        }
    }


    /**
     * 删除最小值, 并返回删除元素内容
     * @return
     */
    public E removeMin() {
        E e = findMin();
        root = removeMin(root);
        return e;
    }

    /***
     * 递归删除最小元素
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        // 1. 一直查询左子树, 找到最小节点, 然后返回其右子树
        if (node.left == null) {
            /**
             *     1. 查找最小的值所有该节点的左孩子一定是为null
             *     2. 返回当前节点的右孩子, 如果右孩子没有节点则为null, 如果有节点则会重新挂载到新的节点上
             */
            Node retNode = node.right;
            size --;
            return retNode;
        }

        Node retNode = removeMin(node.left);
        // 将返回最小节点的右孩子挂载到当前节点的左孩子上
        node.left = retNode;
        return node;
    }

    /***
     * 删除最大值, 返回删除元素内容
     * @return
     */
    public E removeMax() {
        E e = findMax();
        root = removeMax(root);
        return e;
    }

    /***
     * 递归删除最大元素
     * @param node
     * @return
     */
    private Node removeMax(Node node) {

        /**
         * 和删除最小元素同理, 只不过是left改为right而已
         */
        if (node.right == null) {
            Node retNode = node.left;
            size --;
            return retNode;
        }

        Node retNode = removeMax(node.right);
        node.right = retNode;
        return node;
    }

    /**
     * 删除二分搜索树任意节点值
     * @param e
     */
    public void remove(E e) {
        boolean b = find(e);
        if (b) {
            root = remove(root, e);
        }
    }

    /**
     * 递归删除二分搜索树中任意节点值
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if (node.e.compareTo(e) > 0) {
            node.left = remove(node.left, e);
        } else if (node.e.compareTo(e) < 0) {
            node.right = remove(node.right, e);
        } else {

            /**
             *      找到要删除的节点值
             *          1. 判断是否只有做孩子
             *          2. 判断是否只有右孩子
             *          3. 判断左右孩子都有
             */

            if (node.left != null && node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            } else if (node.left == null && node.right != null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            } else {

                /***
                 *      复杂的是左右孩子都存在
                 *          1. 找出右孩子节点最小节点
                 *          2. 删除右孩子最小节点
                 *          3. 将找出的节点左右子树挂载新的节点
                 *
                 */

                Node leftNode = node.left;
                // 1. 找到待删除节点的后继节点
                Node successor = findMin(node.right);
                // 2. 删除待删除节点的后继节点
                Node minNode = removeMin(node.right);
                // 后继节点右子树指向删除后的树结构
                successor.right = minNode;
                // 后继节点左子树则为待删除节点的左子树
                successor.left = leftNode;


                node.left = null;
                node.right = null;
                return successor;
            }

        }

        return node;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        generateBSTString(root, 0, buffer);
//        echo(root, buffer);
        return buffer.toString();
    }

    /***
     * 生成二分搜索树输出结果,
     * @param node
     * @param depth
     * @param buffer
     */
    private void generateBSTString(Node node, int depth, StringBuffer buffer) {
        if (node == null) {
            buffer.append(generateDepthString(depth) + "null \n");
            return ;
        }

        buffer.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, buffer);
        generateBSTString(node.right, depth + 1, buffer);
    }

    /***
     * 根据树的深度输出的--
     * @param depth
     * @return
     */
    private String generateDepthString(int depth) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < depth; i++) {
            buffer.append("--");
        }

        return buffer.toString();
    }
}
