package com.maikang.datastructure.sort;

import java.util.*;

public class AlienDict {


    public static void main(String[] args) {
//        String[] words = {"caa", "aaa", "aab"}; // 'c', 'a', 'b'
//        String[] words = {"hello","leetcode"};
        String[] words = {"baa", "abcd", "abca", "cab", "cad"}; // 'b', 'd', 'a', 'c'
        AlienDict m = new AlienDict();
        System.out.println(m.alienOrder(words));
    }

    public String alienOrder(String[] words) {
        // 1. build adjacency list
        List<Integer>[] graph = new List[26];
        for (String w : words) {
            for (char c : w.toCharArray()) {
                if (graph[c - 'a'] == null) graph[c - 'a'] = new ArrayList<Integer>();
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String prev = words[i];
            String next = words[i + 1];
            if (prev.length() > next.length() && prev.startsWith(next)) {
                return "";
            }
            for (int j = 0; j < Math.min(prev.length(), next.length()); j++) {
                if (prev.charAt(j) != next.charAt(j)) {
                    graph[prev.charAt(j) - 'a'].add(next.charAt(j) - 'a');
                    break;
                }
            }
        }

        // 2 topological sort
        int[] visited = new int[26]; // 0: unvisited. 1 visiting 2 visited;
        StringBuilder sb = new StringBuilder();
        int size = 0;
        for (int i = 0; i < graph.length; i++) {
            if (graph[i] != null) {
                size++;
                if (hasCycle(i, visited, sb, graph)) return "";
            }
        }
        if (sb.length() != size) return "";
        return sb.reverse().toString();

    }

    private boolean hasCycle(int u, int[] visited, StringBuilder stk, List<Integer>[] graph) {
        if (visited[u] == 1) return true;
        if (visited[u] == 2) return false;
        visited[u] = 1;
        for (int child : graph[u]) {
            if (hasCycle(child, visited, stk, graph)) return true;
        }
        visited[u] = 2;
        stk.append((char) (u + 'a'));
        return false;
    }
}
