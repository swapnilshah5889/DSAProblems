package Greedy;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

class Problems {
    public int distributeCandies(ArrayList<Integer> A) {

        int total = A.size();
        int candies[] = new int[A.size()];
        Arrays.fill(candies, 1);
        while(true) {
            boolean leftSwipe = false;
            // Left swipe
            for(int i=1; i<A.size(); i++) {
                // If marks greater and candies less
                if(A.get(i) > A.get(i-1) && candies[i] <= candies[i-1]) {
                    candies[i]++;
                    total++;
                    leftSwipe = true;
                }
            }
            boolean rightSwipe = false;
            // Right swipe
            for(int i=A.size()-2; i>=0; i--) {
                // If marks greater and candies less
                if(A.get(i) > A.get(i+1) && candies[i] <= candies[i+1]) {
                    candies[i]++;
                    total++;
                    rightSwipe = true;
                }
            }
            if(!leftSwipe && !rightSwipe) {
                break;
            }
        }

        Arrays.stream(candies).forEach(value -> {
            System.out.print(value + " ");
        });
        System.out.println();
        return total;
    }
}
public class Greedy {


    public static void main(String[] args) {
        Problems greedyProblems = new Problems();

        //Distribute Candies
        ArrayList<Integer> studentMarks = new ArrayList<>(Arrays.asList(6,7,5,4,3,2,1));
        System.out.println(greedyProblems.distributeCandies(studentMarks));
    }
}
