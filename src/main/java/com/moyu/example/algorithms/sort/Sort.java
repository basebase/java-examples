package com.moyu.example.algorithms.sort;

/**
 * Created by Joker on 20/12/30.
 */
public interface Sort<E extends Comparable<E>> {
    void sort(E[] e);
    void show(E[] e);
    boolean isSorted(E[] e);
}
