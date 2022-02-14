package com.maikang.datastructure.newcoder;

import java.util.*;

public class TicketCounting {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int invalid = 0;

            int candidateNum = sc.nextInt();
            sc.nextLine();
            String[] names = sc.nextLine().split(" ");
            Map<String, Integer> map = new HashMap<>();
            for (String name : names) map.put(name, 0);

            int ticketNum = sc.nextInt();
            sc.nextLine();
            String[] ticketNames = sc.nextLine().split(" ");
            for (String name : ticketNames) {
                if (!map.containsKey(name)) invalid++;
                else map.put(name, map.get(name)+1);
            }

            for (String name : names) {
                System.out.println(name + " : " + map.get(name));
            }
            System.out.println("Invalid : " + invalid);
        }
    }
}
