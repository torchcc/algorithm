package com.maikang.datastructure.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

// a := []int{1,3,5,5,7,7,9}
// b := []int{2,3,5,5,7,9,10,12}
// return []int{3,5,5,6,9}
 */
public class GetDuplicate {

    public int[] resolve(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < m && j < n) {
            int num1 = arr1[i];
            int num2 = arr2[j];
            if (num1 < num2) {
                i++;
            } else if (num1 > num2) {
                j++;
            } else {
                list.add(num1);
                i++;
                j++;
            }

        }
        return list.stream().mapToInt(d->d).toArray();
    }

    public static void main(String[] args) {
        int[] arr1 = {1,3,5,5,7,7,9};
        int[] arr2 = {2,3,5,5,7,9,10,12};
        int[] resolve = new GetDuplicate().resolve(arr1, arr2);
        System.out.println(Arrays.toString(resolve));
    }

}
