package com.maikang.datastructure.newcoder;

import org.xml.sax.helpers.AttributesImpl;

import java.io.LineNumberReader;
import java.util.Scanner;

/*
 *a{b{d,e{g,h{,i}}},c{f}}
 * */
public class inorderStringBinaryTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            dfs(line, sb);
            System.out.println(new String(sb));
        }
    }

    private static void dfs(String line, StringBuilder sb) {
        if (line == null || line.length() == 0) return;
        String[] nodes = getNode(line);
        dfs(nodes[1], sb);
        sb.append(nodes[0]);
        dfs(nodes[2], sb);
    }

    private static String[] getLeftRight(String line) {
        if (line == null || line.length() == 0) return new String[]{null, null};
        if (line.charAt(0) == ',') return new String[]{null, line.substring(1)};
        if (line.length() == 1) return new String[]{line, null};
        String[] ans = new String[2];
        if (line.charAt(1) == '{') {
            int opens = 1;
            int i = 2;
            for (; i < line.length(); i++) {
                if (line.charAt(i) == '{') opens++;
                else if (line.charAt(i) == '}') opens--;
                if (opens == 0) break;
            }
            ans[0] = line.substring(0, i + 1);
            if (i + 1 == line.length()) ans[1] = null;
            else ans[1] = line.substring(i + 2);
            return ans;
        }
        if (line.charAt(1) == ',') {
            ans[0] = line.substring(0, 1);
            ans[1] = line.substring(2);
            return ans;
        }
        return null;

    }

    private static String[] getNode(String line) {
        if (line == null) return new String[]{null, null, null};
        if (!line.contains("{")) return new String[]{line, null, null};
        int indexOfOpen = line.indexOf("{");
        int indexOfClose = -1;
        int opens = 1;
        for (int i = indexOfOpen + 1; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '{') opens++;
            else if (c == '}') opens--;
            if (opens == 0) {
                indexOfClose = i;
                break;
            }
        }
        String[] ans = new String[3];
        ans[0] = line.substring(0, indexOfOpen);
        String children = line.substring(indexOfOpen + 1, indexOfClose);
        System.out.println(line);
        String[] leftRight = getLeftRight(children);
        ans[1] = leftRight[0];
        ans[2] = leftRight[1];
        return ans;
    }
}
