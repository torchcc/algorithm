package com.maikang.datastructure.topologicalsort;

import java.util.*;

// 12:13
class CourseScheduleIV {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        // build the graph and indegree
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[n];
        for (int[] pair : prerequisites) {
            graph[pair[0]].add(pair[1]);
            indegree[pair[1]]++;
        }
        // build the prereq set 
        Queue<Integer> queue = new LinkedList<>();
        Set[] preSet = new Set[n];
        for (int i = 0; i < n; i++) {
            preSet[i] = new HashSet<Integer> ();
            preSet[i].add(i);
            if (indegree[i] == 0) queue.offer(i);
        }
        
        while(!queue.isEmpty()) {
            int curNode = queue.poll();
//            List<Integer> neis = graph[curNode];
            for (Integer nei : graph[curNode]) {
                preSet[nei].addAll(preSet[curNode]);
                if (--indegree[nei] == 0) queue.offer(nei);
            }
        }
        List<Boolean> ans = new ArrayList<> ();
        for (int[] q : queries) ans.add(preSet[q[1]].contains(q[0]));
        return ans;

    }
}