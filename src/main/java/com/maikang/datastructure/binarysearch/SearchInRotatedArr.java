package com.maikang.datastructure.binarysearch;

/**
1. find out the start idx using BS,  compare nums[mid] to nums[left] and nums[r]
2. use regular BS
3. always remember cornor cases
**/
class SearchInRotatedArr {

    public static void main(String[] args) {
        int[] nums = new int[] {4,5,6,7,0,1,2};
        int[] nums1 = new int[] {1};
        int[] nums2 = new int[] {3,5,1};
        int[] nums3 = new int[] {3,1};
        int[] nums4 = new int[] {1, 3};
        int[] nums5 = new int[] {5, 1, 3};
//        System.out.println(new SearchInRotatedArr().findStartIndex(nums));
//        System.out.println(new SearchInRotatedArr().findStartIndex(nums1));
//        System.out.println(new SearchInRotatedArr().findStartIndex(nums2));
//        System.out.println(new SearchInRotatedArr().findStartIndex(nums3));
//        System.out.println(new SearchInRotatedArr().findStartIndex(nums4));
//        System.out.println(new SearchInRotatedArr().findStartIndex(nums5));
        System.out.println(new SearchInRotatedArr().findStartIndex(null));

    }
    private int findStartIndex(int[] nums) {
        if (nums == null ) {
            return -1;
        }
        int n = nums.length;
        int l = 0;
        int r = n-1;
        while (l < r) {
            int m = l + (r - l) /2;
            if (nums[m] >= nums[l] && nums[m] <= nums[r]) {
                break;
            } else if (nums[m] < nums[l]) {
                r = m ;
            } else if (nums[m] > nums[r]) {
                l = m + 1;
            } else {
                System.out.println("Error");
            }
        }
        return l;
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        int l = 0;
        int r = n-1;
        // find out the start point. 
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[l] && nums[m] < nums[r]) {
                break;
            } else if (nums[m] < nums[l]) {
                r = m-1;
            } else if (nums[m] > nums[r]){
                l = m + 1;
            }
        }
        System.out.println("start idx is " + l);
        r = l + n ;
        while (l < r) {
            int m = l + (r - l) /2;
            if (nums[m%n] == target) return m%n;
            else if (nums[m%n] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}