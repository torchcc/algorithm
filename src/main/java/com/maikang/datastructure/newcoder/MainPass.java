package com.maikang.datastructure.newcoder;

import java.util.*;
public class MainPass {

    private static String format(int n) {
        StringBuilder sb = new StringBuilder("" + n);
        int diff = 4 - sb.length();
        for (int i = 0; i < diff; i++) {
            sb.append('*');
        }
        return new String(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int n = sc.nextInt();
            List[] arr = new List[n];
            for (int i = 0; i < n; i++) arr[i] = new ArrayList<>();
            int curNum = 1;
            for (int i = 1; i <= n; i++) {
                List<Integer> list = arr[i-1];
                int j = curNum;
                for (; j < curNum+i; j++) list.add(j);
                curNum = j;
                if (i % 2 == 0) Collections.reverse(list);
            }

            List<String> ans = new ArrayList<> ();
            int ident = 0;
            for (int i = n-1; i >=0; i--) {
                List<Integer> list = arr[i];
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < ident; j++) sb.append(' ');
                for (Integer num : list) {
                    String s = format(num);
                    sb.append(s);
                    sb.append("    ");
                }
                sb.delete(sb.length()-4, sb.length()-1);
                ans.add(new String(sb));
                ident += 4;
            }

            Collections.reverse(ans);
            for (String s : ans) System.out.println(s);
        }
    }
}