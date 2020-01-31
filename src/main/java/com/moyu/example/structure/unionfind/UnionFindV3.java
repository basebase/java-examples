package com.moyu.example.structure.unionfind;

/**
 * Created by Joker on 20/1/30.
 * 基于树结构的并查集
 */
public class UnionFindV3 implements UF {

    private int[] parent;
    private int[] sz; // 记录树的节点个数, sz[i]表示以i为根节点的集合中元素个数


    public UnionFindV3(int size) {
        parent = new int[size];
        sz = new int[size];

        // 初始化, 相互之间没有共同集合, 大家都指向自己
        for (int i = 0 ; i < parent.length; i++) {
            parent[i] = i;
            sz[i] = 1; // 初始化, 每棵树的高度都为1
        }

    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean find(int p, int q) {
        return find(p) == find(q);
    }

    /***
     * 查找过程, 查找元素p所对应的集合编号
     * O(h)复杂度, 其中h为树的高度。
     * @param p
     * @return
     */
    private int find(int p) {

        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("Error");

        while (p != parent[p]) { // 当p和parent[p]相等也就是指向自己, 即根节点
            p = parent[p];
        }

        return p;
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);



        // 让p的根节点指向q的根节点
        if (pRoot != qRoot) {

            // 判断两个树的元素个数, 让元素个数小的根节点指向元素个数多的根节点。
            // 根据两个元素所在树的元素个数不同判断合并方向
            // 将元素个数少的集合合并到元素个数多的集合上

            if (sz[pRoot] < sz[qRoot]) {
                parent[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot]; // 维护sz数组的值, 我们让qRoot值加上pRoot的值, 因为它们已经合并了。
            } else {
                parent[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            }

        }



    }
}
