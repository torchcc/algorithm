package com.maikang.datastructure.dp;

class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n == 0) return 0;
        s = ")" + s;
        System.out.println(s);
        int[] dp = new int[s.length()];
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(i);
            
            if (c == ')' && i - dp[i-1] - 1 >=0 && s.charAt(dp[i-dp[i-1]-1]) == '(') {
                System.out.println(dp[i]);
                dp[i] = 2 + dp[i-1] + dp[i-dp[i-1]-2];
                System.out.println(dp[i]);
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LongestValidParentheses().longestValidParentheses("(()"));
    }
}