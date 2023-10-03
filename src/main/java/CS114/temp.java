package CS114;

import java.util.ArrayList;
import java.util.Stack;

public class temp {

    public static void numberRecurr(String p, String s, int index) {
        if(s.isEmpty()) {
            System.out.println(p);
            return;
        }
        String first = s.charAt(0)+"";
        String second = s.substring(1);
        numberRecurr(p+first, second, index+1);
        if(!second.isEmpty())
            numberRecurr(p+first+"+", second, index+1);
    }

    static class CState {
        private String processed;
        private String unprocssed;

        CState(String p, String u) {
            this.processed = p;
            this.unprocssed = u;
        }

        public String getProcessed() {
            return processed;
        }

        public String getUnprocssed() {
            return unprocssed;
        }
    }
    public static String checkNumberStringRecursive(String s, long target) {

        numberRecurr("", s, 0);
        return "";
    }

    public static String checkNumberStringIterative(String s, long target) {

        Stack<CState> stack = new Stack<>();
        stack.add(new CState("", s));
        ArrayList<String> list = new ArrayList<>();
        while(!stack.isEmpty()) {
            CState prev = stack.pop();

            // If unprocessed in not empty
            if(!prev.getUnprocssed().isEmpty()) {

                // The next digit
                char currChr = prev.getUnprocssed().charAt(0);
                // New unprocessed
                String newUnprocessed = prev.getUnprocssed().substring(1);
                // Next state where we simply added the current number to previous digits
                CState next1 = new CState(prev.getProcessed()+currChr, newUnprocessed);
                stack.add(next1);
                // If new unprocessed is not empty
                if(!newUnprocessed.isEmpty()) {
                    // Insert plus in the processed string
                    CState next2 = new CState(prev.getProcessed()+currChr+"+", newUnprocessed);
                    stack.add(next2);
                }
            }
            // Completely processed the string
            else {
                list.add(prev.getProcessed());
            }
        }

        System.out.println(list);
        return "";
    }

    public static void studentSubmission(String s, long target) {
        Stack<String> stack = new Stack<>();
        stack.push("");
        while(!stack.isEmpty()) {
            String n = stack.pop();
            if(n.isEmpty() || n.startsWith("0") || n.equals("+")) continue;
            if(n.length() > 0) n += "+";

            for(int i=1; i<s.length(); i++) {
                String numStr = s.substring(0, i);
                String rest = s.substring(i);
                stack.push(n+numStr+rest);
            }
            System.out.println(stack);
        }

    }

    public static void main(String[] args) {
//        checkNumberStringRecursive("1234", 10);
//        checkNumberStringIterative("1234", 10);
        studentSubmission("1234", 10);

    }

}
