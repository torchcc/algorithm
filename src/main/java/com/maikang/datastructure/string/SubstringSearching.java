package com.maikang.datastructure.string;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Robin Karl string searching.
 */
public class SubstringSearching {
    private static int m;

    public static boolean contains(String s, String t) {
        m = t.length();
        if (m==0) return true;
        int n = s.length();
        HashHelper hashS = new HashHelper();
        HashHelper hashT = new HashHelper();
        for (int i = 0; i < t.length(); i++) {
            hashS.append(s.charAt(i));
            hashT.append(t.charAt(i));
        }
        int j = 0;
        int k = m-1;
        while (k < n-1) {
            if (hashS.getHash() == hashT.getHash()) {
                if (equal(t, s, j)) {
                    return true;
                }
            }
            j++;
            k++;
            hashS.append(s.charAt(k));
        }
        return false;
    }

    private static boolean equal(String t, String s, int j) {
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) != s.charAt(j)) {
                return false;
            }
            j++;
        }
        return true;
    }


    public void containsTest() {
        String source = "jhhhhhhh99h{{{{}}}}}{KKKJJello worldh";
        String target = "ello";
        Assertions.assertTrue(contains(source, target));
        Assertions.assertFalse(contains("hello world", "hel l"));
        Assertions.assertTrue(contains(source, ""));
        Assertions.assertTrue(contains(source, "}"));
    }

    private static class HashHelper {
        private int hashSum = 0;
        private int hashTableSize = 256;
        private Queue<Character> q = new ArrayDeque<>();

        private int append(Character c) {
            q.offer(c);
            hashSum += (int)c;
            if (q.size() > m) {
                Character poll = q.poll();
                hashSum -= (int) poll;
            }
            return hashSum%hashTableSize;
        }

        private int getHash() {
            return hashSum % hashTableSize;
        }
    }


}
