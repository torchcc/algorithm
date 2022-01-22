package com.maikang.datastructure.sort.bitmanipulation;

public class AbsWithBit {

    public static void main(String[] args) {
        int number  = -1;
        System.out.println(number + "的绝对值是:" + ((number^(number>>31))-(number>>31)));
    }
}
