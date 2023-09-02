package Heap;
import javax.sound.midi.Soundbank;
import java.util.*;

public class HeapProblems {

    public class Pair {
        Integer key;
        Integer val;

        Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static class DistancePair {
        Double distance;
        int index;
        DistancePair(double distance, int index) {
            this.distance = distance;
            this.index = index;
        }
    }

    public List<Integer> heapify(List<Integer> A) {
        List<Integer> ans = new ArrayList<>();

        int i=0;
        while(i < 2*i+1) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            int min = Math.min(A.get(i), Math.min(left, right));
            //Itself is the smallest
            if (A.get(i) == min) {
                i = 2 * i + 1;
            }
        }

        return ans;
    }

    public void swap(List<Integer> a, int parInd, int currInd) {
        int temp  = a.get(parInd);
        a.set(parInd, a.get(currInd));
        a.set(currInd, temp);
    }

    public static void ProductOf3(List<Integer> list) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int product=1;
        int k= Math.min(list.size(),3);
        for(int i=0; i<k; i++) {
            product*=list.get(i);
            queue.add(list.get(i));
            if(i<2)
                ans.add(-1);
            else
                ans.add(product);
        }
        for (int i=k; i<list.size(); i++) {
            int val = list.get(i);
            if(queue.peek() < val) {
                int min = queue.poll();
                product/=min;
                queue.add(val);
                product*=val;
            }
            ans.add(product);
        }

        System.out.println(ans);
    }
    public static List<Integer> LargestElementSubarrays(List<Integer> list, int a) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i=0; i<a; i++) {
            queue.add(list.get(i));
            if(i<a-1)
                ans.add(-1);
            else
                ans.add(queue.peek());
        }
        for (int i=a; i<list.size(); i++) {
            int val = list.get(i);
            if(queue.peek() < val) {
                queue.poll();
                queue.add(val);
            }
            ans.add(queue.peek());
        }

        return ans;
    }

    public static int ConnectRopes(List<Integer> A) {

        int ans = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i=0; i<A.size(); i++) {
            queue.add(A.get(i));
        }

        while(queue.size()>1) {
            int val1 = queue.poll();
            int val2 = queue.poll();
            ans += (val1+val2);
            queue.add(val1+val2);
        }

        return ans;
    }

    public static ArrayList<Double> RunningMedian(List<Integer> A) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ArrayList<Double> ans = new ArrayList<>();
        maxHeap.add(A.get(0));
        ans.add((double)maxHeap.peek());
        for(int i=1; i<A.size(); i++) {
            int val = A.get(i);
            if(val>maxHeap.peek()) {
                minHeap.add(val);
                maxHeap.add(minHeap.poll());
            }
            else {
                maxHeap.add(val);
            }
            if(maxHeap.size()-1 > minHeap.size())
                minHeap.add(maxHeap.poll());

            if(minHeap.size() == maxHeap.size()) {
                ans.add( ((double)minHeap.peek()+maxHeap.peek())/2 );
            }
            else {
                ans.add((double)maxHeap.peek());
            }

        }
        return ans;
    }

    public static long totalCostForWorkers(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> queue1 = new PriorityQueue<>();
        if(candidates*2 >= costs.length) {
            for(int i=0; i<costs.length; i++) {
                queue1.add(costs[i]);
            }
            long ans = 0;
            for(int i=0; i<k; i++) {
                ans += queue1.remove();
            }
            return ans;
        }
        else {
            PriorityQueue<Integer> queue2 = new PriorityQueue<>();
            int lastIndex = costs.length-1;
            for(int i=0; i<candidates; i++) {
                queue1.add(costs[i]);
                queue2.add(costs[lastIndex]);
                lastIndex--;
            }
            long ans = 0;
            int ind1 = candidates;
            int ind2 = costs.length-1-candidates;
            for(int i=0; i<k; i++) {
                if(queue2.isEmpty()) {
                    ans += queue1.remove();
                }
                else if(queue1.isEmpty()) {
                    ans += queue2.remove();
                }
                else if(queue2.peek() < queue1.peek()) {
                    ans += queue2.remove();
                    if(ind2>=ind1) {
                        queue2.add(costs[ind2]);
                        ind2--;
                    }
                }
                else {
                    ans += queue1.remove();
                    if(ind1<=ind2) {
                        queue1.add(costs[ind1]);
                        ind1++;
                    }
                }

            }
            return ans;
        }
    }

    public static double getDistance(int x, int y) {
        return Math.pow(Math.pow(x,2) + Math.pow(y,2), 0.5);
    }
    public static ArrayList<ArrayList<Integer>> closestPointsToOrigin(ArrayList<ArrayList<Integer>> A, int B) {
        Comparator<DistancePair> c = new Comparator<DistancePair>() {
            @Override
            public int compare(DistancePair o1, DistancePair o2) {
                return o1.distance.compareTo(o2.distance);
            }
        };
        PriorityQueue<DistancePair> q = new PriorityQueue<>(c);

        int i=0;
        for(ArrayList<Integer> point : A) {
            DistancePair d = new DistancePair(getDistance(point.get(0), point.get(1)), i);
            q.add(d);
            i++;
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int j=0; j<B; j++) {
            DistancePair d = q.remove();
            ans.add(A.get(d.index));
        }
        return ans;
    }

    public static int minimumCandies(ArrayList<Integer> A, int B) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int candy : A) {
            q.add(candy);
        }
        int total = 0;
        while(!q.isEmpty()) {
            int candies = q.remove();
            if(candies <= B) {
                int eaten = candies / 2;
                total += eaten;
                if (!q.isEmpty()) {
                    int newCandies = q.remove() + (candies - eaten);
                    q.add(newCandies);
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {

        /*List<Integer> list  = Arrays.asList(2,4,5,11,6,7,8,20,12,1,3);
        MinHeap minHeap = new MinHeap(list);
        minHeap.printHeap();*/

        //Return array with max product of 3 values
        //for all lengths of given array starting from 1st index
        /*ProductOf3(Arrays.asList(10,2,6,4));*/

        //Connect all ropes such that cost of connecting is minimized
        /*System.out.println(ConnectRopes(Arrays.asList(1, 2, 3, 4, 5)));*/

        //Find Ath largest number for all subarrays starting from first index
        /*System.out.println(LargestElementSubarrays(Arrays.asList(1, 2, 3, 4, 5, 6),4));*/

        //Running Median Problem
        /*System.out.println(RunningMedian(Arrays.asList(32, 91, 86, 8, 4, 100, 98, 15, 79, 32, 4, 99 )));*/

        // total cost to hire k workers
        /*int[] costs = new int[]{17,12,10,2,7,2,11,20,8};
        System.out.println(totalCostForWorkers(costs, 3, 4));*/

        // B closest points to origin
        /*ArrayList<ArrayList<Integer>> A = new ArrayList<>(
                List.of(
                    new ArrayList<>(List.of(26,41)),
                    new ArrayList<>(List.of(40,47)),
                    new ArrayList<>(List.of(47,7)),
                    new ArrayList<>(List.of(50,34)),
                    new ArrayList<>(List.of(18,28))
                )
        );

        System.out.println(closestPointsToOrigin(A, 5));*/

        // Minimum Candies
        System.out.println(minimumCandies(new ArrayList<>(List.of(324,458,481,167,939,444,388,612,943,890,953,403,653,136,168,163,186,471)), 231));

    }

}
