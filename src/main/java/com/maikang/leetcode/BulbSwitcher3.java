package com.maikang.leetcode;

// I fucking did it by myself
// current light blue = privous blue
// if current is blue check if its front is on
// if (blueCount == onCount) ans++;
public class BulbSwitcher3 {
    public static int numTimesAllBlue(int[] light) {
        int[] status = new int[light.length+1]; // 0: off, 1: on, 2: blue
        int onCount = 0;
        int blueCount = 0;
        int ans = 0;
        for (int value : light) {
            int curLight = value;
            if (status[curLight] != 0) continue;
            onCount++;
            if (curLight == 1) {
                blueCount++;
                status[curLight++] = 2;
                while (curLight <= light.length && status[curLight] == 1) {
                    status[curLight++] = 2;
                    blueCount++;
                }
            } else {
                int prevLight = curLight - 1;
                if (status[prevLight] == 2) {
                    blueCount++;
                    status[curLight++] = 2;
                    while (curLight <= light.length && status[curLight] == 1) {
                        status[curLight++] = 2;
                        blueCount++;
                    }
                } else {
                    status[curLight] = 1;
                }
            }
            if (blueCount == onCount) ans++;

        }
        return ans;
    }

    public static void main(String[] args) {
        /*
        [2,1,3,5,4]
[3,2,4,1,5]
[4,1,2,3]
[2,1,4,3,6,5]
[1,2,3,4,5,6]
         */
        System.out.println(numTimesAllBlue(new int[]{2, 1, 3, 5, 4}) == 3);
        System.out.println(numTimesAllBlue(new int[]{3,2,4,1,5}) == 2);
        System.out.println(numTimesAllBlue(new int[]{4,1,2,3}) == 1);
        System.out.println(numTimesAllBlue(new int[]{2,1,4,3,6,5}) == 3);
        System.out.println(numTimesAllBlue(new int[]{1,2,3,4,5,6}) == 6);

    }
}
