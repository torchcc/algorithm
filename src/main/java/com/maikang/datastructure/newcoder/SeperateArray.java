package com.maikang.datastructure.newcoder;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//
public class SeperateArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int sum5 = 0;
            int sum3 = 0;
            int sum = 0;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int cur = sc.nextInt();
                if (cur % 5 == 0)
                    sum5 += cur;
                else if (cur %3 == 0)
                    sum3 += cur;
                else list.add(cur);
                sum += cur;
            }
            int target = sum / 2 - sum3;
            if (sum % 2 != 0) System.out.println("false");
            else System.out.println(helper(list, target));
        }
    }

    private static boolean helper(List<Integer> list, int target) {
        return helper(0, list, target);
    }

    private static boolean helper(int i, List<Integer> list, int target) {
        if (i == list.size()) return target == 0;
        return helper(i+1, list, target-list.get(i)) || helper(i+1, list, target);
    }
}
