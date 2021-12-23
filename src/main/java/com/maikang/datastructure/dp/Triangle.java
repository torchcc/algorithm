package com.maikang.datastructure.dp;

import java.util.Arrays;

class Triangle {
    private int[][] dp;
    private int[] values;
    // memoization
    public int minScoreTriangulation(int[] values) {
        this.values = values;
        this.dp = new int[values.length][values.length];
        for (int i = 0; i < values.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        return recurse(0, values.length-1);

    }

    private int recurse(int i, int j) {
        if (dp[i][j] == Integer.MAX_VALUE) {
            if (j - i == 1) {
                dp[i][j] = 0;
            } else {
                for (int x = i+1; x < j; x++) {
                    dp[i][j] = Math.min(dp[i][j], values[i] * values[j] * values[x] + recurse(i, x) + recurse(x, j));
                }
            }
        }
        return dp[i][j];
    }

    // tabulation:
    public int tab(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int j = 2; j < n; j++) {
            for (int i = j-2; i >= 0; i--) { // 为什么i要从j-2开始自减而不是从0开始自增？因为若然， dp[k][j]由于之前没有遍历到，它的值是未知的。
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i+1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], values[i] * values[j] * values[k] + dp[i][k] + dp[k][j]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        /*
        * [1,2,3] 6
           [1,3,1,4,1,5] 13
        * */
        int[] values = new int[] {1,3,1,4,1,5};
        System.out.println(new Triangle().minScoreTriangulation(values));
    }
}