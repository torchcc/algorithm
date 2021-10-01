package com.maikang.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 夹逼指针， 对象指针
// deduplicate
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int i = 0;
        int j = i + 1;
        int k = nums.length - 1;
        while (j < k && i < j) {
            while (i < j && j < k) {
                if (nums[j] + nums[k] > -nums[i]) {
                    k--;
                } else if (nums[j] + nums[k] < -nums[i]) {
                    j++;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    k--;
                    while (j < k && nums[k] == nums[k+1]) k--;
                    j++;
                    while (j < k && nums[j] == nums[j-1]) j++;
                }
            }
            i++;
            while (i < nums.length - 2 && nums[i] == nums[i-1]) i++;
            j = i + 1;
            k = nums.length - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {-1, 0, 1, 2, -1, -4};
//        int[] a = {-2,0,0,2,2};

        List<List<Integer>> lists = threeSum(a);
        System.out.println(lists);
    }

}
