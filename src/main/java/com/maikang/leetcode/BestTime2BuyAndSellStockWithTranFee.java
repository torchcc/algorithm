package com.maikang.leetcode;

/*
#741
 */
public class BestTime2BuyAndSellStockWithTranFee {
    public static int maxProfit(int[] prices, int fee) {
        int hold = -fee - prices[0];
        int notHold = 0;
        int oldHold;
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            oldHold = hold;
            hold = Math.max(notHold - fee - prices[i], hold);
            notHold = Math.max(oldHold + prices[i], notHold);
            ans = Math.max(hold, notHold);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[] {1, 3, 2, 8, 4, 9}, 2) == 8);
    }
}
