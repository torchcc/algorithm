package com.maikang.datastructure.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class OpenLock {
    public int openLock(String[] deadends, String target) {
        String start = "0000";
        Set<String> dead = new HashSet<>();
        for (String s : deadends) dead.add(s);
        if (dead.contains(start)) return -1;
        
        Set<String> visited = new HashSet<>();
        visited.add(start);
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int len = queue.size();
            for (int k = 0; k < len; k++) {
                String curString = queue.poll();
                char[] arr = curString.toCharArray();
                for (int i = 0; i < 4; i++) {
                    for (int j = -1; j <= 1; j += 2) {
                        arr[i] = (char)((arr[i] - '0' + j + 10) % 10 + '0');
                        String neiString = String.valueOf(arr);
                        if (neiString.equals(target)) return step;
                        if (visited.contains(neiString) || dead.contains(neiString)) continue;
                        queue.offer(neiString);
                        visited.add(neiString);
                    }
                }
            }
        }
        return -1;
    }
}