package com.moyu.example.structure.heap;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Joker on 20/1/4.
 */
public class MaxHeapTest {


    public static void testHeap(ArrayList<Integer> testData) {

        MaxHeap<Integer> maxHeap = new MaxHeap<>(testData);
        int[] arr2 = new int[testData.size()];
        // 移除最大元素
        for (int i = 0; i < testData.size() ; i ++) {
            arr2[i] = maxHeap.extractMax();
        }

        // 如果上一个元素小于下一个元素则出错
        for (int i = 1; i < testData.size(); i++) {
            if (arr2[i - 1] < arr2[i])
                throw new IllegalArgumentException("Error");
        }

        System.out.println("最大堆测试通过");
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();

        int[] nums = {28, 13, 17, 15, 62, 41, 30, 16, 22, 19} ;

        for (int i = 0 ; i < nums.length; i ++) {
            maxHeap.add(nums[i]);
        }

        System.out.println(maxHeap);
        System.out.println("==================");

        int[] arr = new int[nums.length];
        for (int i = 0 ; i < nums.length; i++) {
            arr[i] = maxHeap.extractMax();
        }

        StringBuffer buffer = new StringBuffer();
        for (int i = 0 ; i < arr.length; i ++) {
            if (i == arr.length - 1)
                buffer.append(arr[i]);
            else
                buffer.append(arr[i]).append(",");
        }

        System.out.println(buffer.toString());




        // 测试2
        int n = 10000;
        MaxHeap<Integer> maxHeap2 = new MaxHeap<>();
        Random random = new Random();
        // 添加元素
        for (int i = 0; i < n; i++) {
            maxHeap2.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr2 = new int[n];
        // 移除最大元素
        for (int i = 0; i < n ; i ++) {
            arr2[i] = maxHeap2.extractMax();
        }

        // 如果上一个元素小于下一个元素则出错
        for (int i = 1; i < n; i++) {
            if (arr2[i - 1] < arr2[i])
                throw new IllegalArgumentException("Error");
        }

        System.out.println("最大堆测试通过");



        System.out.println("==================");

        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            data.add(nums[i]);

        MaxHeap<Integer> maxHeap3 = new MaxHeap<>(data);

        System.out.println(maxHeap3);


        System.out.println("==================");


        ArrayList<Integer> testData = new ArrayList<>();
        Random r = new Random();
        // 添加元素
        for (int i = 0; i < n; i++) {
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }
        testHeap(testData);
    }
}
