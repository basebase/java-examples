package com.moyu.example.structure.avl;

import java.awt.image.BandCombineOp;
import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;

        // 树的高度
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;

            /**
             *  添加元素的时候, 肯定是一个叶子节点, 所以创建默认的高度就是1
             */
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }


    /**
     * 判断该二叉树是否是一颗二分搜索树
     * @return
     */
    public boolean isBST() {
        if (root == null)
            return true;

        /***
         * 在介绍二分搜索树的时候, 我们介绍过一个特性, 如果是一颗二分搜索树在进行中序遍历它是升序的
         */
        ArrayList<K> keys = new ArrayList<K>();
        inOrder(root, keys);

        for (int i = 1; i < keys.size(); i ++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) // 如果不是升序的情况则是不是一颗二分搜索树。
                return false;
        }

        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null)
            return ;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }


    /***
     * 判断该二叉树是否是一颗平衡二叉树。
     * @return
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null)
            return true; // 如果这棵树都为空, 肯定的是一个平衡的 /狗头

        int balanced = getBalanceFactor(node);
        if (Math.abs(balanced) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right); // 左子树和右子树平衡因子都必须在范围1内才是一颗平衡二叉树
    }


    /***
     * 获取节点node的高度
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    /***
     * 计算节点node平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;

        /***
         * 平衡因子计算方法:
         *   当前节点左子树高度 - 当前节点右子树高度
         */
        return getHeight(node.left) - getHeight(node.right);
    }


    /****
     *
     *                 y                                                 x
     *               /  \                                              /   \
     *              x    T4           向右旋转(y)                      z     y
     *             / \             -------------->                  / \    / \
     *            z   T3                                           T1 T2  T3 T4
     *           / \
     *          T1 T2
     *
     * @param y
     * @return
     */
    private Node rightRotate(Node y) {
        /***
         *  根据上面的图例, 我们进行右旋转
         */

        // 1. 首先获取X节点
        Node x = y.left;

        // 2. 获取X节点的右子树
        Node t3 = x.right;

        // 3. 更新X节点的右子树, 把Y节点挂载上
        x.right = y;

        // 4. 更新Y节点的左子树, 将X之前的右节点数据挂载上去
        y.left = t3;


        /***
         *  注意:
         *    不要忘记更新树的高度, 当我们旋转后, 树的高度就会降低。
         *    更新的顺序是先更新Y节点的值, 在更新X节点的值, 这是因为X节点的高度值是会和新的Y节点的高度值相关的:
         *      1. 先更新Y节点的高度
         *      2. 在更新X节点的高度
         */
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }


    /****
     *
     *                 y                                                 x
     *               /  \                                              /   \
     *              T1   x                 向左旋转(y)                 y     z
     *                  / \             -------------->             / \    / \
     *                 T2  z                                       T1 T2  T3 T4
     *                    / \
     *                   T3 T4
     *
     * @param y
     * @return
     */
    private Node leftRotate(Node y) {
        /***
         *  根据上面的图例, 我们进行左旋转
         */

        // 1. 首先获取X节点
        Node x = y.right;

        // 2. 接收X节点的左子树
        Node t2 = x.left;

        // 3. 将节点Y挂载到节点X的左子树下
        x.left = y;

        // 4. 将T2挂载到Y节点的右子树下
        y.right = t2;

        /***
         *  注意:
         *    不要忘记更新树的高度, 当我们旋转后, 树的高度就会降低。
         *    更新的顺序:
         *      1. 先更新Y节点的高度
         *      2. 在更新X节点的高度
         */
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;

    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value); // 遍历到最后一个节点返回, 默认高度为1
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        /***
         *  在添加元素的时候, 我们需要维护一下树的高度。
         *  如何计算高度呢?
         *    当前节点 + Max(左子树高度, 右子树高度)
         */
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        /***
         *  有了高度之后, 我们很轻易的获取到平衡因子
         */
        int balanceFactor = getBalanceFactor(node);

        // 如果平衡因子大于1, 则破坏了这颗树的平衡性...
        // 这里暂时先不处理, 先输出一段话即可。
//        if (Math.abs(balanceFactor) > 1)
//            System.out.println("unbalanced : " + balanceFactor);


        /***
         *
         * 维护平衡
         *   之所以在这里维护平衡是, 节点的元素添加完成了, 也一级一级的向父节点回溯中, 并得到相应的高度和平衡因子。
         *   因此可以很方便的知道当前以该节点为根的树是否保持平衡性。
         */

        // 如果需要右旋转的情况(LL)
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);   // 将旋转过后的平衡树返回回去, 这样父节点就又是一颗平衡二叉树了

        // 如果需要左旋转的情况(RR)
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);

        // 先向左旋转, 在向右旋转的情况(LR)
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 先向右旋转, 在向左旋转的情况(RL)
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");
        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        /***
         * 这里, 使用一个变量接住删除后的Node信息, 这样, 如果当前二分搜索树的平衡性被破坏, 我们可以进行平衡。
         */
        Node retNode = null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
//            return node;
            retNode = node;
        } else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
//            return node;
            retNode = node;
        } else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
//                return rightNode;
                retNode = rightNode;
            }

            // 待删除节点右子树为空的情况
            /***
             * 这里, 还需要注意一点, 之前我们是return返回数据, 所以我们写成if没问题, 现在我们使用变量来接收,
             * 下面的过程都会执行一遍, 但我们的条件是互斥的, 所以需要写成else if ...了
             *
              */
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
//                return leftNode;
                retNode = leftNode;
            } else {

                // 待删除节点左右子树均不为空的情况

                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);

                /***
                 * successor.right = removeMin(node.right);
                 *   这里有个小bug需要注意一下, 由于我们并没有维护removeMin方法中二分搜索树的平衡
                 *   所以, 很有可能会破坏整棵树的平衡性。
                 *
                 *   这里有两个解决方法:
                 *     1. 在removeMin()方法中维护二分搜索树的平衡性。
                 *     2. 我们在remove()方法中已经添加了整棵树的自平衡,
                 *        这句话已经求出Node右子树的最小值: Node successor = minimum(node.right);
                 *        而removeMin(node.right);要做的事情就是在Node的右子树中将这个最小值删除, 而
                 *        我们的remove()方法就是删除以Node为根节点相应的某一个K对应的节点。
                 *        所以successor中已经存储右子树的最小值了, 使用可以写成remove(node.right, successor.key)
                 */
//            successor.right = removeMin(node.right);
                // +++
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

//            return successor;

                retNode = successor;

            }
        }

        // 由于是删除节点, 有可能retNode会获得空节点, 需要判断一下
        if (retNode == null)
            return null;


        /***
         *  在最后, 维护二分搜索树的平衡性
         */
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        int balanceFactor = getBalanceFactor(retNode);

        // 如果需要右旋转的情况(LL)
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        // 如果需要左旋转的情况(RR)
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        // 先向左旋转, 在向右旋转的情况(LR)
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // 先向右旋转, 在向左旋转的情况(RL)
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode; // 将维护后的树返回回去

    }
}
