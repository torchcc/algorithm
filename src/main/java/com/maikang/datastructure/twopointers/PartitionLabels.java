package com.maikang.datastructure.twopointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        int[] count = new int[26];
        char[] arr = s.toCharArray();
        for (char c : arr) count[c-'a']++;
        int lo = 0;
        do {
            Map<Character, Integer> map = new HashMap<>();
            map.put(arr[lo], count[arr[lo]-'a']);
            int hi = helper(arr, lo, count, map);
            ans.add(hi - lo +1);
            lo = hi+1;
        } while (lo < arr.length);
        return ans;
    }

    private int helper(char[] arr, int lo, int[] count, Map<Character, Integer> map) {
        int j = lo;
        while (j < arr.length) {
            char c = arr[j];
            if (map.containsKey(c)) {
                Integer number = map.get(c);
                if (number == 1) map.remove(c);
                else map.put(c, number - 1);
            } else {
                if (count[c-'a'] > 1)
                    map.put(c, count[c-'a'] -1);
            }
            if (map.size() == 0) return j;
            j++;
        }
        System.out.println("error!");
        return -1;
    }

    public static void main(String[] args) {
        PartitionLabels p = new PartitionLabels();
        System.out.println(p.partitionLabels("ababcbacadefegdehijhklij"));
    }
}