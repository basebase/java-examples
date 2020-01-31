package com.moyu.example.structure.unionfind;

/**
 * Created by Joker on 20/1/30.
 * 基于树结构的并查集
 */
public class UnionFindV4 implements UF {

    private int[] parent;
    private int[] rank; // rank[i]表示以i为根的集合所展示的树的深度


    public UnionFindV4(int size) {
        parent = new int[size];
        rank = new int[size];

        // 初始化, 相互之间没有共同集合, 大家都指向自己
        for (int i = 0 ; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1; // 初始化, 每棵树的高度都为1
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

        if (pRoot != qRoot) {

            /***
             * 根据两个元素所在树的rank不同判断合并方向
             * 将rank低的树合并到rank高的树上
             *
             *   注意:
             *     当一棵树比另外一颗树的深度更大的话, 就不需要维护树的深度,
             *     比如A的深度是4, B的深度是3, B挂载到A上面, B的深度最多和A的子树深度一样大
             */

            if (rank[pRoot] < rank[qRoot]) {
                parent[pRoot] = qRoot;
            } else if (rank[qRoot] < rank[pRoot]) {
                parent[qRoot] = pRoot;
            } else {
                parent[qRoot] = pRoot;
                rank[pRoot] += 1; // 如果两棵树的深度相等, 在进行指向之后, 肯定会多一个节点出来的。
            }
        }
    }
}
