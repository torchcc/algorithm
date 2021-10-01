package com.maikang.leetcode;

import java.util.HashSet;
import java.util.Stack;

/**
 *
 */
public class Infix2Postfix {
   private boolean isOperand(Character c) {
       return c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')';
   }

   private boolean isPriorOrEqualTo(Character x, Character y) {
       HashSet<Character> lower = new HashSet<>() {{
           add('+');
           add('-');
       }};
       HashSet<Character> higher= new HashSet<>() {{
           add('*');
           add('/');
       }};
       return lower.contains(x) && lower.contains(y) || higher.contains(x) && higher.contains(y) || higher.contains(x) && lower.contains(y);
   }

    public String infix2Postfix(String infix) {
        int n = infix.length();
        Stack<Character> output = new Stack<>();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = infix.charAt(i);
            if (isOperand(c)) {
                output.push(c);
            } else if (c == '('){
                stack.push(c);
            } else if (c == ')') {
//                Character curChar = stack.pop();
//                while (curChar != '(') {
//                    output.push(curChar);
//                    curChar = stack.pop();
//                }
                while (output.push(stack.pop()) != '(') {
                }
                output.pop();
            } else {
                while (!stack.isEmpty() && isPriorOrEqualTo(stack.peek(), c)) {
                    output.push(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            output.push(stack.pop());
        }

        StringBuilder stringBuilder = new StringBuilder(stack.size());
        while (!output.isEmpty()) {
            stringBuilder.append(output.pop());
        }
        return stringBuilder.reverse().toString();
    }

    public Integer calculate(String postfix) {
        char[] chars = postfix.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (isOperand(chars[i])) {
                stack.push(chars[i]-'0');
            } else {
                Integer operandRight = stack.pop();
                Integer operandLeft = stack.pop();
                stack.push(simpleCalculate(operandLeft, operandRight, chars[i]));
            }
        }
        return stack.pop();
    }

    private Integer simpleCalculate(Integer operandLeft, Integer operandRight, char operator) {
       switch (operator) {
           case '+': return operandLeft + operandRight;
           case '-': return operandLeft - operandRight;
           case '*': return operandLeft * operandRight;
           case '/': return operandLeft / operandRight;
           default: return null;
       }
    }


    public static void main(String[] args) {
        String express = "a+b*c+(d*e+f)*g";
        String postfix = new Infix2Postfix().infix2Postfix(express);
        System.out.println(postfix);
        System.out.println(postfix.equals("abc*+de*f+g*+"));
        String express2 = "1+2*3+(4*5+6)*7"; // 27*7 = 189
        Infix2Postfix calculator = new Infix2Postfix();
        System.out.println(calculator.calculate(calculator.infix2Postfix(express2)));
    }
}
