package com.maikang.leetcode;

/*
A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S, where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.

Suppose the first element in S starts with the selection of element A[i] of index = i, the next element in S should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.

Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation:
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}

 */
class ArrayNesting {
    public static int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            int count = 0;
            int start = nums[i];
            while (true) {
                start = nums[start];
                count++;
                visited[start] = true;
                if (start == nums[i]) break;
            }
            if (count > ans) ans = count;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[] {5,4,0,3,1,6,2};
        System.out.println(arrayNesting(a) == 4);
        System.out.println(arrayNesting(new int[]{5, 4, 0, 7, 3, 1, 6, 2})==7);
        System.out.println(arrayNesting(new int[]{5,8,4,0,7,3,1,6,2})==67);
    }
}
