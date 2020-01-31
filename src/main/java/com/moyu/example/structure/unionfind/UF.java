package com.moyu.example.structure.unionfind;

/**
 * Created by Joker on 20/1/27.
 */
public interface UF {

    // 并查集元素个数
    int getSize();

    boolean find(int p, int q);
    void union(int p, int q);
}
