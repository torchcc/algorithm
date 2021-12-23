package com.maikang.datastructure.disjoinset;

import com.maikang.datastructure.stack.ListNode;

import java.util.*;

/**
https://www.youtube.com/watch?v=UwpvInpgFmo
disjoint-set / union-find
**/

class EvaluateDivision{
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(List.of("a", "b"));
        equations.add(List.of("b", "c"));
        equations.add(List.of("d", "e"));
        equations.add(List.of("a", "d"));
        double[] values = {1.0, 2.0, 3.0, 4.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(List.of("c", "e"));
        double[] ans = new EvaluateDivision().calcEquation(equations, values, queries);
        String s = Arrays.toString(ans);
        System.out.println(s);

    }
    Map<String, List<Object>> parents;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        parents = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double weight = values[i];
            if (!parents.containsKey(u) && !parents.containsKey(v)) {
                parents.put(u, new ArrayList<Object>() {{ add(v); add(weight);}});
                parents.put(v, new ArrayList<Object>() {{ add(v); add(1.0);}});
            }  else if (!parents.containsKey(u)) {
                parents.put(u, new ArrayList<>() {{ add(v); add(weight);}});
            } else if (!parents.containsKey(v)) {
                parents.put(v, new ArrayList<>() {{ add(u); add(1.0/weight);}});
            }else {
                List<Object> pu = find(u);
                List<Object> pv = find(v);
                 if (!pu.get(0).equals(pv.get(0))) {
                    pu.set(0, pv.get(0));
                     Double o = (Double)pu.get(1);
                     Double o2 = (Double)pv.get(1);
                     pu.set(1, o * o2 * weight );

                }
            }
        }

        List<Double> ans = new ArrayList<>();
        for (List<String> q: queries) {
            String u = q.get(0);
            String v = q.get(1);
            if (!parents.containsKey(u) || !parents.containsKey(v)) {
                // System.out.println(u + "=====" + v);
                ans.add(-1.0);
                continue;
            }
            List<Object> pu = find(u);
            List<Object> pv = find(v);
            if (!pu.get(0).equals(pv.get(0))) {
                // System.out.println(u + "-------" + v);
                ans.add(-1.0);
                continue;
            }
            ans.add((Double) pu.get(1)/ (Double) pv.get(1));
        }
        return ans.stream().mapToDouble(d->d).toArray();
    }

    private List<Object> find(String u) {
        if (!u.equals(parents.get(u).get(0))) {
            List<Object> p = find((String) parents.get(u).get(0));
            parents.get(u).set(0, p.get(0));
            parents.get(u).set(1, (Double)parents.get(u).get(1) * (Double)p.get(1));
        }
        return parents.get(u);
    }
}

class Pair {
    public String parent;
    public Double weight;

    public Pair(String p, Double w) {
        parent = p;
        weight = w;
    }

    public String toString() {
        return "[" + parent + " <> " + weight + "]";
    }

    public Pair() {

    }
}