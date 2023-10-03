package CS114.Lab3;

import CS114.Lab3.CState;
import org.checkerframework.checker.units.qual.A;

import java.util.*;

/**
 * Print out all nonempty subsets of a string using recursion.
 */

public class SSS {

    static void printAllSubsets(CState s) {
        if (s.getSuffix().length() > 0) {
            System.out.println(s.getPrefix() + s.getSuffix().charAt(0));
            CState n1 = new CState(s.getPrefix() + s.getSuffix().charAt(0), s.getSuffix()
                    .substring(1));
            CState n2 = new CState(s.getPrefix(), s.getSuffix().substring(1));
            printAllSubsets(n1);
            printAllSubsets(n2);
        }
    }

    public static void subsets(String s, String processed, int index) {
        if(index == s.length()) {
            System.out.println(processed);
            return;
        }

        subsets(s, processed+s.charAt(index), index+1);
        subsets(s, processed, index+1);
    }

    public static void printAllSubsets(String s) {
        subsets(s, "", 0);
    }

    public static void subsetsArray(String s) {

        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder(s);
        int len = sb.length();
        for(int i=0; i<len; i++) {
            int n=list.size();
            char chr = sb.charAt(i);
            list.add(chr+"");

            for(int j=0; j<n; j++) {
                list.add(list.get(j)+chr);
            }
        }

        System.out.println(list);
    }

    public static void subsetsQueue(String s) {
        Queue<CState> queue = new LinkedList<>();
        queue.add(new CState("", s));
        ArrayList<String> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            CState curr = queue.poll();
            if(curr.getSuffix().length()>0) {
                ans.add(curr.getPrefix()+curr.getSuffix().charAt(0));
                CState n1 = new CState(curr.getPrefix(), curr.getSuffix().substring(1));
                CState n2 = new CState(curr.getPrefix()+curr.getSuffix().charAt(0), curr.getSuffix().substring(1));
                queue.add(n1);
                queue.add(n2);
            }
        }

        System.out.println(ans);
    }

    public static void main(String args[]) {

        /*CState start = new CState("","abcd");
        printAllSubsets(start);

//        printAllSubsets("abc");

        subsetsDeque("abcd");*/

        subsetsArray("abcd");

        subsetsQueue("abcd");
    }

}
