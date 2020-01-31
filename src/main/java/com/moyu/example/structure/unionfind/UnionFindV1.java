package com.moyu.example.structure.unionfind;

/**
 * Created by Joker on 20/1/29.
 * 基于数组实现的并查集
 */
public class UnionFindV1 implements UF {

    private int[] id; // 集合编号

    public UnionFindV1(int size) {
        id = new int[size];

        /***
         * 在初始化的时候, 我们的元素都是独立的, 还没有某两个元素互相合并
         * 合并操作等我们构建好并查集之后进行union即可
         *
         * 现在我们初始化, 每个元素的编号都不一样
         *   第0个元素对应的集合编号是0
         *   第1个元素对应的集合编号是1
         *   ...依次类推
         *
         */
        for (int i = 0 ; i < id.length; i ++)
            id[i] = i;
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean find(int p, int q) {

        /***
         * 首先查询p和q两个元素所属同一个集合编号
         *   O(1)查找
         */
        return find(p) == find(q);
    }

    // 查找元素p所对应的集合编号
    private int find(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("Error/");
        return id[p];
    }

    @Override
    public void union(int p, int q) {

        /***
         * 合并元素p和元素q所属的集合。
         *   需要循环所有元素进行替换O(n)
         */

        int pID = find(p);
        int qID = find(q);

        if (pID != qID) { // 这里只判断它们属于不同的集合中, 才进行合并。
            for (int i = 0; i < id.length; i++)
                if (id[i] == pID)
                    id[i] = qID;
        }
    }
}
