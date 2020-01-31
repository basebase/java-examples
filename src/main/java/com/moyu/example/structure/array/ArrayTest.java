package com.moyu.example.structure.array;

/**
 * Created by Joker on 19/9/13.
 */
public class ArrayTest {
    public static void main(String[] args) {
        Array<Integer> array = new Array();
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }

        System.out.println(array);

        array.addFirst(-1);
        System.out.println(array);

        array.addFirst(-2);
        System.out.println(array);

        array.remove(0);
        System.out.println(array);


        array.remove(1);
        System.out.println(array);


        array.remove(2);
        System.out.println(array);



//        System.out.println(array);
//
//        array.add(1, 100);
//
//        System.out.println(array);
//
//        array.addFirst(200);
//
//        System.out.println(array);
//
//        System.out.println(array.get(4));
//
//        System.out.println(array.contains(14));
//
//        System.out.println(array.find(4));
//
//
//        int remove = array.remove(3);
//        System.out.println("删除元素为: " + remove);
//        System.out.println(array);
//
//        array.removeFirst();
//        System.out.println(array);
//
//        array.removeLast();
//        System.out.println(array);

    }
}
