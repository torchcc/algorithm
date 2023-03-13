package com.maikang.datastructure.newcoder;

import java.util.Arrays;

public class NumberOfPrime {


    public static void main(String[] args) {
        NumberOfPrime m = new NumberOfPrime();
        System.out.println(m.numOfPrime(20));

    }


//    [0, 1, 2, 3, 4, 5, 6..., 100];
//
//    [false, false, true, true, false....] //true

    /**
     * TC: n * (log log n)
     * start to delete from index i*i, delete those indices that are multiple of index i
     * @param n
     * @return
     */
    public int numOfPrime(int n) {
        boolean[] arr = new boolean[n+1];
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;
        for (int i = 2; i < (int)Math.sqrt(n); i++)
            if (arr[i])
                for (int j = i * i; j < n; j += i)
                    arr[j] = false;

        int ans = 0;
        for (int i = 0; i <= n; i++)
            if (arr[i])
                ans++;
        return ans;
    }
}
