package com.moyu.example.structure.unionfind;

import java.util.Random;

/**
 * Created by Joker on 20/1/30.
 */
public class UnionFindTest {

    private static double testUF(UF uf, int m) {

        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();

        // m次合并性能测试
        for (int i = 0; i < m; i ++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.union(a, b);
        }

        // m次查询性能测试
        for (int i = 0; i < m; i ++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.find(a, b);
        }


        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int size = 10000;

        size = 100000;

        int m = 10000;

        m = 100000;

        UnionFindV1 unionFindV1 = new UnionFindV1(size);
        System.out.println("UnionFindV1: " + testUF(unionFindV1, m) + " s");

        UnionFindV2 unionFindV2 = new UnionFindV2(size);
        System.out.println("UnionFindV2: " + testUF(unionFindV2, m) + " s");


        size = 10000000;
        m = 10000000;

        UnionFindV3 unionFindV3 = new UnionFindV3(size);
        System.out.println("UnionFindV3: " + testUF(unionFindV3, m) + " s");



        UnionFindV4 unionFindV4 = new UnionFindV4(size);
        System.out.println("UnionFindV4: " + testUF(unionFindV4, m) + " s");



        UnionFindV5 unionFindV5 = new UnionFindV5(5);
        System.out.println("UnionFindV5: " + testUF(unionFindV5, m) + " s");

    }
}
