package com.maikang.datastructure.newcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsecutiveLongestDigits {
    private static int maxLen = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            List<String> ans = helper(line);
            for(String s : ans) System.out.print(s);
            System.out.println("," + ans.get(0).length());
        }
    }

    private static List<String> helper(String s) {
        int n = s.length();
        int i = 0;
        int curMaxLen = 0;
        List<String> ans = new ArrayList<>();
        while (i < n) {
            while (i < n && !Character.isDigit(s.charAt(i))) i++;
            if (i == n) break;
            int j = i;
            while (j < n && Character.isDigit(s.charAt(j))) j++;
            if (j-i >curMaxLen) {
                ans = new ArrayList<>();
                curMaxLen = j-i;
                ans.add(s.substring(i, j));
            } else if (j - i == curMaxLen) {
                ans.add(s.substring(i, j));
            }
            i = j;
        }
        return ans;
    }

}
