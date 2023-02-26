package Queue;

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

    public static void main(String[] args) {

        //Sliding Window Maximum Using Deque
        System.out.println(slidingWindowMaximum(Arrays.asList(1,3,-1,-3,5,3,6,7),3));

        //First Non Repeating Character

    }
}
