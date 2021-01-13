package com.moyu.example.algorithms.sort;

/**
 * Created by Joker on 20/12/30.
 */
public interface Sort<E extends Comparable<E>> {
    void sort(E[] e);

    default void show(E[] e) {
        StringBuffer res = new StringBuffer("[");
        for (int i = 0; i < e.length; i++) {
            res.append(e[i] + ",");
        }

        res = res.deleteCharAt(res.length() - 1);
        res.append("]");
        System.out.println(res.toString());
    }

    default boolean isSorted(E[] e) {
        for (int i = 0; i < e.length - 1; i++) {
            if (e[i].compareTo(e[i + 1]) > 0)
                throw new IllegalArgumentException("排序出错, 请检查程序");
        }

        System.out.println("排序正常...");
        return true;
    }
}
