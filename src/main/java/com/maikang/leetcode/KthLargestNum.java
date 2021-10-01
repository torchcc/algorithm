package com.maikang.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 *  given a list a num, find the Kth largest num
 */
public class KthLargestNum {

    KthLargestNum() {}

    private Integer findTheKthLargestNum(List<Integer> nums, Integer K) {
        int[] con = new int[K + 1];
        con[0] = Integer.MAX_VALUE;

        for (int i = 0; i < K; i++) {
            Integer curInt = nums.get(i);
            for (int j = i+1; j > 0; j--) {
                if (curInt<con[j-1]) {
                    con[j] =  curInt;
                    break;
                }
                con[j]= con[j-1];
            }
        }

        List<Integer> collect = Arrays.stream(con).boxed().collect(Collectors.toList());
        System.out.println(collect);

        for (int i = K; i < nums.size(); i++) {
            Integer curInt = nums.get(i);
            int curInsertPosition = K+1;
            for (int j = K; j > 0; j--) {
                if (curInt < con[j]) {
                    break;
                }
                curInsertPosition--;
                con[j] = con[j-1];
            }
            if (curInsertPosition != K+1) {
                con[curInsertPosition] = curInt;
            }
        }

        System.out.println("ans is: " + con[K]);

        return con[K];
    }


    private Integer resolution2(List<Integer> nums, Integer K) {
        PriorityQueue<Integer> integers = new PriorityQueue<>(K, (o1, o2) -> {
            if (o1 > o2) return 1;
            else if (o1.equals(o2)) return 0;
            else return -1;
        });

        for (Integer num : nums) {
            if (integers.size() != K) {
                integers.add(num);
                continue;
            }
            System.out.println(integers);
            if (integers.peek() < num) {
                integers.poll();
                integers.add(num);
            }
        }
        System.out.println(integers);
        System.out.println("resolution2: ans is: " + integers.peek());
        return integers.peek();
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 4, 9, 3, 6, 3, 2, 100);
        new KthLargestNum().findTheKthLargestNum(nums, 3);
        new KthLargestNum().resolution2(nums, 3);


    }
}
