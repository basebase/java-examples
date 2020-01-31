package com.moyu.example.structure.segment;

/**
 * Created by Joker on 20/1/23.
 */
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        this.data = (E[]) new Object[arr.length];
        for (int i = 0 ; i < arr.length; i ++)
            this.data[i] = arr[i];

        this.tree = (E[]) new Object[this.data.length * 4];

        buildSegmentTree(0, 0, this.data.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return ;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("请正确输入区间值");

        return query(0, 0, data.length -1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 要查找的区间在右子树中
        if (queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid) // 要查找的区间在左子树中
            return query(leftTreeIndex, l, mid, queryL, queryR);

        // 当要查询的区间, 在左右子树中
        E leftQueryResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightQueryResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftQueryResult, rightQueryResult);
    }


    public void set(int index, E e) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("请正确输入索引位置.");

        data[index] = e;
        // 初始化从根节点开始, 查找到对应的叶子节点更新
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return ;
        }

        int mid = l + (r - l) / 2;
        int leftIndexTree = leftChild(treeIndex);
        int rightIndexTree = rightChild(treeIndex);

        // 如果索引位置大于中间值, 则一定在树的右侧, 否则在树的左侧。
        if (index >= mid + 1)
            set(rightIndexTree, mid + 1, r, index, e);
        else
            set(leftIndexTree, l, mid, index, e);

        // 最后不要忘记更新父节点的值
        tree[treeIndex] = merger.merge(tree[leftIndexTree], tree[rightIndexTree]);
    }

    public int getSize() {
        return this.data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= this.data.length)
            throw new IllegalArgumentException("请输入正确的索引");
        return this.data[index];
    }

    // 返回完全二叉树的数组表示左孩子的索引位置
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示右孩子的索引位置
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        res.append('[');
        for (int i = 0; i < tree.length; i ++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i < tree.length - 1)
                res.append(", ");
        }

        res.append(']');
        return res.toString();
    }
}
