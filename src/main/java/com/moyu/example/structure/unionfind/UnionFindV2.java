package com.moyu.example.structure.unionfind;

/**
 * Created by Joker on 20/1/30.
 * 基于树结构的并查集
 */
public class UnionFindV2 implements UF {

    private int[] parent;


    public UnionFindV2(int size) {
        parent = new int[size];
        // 初始化, 相互之间没有共同集合, 大家都指向自己
        for (int i = 0 ; i < parent.length; i++)
            parent[i] = i;
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
        if (pRoot != qRoot)
            parent[pRoot] = qRoot;
    }
}
