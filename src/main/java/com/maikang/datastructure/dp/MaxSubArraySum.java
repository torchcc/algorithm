package com.maikang.datastructure.dp;

public class MaxSubArraySum {

    /*
    *
    * 输入：nums = [1]
输出：1
示例 3：

输入：nums = [5,4,-1,7,8]
输出：23
    *
    *
    *
    * */
    public static void main(String[] args) {
        int[] nums = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        MaxSubArraySum myTest = new MaxSubArraySum();
        System.out.println(myTest.maxSum(nums));
        nums = new int[] {1};
        System.out.println(myTest.maxSum(nums));
        nums = new int[] {5,4,-1,7,8};
        System.out.println(myTest.maxSum(nums));
        nums = new int[] {-1, -2, -3};
        System.out.println(myTest.maxSum(nums));
    }
// ：nums = [-2,1,-3,4,-1,2,1,-5,4]
    // ：连续子数组 [4,-1,2,1] 的和最大，为 6 。
    public int maxSum(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int curMax = 0;
        for (int num : nums) {
            curMax = Math.max(num, curMax + num);
            ans = Math.max(ans, curMax);
        }
        return ans;

    }


}
