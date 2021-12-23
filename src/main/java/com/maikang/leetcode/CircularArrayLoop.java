package com.maikang.leetcode;

// to validate a cycly, use fast slow pointer
// https://leetcode.com/problems/circular-array-loop/discuss/595004/C%2B%2B-100-(Using-Floyd's-Cycle-Detection-Algorithm)-O(n)-time-O(1)-space
// 快慢指针, 把visit过的标记为0
class CircularArrayLoop {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        int fast = 0;
        int slow = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;
            fast = i;
            slow = i;
            System.out.println("fast is " + fast + " slow is " + slow);
            while (nums[slow] * nums[next(nums, slow)] > 0 &&
                    nums[fast] * nums[next(nums, fast)] > 0 && 
                    nums[fast] * nums[next(nums, next(nums, fast))] > 0) {
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
                if (fast == slow) {
                    if (slow == next(nums, slow)) break; // means the cycle length is 1
                    return true;
                }
            }
            // mark all the visited 
            slow = i;
            int value = nums[slow];
            while (value * nums[slow] > 0) {
                int tmp = slow;
                slow = next(nums, slow);
                nums[tmp] = 0;
            }
        }
        return false;
    }
    
    private int next(int[] nums, int idx) {
        return (idx + nums[idx] + (nums.length) ) % (nums.length);
    }

    public static void main(String[] args) {
        int[] nums = {-2, -3, -9};
        new CircularArrayLoop().circularArrayLoop(nums);
    }
    
}