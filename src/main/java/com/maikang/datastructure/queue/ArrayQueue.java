package com.maikang.datastructure.queue;

import java.util.*;

/**
 * cyclic queue.
 */
public class ArrayQueue {
    private int maxSize;
    private int len;
    private int front;
    private int rear;
    private int[] con;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;  // points to the previous position of the font element.
        rear = -1;  // points to the tail element
        this.con = new int[maxSize];
    }

    private boolean isEmpty() {
        return len == 0;
    }

    private boolean isFull() {
        return len == maxSize;
    }

    public void push(int x) {
        if (isFull()) {
            System.out.println("queue is full , cant push any new element");
            return;
        }
        con[(++rear)%maxSize]= x;
        len++;

    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("empty, cant pop any element");
        }
        len--;
        return con[(++front)%maxSize];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("empty, cant peek any element");
        }
        return con[(front+1)%maxSize];
    }

    public static void main(String[] args) {
//        int[] a = new int[5];
//        Deque<Integer> q = new ArrayDeque<>();
//        new LinkedList<>()
//
//        Queue<Integer> integerQueue = new ArrayDeque<>();
//        integerQueue.
//        int length = a.length;
//        Stack<Integer> integers = new Stack<>();
//        integers.pop()
//        Map<Integer, Integer> map = new HashMap<>();
//        ArrayQueue q= new ArrayQueue(3);
//        map.remo
//        System.out.println("hi");
    }
}
