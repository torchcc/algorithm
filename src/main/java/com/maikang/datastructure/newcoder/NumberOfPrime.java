package com.maikang.datastructure.newcoder;

import java.util.Arrays;

public class NumberOfPrime {


    public static void main(String[] args) {
        NumberOfPrime m = new NumberOfPrime();
        System.out.println(m.numOfPrime(20));

    }


    // 21:06  25
//    [0, 1, 2, 3, 4, 5, 6..., 100];
//
//    [false, false, true, true, false....] //true
    public int numOfPrime(int n) {
        boolean[] arr = new boolean[n+1];
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;
        for (int i = 2; i <= n; i++) {
            if (!arr[i]) continue;
            for (int j = i+1; j <= n; j++) {
                if (j % i == 0) arr[j] = false;
            }
        }
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            if (arr[i])
                ans++;
        }
        return ans;
    }
}
