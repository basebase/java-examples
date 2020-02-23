package com.moyu.example.jobs;

import com.moyu.example.structure.heap.MaxHeap;

/**
 * Created by Joker on 20/2/23.
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        return searchMatrix(matrix, target, 0, 0);
    }

    private boolean searchMatrix(int[][] matrix, int target, int row, int column) {
        // 递归到底都没有找到的话
        if (row > matrix.length - 1 || (row == matrix.length - 1 && column == matrix[row].length) )
            return false;

        if (matrix[row][column] < target && matrix[row][matrix[row].length - 1] >= target)
            return searchMatrix(matrix, target, row, column + 1);
        else if (matrix[row][column] > target)
            return searchMatrix(matrix, target, row + 1, 0);



        if (matrix[row][column] < target && matrix[row][matrix[row].length - 1] < target)
            return searchMatrix(matrix, target, row + 1, column);

        if (matrix[row][column] == target)
            return true;
        else
            return false;
    }

    private void testMain() {
        int[][] matrix = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        boolean b = searchMatrix(matrix, 211);
        System.out.println(b);
    }

    public static void main(String[] args) {
        new SearchMatrix().testMain();
    }
}
