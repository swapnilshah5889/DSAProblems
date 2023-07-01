package LeetCode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class RecentCounter {
    Queue<Integer> queue;
    int millis;
    public RecentCounter() {
        queue = new LinkedList<>();
        millis = 0;
    }

    public int ping(int t) {
        millis = t-3000;
        queue.add(t);
        System.out.println(queue);
        System.out.println(millis);
        while(!queue.isEmpty() && queue.peek()<millis) {
            System.out.println("Peek:"+queue.peek());
            queue.remove();
        }
        return queue.size();
    }
}
public class LeetCodePractice {

    public static int findFirstOccurence(String haystack, String needle) {
        int end = 0;
        int len = needle.length();
        for(int i=0; i<haystack.length(); i++) {
            if(haystack.charAt(i) == needle.charAt(end) ) {
                end++;
                if(end == len) {
                    System.out.println("In : "+i);
                    return (i - end + 1);
                }
            }
            else {
                int iend = i+end;
                i-=end;
                while(i<iend && haystack.charAt(i) != needle.charAt(0)) {
                    i++;
                }
                end = 0;
            }
        }

        if(end == len)
            return len-end;
        else
            return -1;
    }

    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == ']') {
                Character c1 = stack.pop();
                StringBuilder st = new StringBuilder();
                while(c1 != '['){
                    st.insert(0, c1);
                    c1 = stack.pop();
                }
                StringBuilder numberSt = new StringBuilder();
                c1 = stack.peek();
                while (((int)c1 >= 48 && (int)c1 <= 57)) {
                    c1 = stack.pop();
                    numberSt.insert(0,c1);
                    if(!stack.isEmpty())
                        c1 = stack.peek();
                    else
                        break;
                }
                if(numberSt.length()>0) {
                    int count = Integer.parseInt(numberSt.toString());
                    for(int k=0; k<count; k++) {
                        for(int j=0; j<st.length(); j++) {
                            stack.push(st.charAt(j));
                        }
                    }
                }
            }
            else {
                stack.push(c);
            }
        }

        StringBuilder ans = new StringBuilder();
        while(!stack.isEmpty()) {
            ans.insert(0,stack.pop());
        }
        return ans.toString();
    }

    public static void main(String[] args) {

        // Find first occurrence of a substring
        /*System.out.println(findFirstOccurence("mississippi", "issip"));*/

        // Decode String
        /*System.out.println(decodeString("3[a]2[bc]"));*/

        // Queue
        RecentCounter rc = new RecentCounter();
        System.out.println(rc.ping(2196));
        System.out.println(rc.ping(3938));
        System.out.println(rc.ping(4723));
        System.out.println(rc.ping(4775));
        System.out.println(rc.ping(5952));

    }

}
