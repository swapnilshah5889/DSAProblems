package CS114.Lab3;

import java.util.Stack;

/**
 * Calculate 5!
 */

public class Factorial {

    public static void main(String args[]) {

        // nonrecursive equivalent to factorial()
        Stack<NState> stack = new Stack<NState>();
        NState start = new NState(5, 1);
        long result = 0;
        stack.push(start);
        while (!stack.isEmpty()) {

            NState s = stack.pop();
            System.out.println(s.getN() +" = "+s.getA());
            if(s.getN() <= 1) result = s.getA();
            else {
                NState t = new NState(s.getN()-1, s.getA()*s.getN());
                stack.push(t);
            }
        }
        System.out.println(result);
    }

    // recursive version
    static long factorial(NState s) {
        if(s.getN() <= 1) return s.getA();
        return factorial(new NState(s.getN()-1, s.getA()*s.getN()));
    }
}
