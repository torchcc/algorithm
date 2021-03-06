package com.maikang.leetcode;

/*
You are given an array prices for which the ith element is the price of a given stock on day i.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

 Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e., max profit = 0.
 */


public class BestTime2BuyAndSellStock2 {
    public static int maxProfit(int[] prices) {
        int ans = 0;
        int diff = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                ans += diff;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        /*
        [7,1,5,3,6,4]
[7,6,4,3,1]
expected: 5, 0
         */

        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}) == 7);
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}) == 0);
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}) == 4);
    }
}
