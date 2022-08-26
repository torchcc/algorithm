package com.maikang.datastructure.dp;

import java.util.Arrays;

class lengthOfLIS {
    public static void main(String[] args) {
        lengthOfLIS s = new lengthOfLIS();
//        int[][] arr = {{4,5},{4,6},{6,7},{2,3},{1,1},{1,1}};
//        System.out.println(s.maxEnvelopes(arr));
        int[] A = {1, 1, 3, 6, 5, 7};
        s.lenOfLIS(A);

    }
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        int[] A = new int[n];
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        for (int i = 0; i < n; i++) A[i] = envelopes[i][1];
        return lenOfLIS(A);
    }

//       public int lengthOfLIS(int[] nums) {
//         List<Integer> list = new ArrayList<> () {{add(nums[0]);}};
//         for (int i = 1; i < nums.length; i++) {
//             int num = nums[i];
//             if (num > list.get(list.size()-1)) list.add(num);
//             else {
//                 int idx = ceiling(list, num);
//                 list.set(idx, num);
//             }
//         }
//         return list.size();
//     }

//     private int ceiling(List<Integer> list, int target) {
//         int lo = 0, hi = list.size();
//         while (lo < hi) {
//             int m = lo + (hi - lo) / 2;
//             if (list.get(m) >= target) hi = m;
//             else lo = m + 1;
//         }
//         return lo;
//     }

    private int lenOfLIS(int[] A) {
        int[] dp = new int[A.length];
        int len = 0;
        for (int num : A) {
            int idx = ceiling(dp, num, 0, len);
            System.out.println(idx);
            dp[idx] = num;
            if (idx == len) len++;
        }
        return len;
    }

    private int ceiling(int[] A, int target, int lo, int hi) {
        while (lo < hi) {
            int m = lo + (hi - lo) / 2;
            if (A[m] >= target) hi = m;
            else lo = m + 1;
        }
        return lo;
    }
}