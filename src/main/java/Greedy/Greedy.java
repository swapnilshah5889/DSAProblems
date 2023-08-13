package Greedy;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Pair {
    Integer val1, val2;
    Pair(int val1, int val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
}
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

    public int maximumJobs(ArrayList<Integer> A, ArrayList<Integer> B) {

        PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.val2.equals(o2.val2)) {
                    return o1.val1.compareTo(o2.val1);
                }

                return o1.val2.compareTo(o2.val2);
            }
        });

        for(int i=0; i<A.size(); i++) {
            queue.add(new Pair(A.get(i), B.get(i)));
        }

        int total = 0;
        int prevEnd = -1;
        while(!queue.isEmpty()) {
            Pair task = queue.remove();

            if(task.val1 >= prevEnd) {
                System.out.println("Task Scheduled: "+task.val1 + " - "+task.val2);
                total++;
                prevEnd = task.val2;
            }
        }

        return total;
    }
}
public class Greedy {


    public static void main(String[] args) {
        Problems greedyProblems = new Problems();

        // Distribute Candies
        /*ArrayList<Integer> studentMarks = new ArrayList<>(Arrays.asList(6,7,5,4,3,2,1));
        System.out.println(greedyProblems.distributeCandies(studentMarks));*/

        // Schedule maximum jobs
        ArrayList<Integer> startTimes = new ArrayList<>(Arrays.asList(1, 5, 7, 1));
        ArrayList<Integer> endTimes = new ArrayList<>(Arrays.asList(7, 8, 8, 8));
        System.out.println(greedyProblems.maximumJobs(startTimes, endTimes));
    }
}
