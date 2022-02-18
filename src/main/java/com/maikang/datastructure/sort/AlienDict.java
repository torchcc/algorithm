package com.maikang.datastructure.sort;

import java.util.*;

public class AlienDict {


    public static void main(String[] args) {
//        String[] words = {"caa", "aaa", "aab"}; // 'c', 'a', 'b'
//        String[] words = {"hello","leetcode"};
        String[] words = {"baa","abcd","abca","cab","cad"}; // 'b', 'd', 'a', 'c'
        AlienDict m = new AlienDict();
        System.out.println(m.order(words));
    }

    private String order(String[] words) {
        // 1. build adjacency list
        List<Integer>[] graph = new List[26];
        for (String w : words) {
            for (char c : w.toCharArray()) {
                if (graph[c-'a'] == null) graph[c-'a'] = new ArrayList<Integer>();
            }
        }
        for (int i = 0; i < words.length-1; i++) {
            String prev = words[i];
            String next = words[i+1];
            for (int j = 0; j < Math.min(prev.length(), next.length()); j++) {
                if (prev.charAt(j) != next.charAt(j)) {
                    graph[prev.charAt(j)-'a'].add(next.charAt(j)-'a');
                    break;
                }
            }
        }

        // 2 topological sort
        boolean[] visited = new boolean[26]; // 0: unvisited. 1 visiting 2 visited;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i] && graph[i] != null) {
                topologicalSort(i, visited, sb, graph);
            }
        }
        return sb.reverse().toString();

    }

    private void topologicalSort(int u, boolean[] visited, StringBuilder stk, List<Integer>[] graph) {
        if (visited[u]) return ;
        for (int child : graph[u]) {
            topologicalSort(child, visited, stk, graph);
        }
        visited[u] = true;
        stk.append((char) (u + 'a'));
    }
}
