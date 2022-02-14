package com.maikang.datastructure.newcoder;


import java.util.*;

public class TwentyFourPoint {
    private static Map<String, Integer> stoi;
    private static Map<Integer, String> itos;
    private static String ans = null;
    private static boolean backtrack(int[] arr, int val, String route, int len) {
        if (len == arr.length) {
            if (val == 24) {
                ans = route;
                return true;
            }
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                int tmp = arr[i];
                arr[i] = 0;
                if (backtrack(arr, val + tmp,  route + "+" + itos.get(tmp), len+1)) return true;
                if (backtrack(arr, val - tmp, route + "-" + itos.get(tmp), len+1)) return true;
                if (backtrack(arr, val * tmp, route + "*" + itos.get(tmp), len+1)) return true;
                if (backtrack(arr, val / tmp, route + "/" + itos.get(tmp), len+1)) return true;
                arr[i] = tmp;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        stoi = new HashMap<>();
        stoi.put("3", 3);
        stoi.put("4", 4);
        stoi.put("5", 5);
        stoi.put("6", 6);
        stoi.put("7", 7);
        stoi.put("8", 8);
        stoi.put("9", 9);
        stoi.put("10", 10);
        stoi.put("J", 11);
        stoi.put("Q", 12);
        stoi.put("K", 13);
        stoi.put("A", 1);
        stoi.put("2", 2);

        itos = new HashMap<>();
        itos.put(3, "3");
        itos.put(4, "4");
        itos.put(5, "5");
        itos.put(6, "6");
        itos.put(7, "7");
        itos.put(8, "8");
        itos.put(9, "9");
        itos.put(10, "10");
        itos.put(11, "J");
        itos.put(12, "Q");
        itos.put(13, "K");
        itos.put(1, "A");
        itos.put(2, "2");


        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] cards = line.split(" ");
            int[] arr = new int[4];
            int p = 0;
            boolean error = false;
            for (String s : cards) {
                if (s.toLowerCase().equals("joker") || !stoi.containsKey(s)) {
                    System.out.println("ERROR");
                    error = true;
                    break;
                } else arr[p++] = stoi.get(s);
            }
            // permutation, need visited
            if (!error) {
                boolean hasAns = false;
                for (int i = 0; i < 4; i++) {
                    int val = arr[i];
                    arr[i] = 0;
                    if(hasAns = backtrack(arr, val, "" + itos.get(val), 1)) break;
                    arr[i] = val;
                }
                if (hasAns) System.out.println(ans);
                else System.out.println("NONE");
            }
        }
    }
}