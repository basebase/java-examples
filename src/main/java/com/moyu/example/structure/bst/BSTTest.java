package com.moyu.example.structure.bst;

import java.util.ArrayList;

/**
 * Created by Joker on 19/12/22.
 */
public class BSTTest {
    public static void main(String[] args) {
        int[] nums = {41, 22, 15, 33, 13, 37, 58, 50, 63, 42, 53};
        BST<Integer> bst = new BST<>();
        for (Integer num : nums) {
            bst.add(num);
        }


//        System.out.println(bst.contains(8));

//        bst.preOrder();

//        bst.preOrderNR();


        bst.levelOrder();
        System.out.println("=============");

        System.out.println(bst.minimum());
        System.out.println(bst.maximun());


        ArrayList<Integer> res = new ArrayList<>();
        while (!bst.isEmpty()) {
            res.add(bst.removeMin());
        }

        for (int i = 1; i < res.size(); i ++) {
            if (res.get(i - 1) > res.get(i))
                throw new IllegalArgumentException("Error");
        }

        System.out.println(res);
        System.out.println("removeMin test completed.");



        for (Integer num : nums) {
            bst.add(num);
        }
        res = new ArrayList<>();
        while (!bst.isEmpty()) {
            res.add(bst.removeMax());
        }

        for (int i = 1; i < res.size(); i ++) {
            if (res.get(i - 1) < res.get(i))
                throw new IllegalArgumentException("Error");
        }

        System.out.println(res);
        System.out.println("removeMax test completed.");



        for (Integer num : nums) {
            bst.add(num);
        }
        bst.remove(58);
        System.out.println("=============");
        bst.levelOrder();
    }
}
