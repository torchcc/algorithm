package com.maikang.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (n < 4) return ans;

        for (int i = 0; i <= n-4; i++ ) {
            if (i != 0 && nums[i]==nums[i-1]) continue;
            for (int j=i+1; j <= n-3 && i<j; j++) {
                if (j != i+1 && nums[j] ==nums [j-1]) continue;
                int k = j+1;
                int l = n-1;
                while (k<l && j<k) {
                    int sum = nums[i] + nums[j] + nums[k] +nums[l];
                    if (sum > target){
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k++;
                        while (k < l && nums[k]==nums[k-1]) k++;
                        l--;
                        while (k < l && nums[l]==nums[l+1]) l--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {0,0,0,0};
        int [] b = {1,0,-1,0,-2,2};
        int target = 0;
        List<List<Integer>> lists = fourSum(b, target);
        System.out.println(lists);
    }

}
