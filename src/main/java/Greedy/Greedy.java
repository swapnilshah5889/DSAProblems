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

    public int freeCars(ArrayList<Integer> A, ArrayList<Integer> B) {

        PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.val1.equals(o2.val1)) {
                    return o2.val2.compareTo(o1.val2);
                }

                return o1.val1.compareTo(o2.val1);
            }
        });

        PriorityQueue<Pair> queue2 = new PriorityQueue<Pair>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.val1.equals(o2.val1)) {
                    return o2.val2.compareTo(o1.val2);
                }

                return o1.val1.compareTo(o2.val1);
            }
        });

        for(int i=0; i<A.size(); i++) {
            queue.add(new Pair(A.get(i), B.get(i)));
            queue2.add(new Pair(A.get(i), B.get(i)));
        }


        ArrayList<Pair> cars = new ArrayList<>();
        cars.add(queue.remove());
        int total = cars.get(0).val2;
        int mod = (int) Math.pow(10, 9) + 7;
        while(!queue.isEmpty()) {
            Pair car = queue.remove();
            if(cars.size() < car.val1) {
                cars.add(car);
                total = (int)(((long)total + car.val2)%mod);
            }
            else {
                int replaceIndex = -1;
                for(int i=0; i<cars.size(); i++) {
                    if(cars.get(i).val2 < car.val2) {
                        // Found lower value than new car
                        if(replaceIndex == -1) {
                            replaceIndex = i;
                        }
                        // Found lower value than previous old car
                        else if(cars.get(replaceIndex).val2 > cars.get(i).val2) {
                            replaceIndex = i;
                        }
                    }
                }
                // Replace car
                if(replaceIndex != -1) {
                    total -= cars.get(replaceIndex).val2;
                    cars.set(replaceIndex, car);
                    total = (int)(((long)total + car.val2)%mod);
                }
            }
        }

        cars.stream().forEach(car -> {
            System.out.println(car.val1 + " - "+ car.val2);
        });

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
        /*ArrayList<Integer> startTimes = new ArrayList<>(Arrays.asList(1, 5, 7, 1));
        ArrayList<Integer> endTimes = new ArrayList<>(Arrays.asList(7, 8, 8, 8));
        System.out.println(greedyProblems.maximumJobs(startTimes, endTimes));*/

        // Find maximum profit by buying cars
        ArrayList<Integer> deadline = new ArrayList<>(Arrays.asList(1,7,6,2,8,4,4,6,8,2));
        ArrayList<Integer> profit = new ArrayList<>(Arrays.asList(8,11,7,7,10,8,7,5,4,9));
        System.out.println(greedyProblems.freeCars(deadline, profit));
    }
}
