package com.moyu.example.algorithms.sort.selection;

import com.moyu.example.algorithms.sort.Sort;
import java.util.Random;

/**
 *      选择排序算法
 */
public class Selection<E extends Comparable<E>> implements Sort<E> {

    @Override
    public void sort(E[] e) {
        /***
         *    搞清楚这两个循环在做什么就不难理解选择排序:
         *      1. 第一层循环主要是从头到尾遍历数组, 并且被遍历到的元素默认为最小元素
         *      2. 第二层循环主要用于判断是否出现更小元素, 如果出现则把min的下标更新
         *      3. 经过上面2步后, 把元素进行交换, 如果被遍历的元素就是最小的元素则和自己交换, 否则和指定下标元素进行交换
         */
        for (int i = 0; i < e.length; i++) {
            int min = i;        // 假设当前元素即最小元素
            for (int j = i + 1; j < e.length; j++) {
                if (e[min].compareTo(e[j]) > 0)     // 如果发现有比当前更小的元素出现则更新为当前索引值
                    min = j;            // 发现更小的元素, 更新为新的索引值
            }

//            System.out.println("=================第" + (i + 1) + "次排序后结果: =================");
            swap(e, i, min);
//            show(e);        // 方便调试可以打印每次结果
        }
    }

    /**
     * 交换数组中的值
     * @param nums
     * @param i
     * @param min
     */
    private void swap(E[] nums, int i, int min) {
        E e = nums[i];
        nums[i] = nums[min];
        nums[min] = e;
    }

    @Override
    public void show(E[] e) {
        StringBuffer res = new StringBuffer("[");
        for (int i = 0; i < e.length; i++) {
            res.append(e[i] + ",");
        }

        res = res.deleteCharAt(res.length() - 1);
        res.append("]");
        System.out.println(res.toString());
    }

    @Override
    public boolean isSorted(E[] e) {
        for (int i = 0; i < e.length - 1; i++) {
            if (e[i].compareTo(e[i + 1]) > 0)
                throw new IllegalArgumentException("排序出错, 请检查程序");
        }

        System.out.println("排序正常...");
        return true;
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[1000];
        Sort<Integer> selection = new Selection<>();

        Random r = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(10000);
        }


        System.out.println("排序前: ");
        selection.show(nums);

        selection.sort(nums);
        System.out.println("排序后: ");
        selection.show(nums);

        selection.isSorted(nums);
    }
}
