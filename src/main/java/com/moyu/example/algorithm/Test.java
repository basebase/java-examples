package com.moyu.example.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Joker on 20/2/28.
 */
public class Test {



    public int topK(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length ; i ++) {
            map.put(nums[i], 0);
        }
        return map.get(k);

    }

    public static void main(String[] args) {
        int[] nums = {5, 10, 23, 8, 100};
        new Test().topK(nums, 3);
    }
}
