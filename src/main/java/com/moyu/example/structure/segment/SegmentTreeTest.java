package com.moyu.example.structure.segment;

/**
 * Created by Joker on 20/1/22.
 */
public class SegmentTreeTest {
    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
//        System.out.println(segmentTree);

//        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(2, 5));
//        System.out.println(segmentTree.query(0, 5));

        segmentTree.set(5, 7);
        System.out.println(segmentTree.query(2, 5));

    }
}
