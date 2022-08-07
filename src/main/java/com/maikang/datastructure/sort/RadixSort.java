package com.maikang.datastructure.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {

    public static void main(String[] args) {
        int[] A = {11, 98, 3, 2, 18, 18, 97, 20, 11, 3, 2, 33};
        int[] ret = new RadixSort().sort(A);
        System.out.println(Arrays.toString(A));
    }

    public int[] sort(int[] A) {
        int base = 10;
        int max = Arrays.stream(A).max().getAsInt();
        int digits = 0;
        while (max > 0) {
            digits++;
            max /= base;
        }

        List<Integer>[] buckets = new ArrayList[base];
        for (int i = 0; i < digits; i++) {
            for (int j = 0; j < base; j++) buckets[j] = new ArrayList<>();
            for (int num : A) {
                int idx = getIndex(num, i);
                buckets[idx].add(num);
            }
            int p = 0;
            for (List<Integer> list : buckets)
                for (int num : list) A[p++] = num;
        }
        return A;
    }

    private int getIndex(int num, int i) {
        int ans = 0;
        while (i-- >= 0) {
            ans = num % 10;
            num /= 10;
        }
        return ans;
    }
}
