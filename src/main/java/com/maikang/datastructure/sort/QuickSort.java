package com.maikang.datastructure.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public void sort(int[] nums, int left, int right) {
        if (left >= right) return;
        int pivotIdx = left + new Random().nextInt(right-left);
        pivotIdx = partition(nums, left, right, pivotIdx);


        sort(nums, left, pivotIdx-1);
        sort(nums, pivotIdx+1, right);

    }

    public void sort2(int[] nums, int left, int right) {
        if (left >= right) return;
        int pivotIdx = left;
        int storeIdx = left+1;
        for (int i = left; i <= right; i++) {
            if (nums[i] < nums[pivotIdx]) {
                swap(nums, i, storeIdx);
                storeIdx++;
            }
        }
        storeIdx--;
        swap(nums, storeIdx, pivotIdx);
        sort2(nums, left, storeIdx-1);
        sort2(nums, storeIdx+1, right);
    }

//    private int partition(int[] nums, int left, int right, int pivotIdx) {
//        int pivotValue = nums[pivotIdx];
//        swap(nums, pivotIdx, left);
//        int storeIdx = left+1;
//        for (int i = left; i <= right; i++) {
//            if (nums[i] < pivotValue) {
//                swap(nums, storeIdx, i);
//                storeIdx++;
//            }
//        }
//        storeIdx--;
//        swap(nums, storeIdx, left);
//        return storeIdx;
//    }


    private int partition(int[] nums, int left, int right, int pivotIdx) {
        int pivotValue = nums[pivotIdx];
        swap(nums, pivotIdx, right);

        int storeIdx = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIdx, i);
                storeIdx++;
            }
        }
        swap(nums, storeIdx, right);
        return storeIdx;
    }
    private static void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 2, 4, 9, 22, 99, 23, 66};
//        new QuickSort().sort2(nums, 0, nums.length-1);
        new QuickSort().sort(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
