package com.maikang.datastructure.backtracking;

class SplitStringIntoConsecutiveIntValue {

    public static void main(String[] args) {
        String s = "94650723337775781477";
        String s1 = "19979817075396416247";
        SplitStringIntoConsecutiveIntValue test = new SplitStringIntoConsecutiveIntValue();
        System.out.println(test.splitString(s1));
    }
    public boolean splitString(String s) {
        for (int i = 1; i < s.length(); i++) {
            String prev = s.substring(0, i);
            if (backtrack(prev, s.substring(i)))
                return true;
        }
        return false;
    }
    private boolean backtrack(String bigger, String rest) {
        if (rest.length() == 0) return false;
        String trimedBigger = bigger.replaceFirst("^0*", "");
        String trimedRest =rest.replaceFirst("^0*", "") ;
        if((trimedBigger.length() - trimedRest.length() == 0 ||  trimedBigger.length() - trimedRest.length() == 1) && Long.parseLong(bigger) == Long.parseLong(rest)+1) return true;

        for (int i = 1; i <= rest.length(); i++) {
            String cur = rest.substring(0, i);
            String trimedCur = cur.replaceFirst("^0*", "");
            if ((trimedBigger.length() - trimedCur.length() == 0 ||  trimedBigger.length() - trimedCur.length() == 1) && Long.parseLong(bigger) == Long.parseLong(cur)+1 && backtrack(cur, rest.substring(i)))
                return true;
        }
        return false;
    }
}