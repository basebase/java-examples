package com.moyu.example.jobs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joker on 20/2/23.
 *   LeetCode 169: hash暴力解法
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int mid = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < nums.length; i ++) {
            int key = nums[i];
            if (map.get(key) != null) {
                Integer v = map.get(key);
                map.put(key, v + 1);
            } else {
                map.put(key, 1);
            }
        }

        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            if (m.getValue() > mid) {
                return m.getKey();
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        int i = new MajorityElement().majorityElement(nums);
        System.out.println(i);
    }
}
