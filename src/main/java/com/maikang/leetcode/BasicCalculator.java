package com.maikang.leetcode;

import java.util.Stack;

class BasicCalculator {
    private int priority(char c) {
        if (c == '+' || c == '-') {
            return 1;
        }
        if (c == '*' || c == '/') {
            return 2;
        }
        return -1;
    }
    private int calculate(int x, int y, char c) {
        switch (c) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x/y;
            default:
                return -1;
        }
    }
    public int calculate(String s) {
        Stack<Integer> operants = new Stack();
        Stack<Character> operators = new Stack();
        int ans = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < chars.length && Character.isDigit(chars[i])) {
                    sb.append(chars[i++]);
                }
                i--;
                int x = Integer.valueOf(sb.toString());
                operants.push(x);
            } else { // meet a operator
                if (!operators.isEmpty()) {
                    char top = operators.peek();
                    while (priority(top) >= priority(c)) {
                        operators.pop();
                        int y = operants.pop();
                        int x = operants.pop();
                        int z = calculate(x, y, top);
                        operants.push(z);
                    }
                } 
                operators.push(c);
            }
        }
        while (!operators.isEmpty()) {
            int y = operants.pop();
            int x = operants.pop();
            char op = operators.pop();
            int z = calculate(x, y, op);
            operants.push(z);
        }
        return operants.pop();
    }

    public static void main(String[] args) {
        BasicCalculator c = new BasicCalculator();
        String s = "1*2-3/4+5*6-7*8+9/10";
        System.out.println(c.calculate(s));
    }
}