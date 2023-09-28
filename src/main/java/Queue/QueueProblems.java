package Queue;

import java.lang.reflect.Array;
import java.util.*;

public class QueueProblems {

    //Deque Problems

    //Sliding Window Maximum
    public static ArrayList<Integer> slidingWindowMaximum(List<Integer> A, int B){

        Deque<Integer> dq = new ArrayDeque<>();
        int i=0;
        while(i<B){
            if(!dq.isEmpty() && A.get(dq.getLast())<=A.get(i)){
                while(!dq.isEmpty()&& A.get(dq.getLast())<=A.get(i) ){
                    dq.pollLast();
                }
            }
            dq.addLast(i);
            i++;
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(A.get(dq.getFirst()));
        int j=1;
        for(i=B;i<A.size();j++,i++){

            if(dq.getFirst()==(j-1)){
                dq.pollFirst();
            }
            if(!dq.isEmpty() && A.get(dq.getLast())<=A.get(i)){
                while(!dq.isEmpty() && A.get(dq.getLast())<=A.get(i) ){
                    dq.pollLast();
                }
            }
            dq.addLast(i);

            ans.add(A.get(dq.getFirst()));
        }

        return ans;
    }

    //First Non Repeating Character
    public static String firstNonRepeatingCharacter(String s){
        StringBuilder st = new StringBuilder();
        return st.toString();
    }

    public static String findPerfectNumber(int A) {
        if (A == 1) {
            return "11";
        }
        if (A == 2) {
            return "22";
        }
        Queue<String> queue = new LinkedList<>();
        queue.add("11");
        queue.add("22");

        while(A>2) {
            Queue<String> tempQueue = new LinkedList<>();
            //Append ones
            for(String s : queue) {
                String newNum = "1"+s+"1";
                tempQueue.add(newNum);
                A--;
                if(A==2){
                    return newNum;
                }
            }

            //Append twos
            for(String s : queue) {
                String newNum = "2"+s+"2";
                tempQueue.add(newNum);
                A--;
                if(A==2){
                    return newNum;
                }
            }

            queue = tempQueue;
        }

        return "";
    }

    public static ArrayList<Integer> getFirstNIntegers(int A) {
        if(A == 1) {
            return new ArrayList<>(Arrays.asList(1));
        }
        if(A == 2) {
            return new ArrayList<>(Arrays.asList(1,2));
        }
        if(A == 3) {
            return new ArrayList<>(Arrays.asList(1,2,3));
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        queue.add(2);
        queue.add(3);
        ArrayList<Integer> ans = new ArrayList<>(queue);
        int pow = 10;
        while(A>3) {
            Queue<Integer> temp = new LinkedList<>();
            // Append 1
            for(Integer i : queue) {
                int num = i+pow;
                temp.add(num);
                A--;
                if(A==3) {
                    break;
                }
            }

            if(A>3) {
                int newPow = 2*pow;
                // Append 1
                for(Integer i : queue) {
                    int num = i+newPow;
                    temp.add(num);
                    A--;
                    if(A==3) {
                        break;
                    }
                }
            }


            if(A>3) {
                int newPow = 3*pow;
                // Append 1
                for(Integer i : queue) {
                    int num = i+newPow;
                    temp.add(num);
                    A--;
                    if(A==3) {
                        break;
                    }
                }
            }

            queue = temp;
            ans.addAll(queue);
            pow *= 10;
        }

        return ans;
    }

    public static ArrayList<Integer> reverseElements(ArrayList<Integer> A, int B) {

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<B; i++) {
            queue.add(A.get(i));
        }

        B--;
        while (!queue.isEmpty()) {
            A.set(B, queue.remove());
            B--;
        }

        return A;
    }

    public static String firstNonRepeatingElements(String A) {
        StringBuffer sb = new StringBuffer();
        HashMap<Character, Integer> map = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();

        for(int i=0; i<A.length(); i++) {
            char curr = A.charAt(i);
            map.put(curr, map.getOrDefault(curr, 0)+1);
            queue.add(curr);

            while(!queue.isEmpty()) {
                if(map.get(queue.peek())==1) {
                    break;
                }
                else {
                    queue.remove();
                }
            }

            if(queue.isEmpty()){
                sb.append('#');
            }
            else {
                sb.append(queue.peek());
            }
        }

        return sb.toString();
    }

    private static int minMaxSumSubarrays(ArrayList<Integer> A, int B) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int mod = (int) Math.pow(10, 9) + 7;
        for(int i=0; i<B; i++) {
            minHeap.add(A.get(i));
            maxHeap.add(A.get(i));
        }

        int ans = (int)(((long)minHeap.peek() + maxHeap.peek())%mod);
        if(ans<0) {
            ans+=mod;
        }

        for(int i=B; i<A.size(); i++) {
            minHeap.remove(A.get(i-B));
            maxHeap.remove(A.get(i-B));
            minHeap.add(A.get(i));
            maxHeap.add(A.get(i));
            ans = (int)(((long)ans + minHeap.peek() + maxHeap.peek())%mod);
            if(ans<0) {
                ans+=mod;
            }
        }

        return ans;
    }



    public static void main(String[] args) {

        //Sliding Window Maximum Using Deque
        /*System.out.println(slidingWindowMaximum(Arrays.asList(1,3,-1,-3,5,3,6,7),3));*/

        // Find perfect numbers
        /*System.out.println(findPerfectNumber(7));*/

        // Reverse Queue elements
        /*System.out.println(reverseElements(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)), 3));*/

        // Get first N Integers
        /*System.out.println(getFirstNIntegers(20));*/

        // First non repeating characters
        /*System.out.println(firstNonRepeatingElements("iergxwhddh"));
        System.out.println(firstNonRepeatingElements("abadbc"));
        System.out.println(firstNonRepeatingElements("abcabc"));*/

        // Sum of min and max in all subarrays
//        System.out.println(minMaxSumSubarrays(new ArrayList<>(Arrays.asList(2, 5, -1, 7, -3, -1, -2)), 4));
        System.out.println(minMaxSumSubarrays(new ArrayList<>(Arrays.asList(-1, -2, -4)), 2));

    }
}
