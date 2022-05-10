package com.maikang.datastructure.newcoder;

import java.util.Arrays;

/**
0  n-1
1 n-2
x  + y = n-1
y = n-1-
**/
class Session2FinishTasks {
    private int ans , sessionTime;
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        ans = n;
        Arrays.sort(tasks);
        for (int i = 0; i < n/2; i++) {
            int tmp = tasks[i];
            tasks[i] = tasks[n-1-i];
            tasks[n-1-i] = tmp;
        }
        System.out.println(Arrays.toString(tasks));
        this.sessionTime = sessionTime;
        backtrack(tasks, 0, 0, 0);
        return ans;
    }
    private void backtrack(int[] tasks, int remainTime, int done, int count) {
        // prunning
        if (count >= ans) return;
        if (done == tasks.length) {
            ans = Math.min(ans, count);
            return;
        }
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == -1) continue;
            int needTime = tasks[i] ;
            tasks[i] = -1;
            if (needTime > remainTime) backtrack(tasks, sessionTime-needTime, done+1, count+1);
            else backtrack(tasks, remainTime - needTime, done+1, count);
            tasks[i] = needTime;
        }
    }
}