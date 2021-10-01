package com.maikang.leetcode;

import java.util.Arrays;

/*
Given an array nums of n integers and an integer target,
 find three integers in nums such that the sum is closest to target.
  Return the sum of the three integers.
  You may assume that each input would have exactly one solution.

 */
// 夹逼指针，
// deduplicate
// 每夹一次更新一下结果
public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        int curSum = nums[0] + nums[1] + nums[2];
        int curDiff = Math.abs(curSum - target);
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length-3; i++) {
            int j = i + 1;
            int k = nums.length -1;
            while (i < j && j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(sum-target);
                if (sum > target) {
                    k--;
                    while (nums[k]==nums[k+1] && k>j) k--;

                } else {
                    j++;
                    while(nums[j] == nums[j-1] && j < k) j++;
                }
                if (diff < curDiff) {
                    curSum = sum;
                    curDiff = diff;
                }
            }
        }
        return curSum;
    }

    public static void main(String[] args) {
        int[] a = {-1,2,1,-4};
        System.out.println(threeSumClosest(a, 1));
    }
}
