package com.maikang.datastructure.twopointers;

class ReverseWordsInAString {
    public String reverseWords(String s) {
        int n = s.length();
        int i = 0;
        int j = i;
        StringBuilder sb = new StringBuilder();
        while (j < n) {
            while (j < n && j != ' ') j++;
            int p = j-1;
            while (p >= i) sb.append(s.charAt(p--));
            sb.append(' ');
            i = j++;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWordsInAString().reverseWords("Let's take LeetCode contest"));
    }
}