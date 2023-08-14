package Greedy;
import java.lang.reflect.Array;
import java.util.*;

class Pair {
    Integer val1, val2;
    Pair(int val1, int val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
}

class Problems {
    public int distributeCandies(ArrayList<Integer> A) {

        int candiesLeft[] = new int[A.size()];
        Arrays.fill(candiesLeft, 1);

        // Left swipe
        for(int i=1; i<A.size(); i++) {
            // If marks greater and candies less
            if(A.get(i) > A.get(i-1) && candiesLeft[i] <= candiesLeft[i-1]) {
                candiesLeft[i] = candiesLeft[i-1] + 1;
            }
        }

        int candiesRight[] = new int[A.size()];
        Arrays.fill(candiesRight, 1);

        // Right swipe
        for(int i=A.size()-2; i>=0; i--) {
            // If marks greater and candies less
            if(A.get(i) > A.get(i+1) && candiesRight[i] <= candiesRight[i+1]) {
                candiesRight[i] = candiesRight[i+1] + 1;
            }

        }

        Arrays.stream(candiesLeft).forEach(value -> {
            System.out.print(value + " ");
        });
        System.out.println();
        Arrays.stream(candiesRight).forEach(value -> {
            System.out.print(value + " ");
        });
        System.out.println();

        int total = 0;
        for(int i=0; i<candiesRight.length; i++) {
            total += Math.max(candiesLeft[i], candiesRight[i]);
        }

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

    public int minimumHops(String A) {
        A = A.toLowerCase();
        ArrayList<Integer> xIndexes = new ArrayList<>();
        for(int i=0; i<A.length(); i++) {
            if(A.charAt(i) == 'x') {
                xIndexes.add(i);
            }
        }

        int total = 0;
        if(xIndexes.size()>0) {
            int mod = (int) Math.pow(10,7) + 3;
            int mid = xIndexes.size()/2;
            int left = mid-1;
            // Left Swipe
            for(int i = xIndexes.get(mid)-1; i>=0; i--) {
                if(left == -1){
                    break;
                }

                total = (int) (((long)total + i - xIndexes.get(left))%mod);
                left--;
            }

            int right = mid+1;
            // Right Swipe
            for(int i = xIndexes.get(mid)+1; i<A.length(); i++) {
                if(right == xIndexes.size()){
                    break;
                }

                total = (int) (((long)total + xIndexes.get(right) - i)%mod);
                right++;
            }
        }
        return total;
    }

    public int findPowerofK(int A, int k) {
        return (int) (Math.log(A) / Math.log(k));
    }
    public int coinChangePowersofK(int A, int k) {
        int dp[] = new int[findPowerofK(A, k)+1];
        Arrays.fill(dp, -1);
        int totalCoins = 0;
        while(A>0) {
            int pow = findPowerofK(A, k);
            if(dp[pow] == -1) {
                dp[pow] = (int) Math.pow(k, pow);
            }
            A -= dp[pow];
            totalCoins++;
        }

        return totalCoins;
    }

    public ArrayList<Integer> shipProfits(int A, int B, ArrayList<Integer> C) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i=0; i<C.size(); i++) {
            maxHeap.add(C.get(i));
            minHeap.add(C.get(i));
        }
        int maxProfit = 0;
        int minProfit = 0;
        while(A>0) {

            // Maximize profit
            int max = maxHeap.remove();
            maxProfit += max;
            max--;
            if(max>0)
                maxHeap.add(max);

            // Minimize profit
            int min = minHeap.remove();
            minProfit += min;
            min--;
            if(min>0)
                minHeap.add(min);
            A--;

        }

        return new ArrayList<>(Arrays.asList(maxProfit, minProfit));
    }

    public int convertBinaryString(String A, int B) {
        int[] prefixSum = new int[A.length()];
        Arrays.fill(prefixSum, 0);

        int xor = 0;
        int flips = 0;

        for(int i=0; i<=A.length()-B; i++) {
            xor = xor ^ prefixSum[i];
            System.out.println(A.charAt(i) + " "+ xor);
            if( (A.charAt(i) == '0' && xor == 0) || (A.charAt(i) == '1' && xor==1)) {
                if(i+B < A.length())
                    prefixSum[i+B] = 1;
                xor = 1-xor;
                flips++;
            }
        }

        System.out.println();
        System.out.println(flips);
        Arrays.stream(prefixSum).forEach(val -> {
            System.out.print(val + " ");
        });
        System.out.println();

        for(int i=A.length()-B+1; i<A.length(); i++) {
            xor = xor ^ prefixSum[i];
            if( (xor ^ Integer.parseInt(A.charAt(i)+"")) == 0) {
                return -1;
            }
        }

        return flips;

    }

    public int findMiceHoles(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);
        int max = 0;
        for(int i=0; i<A.size(); i++) {
            int temp = Math.abs(A.get(i) - B.get(i));
            System.out.println(temp);
            max = Math.max(max, temp);
        }

        return max;
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
        /*ArrayList<Integer> deadline = new ArrayList<>(Arrays.asList(1,7,6,2,8,4,4,6,8,2));
        ArrayList<Integer> profit = new ArrayList<>(Arrays.asList(8,11,7,7,10,8,7,5,4,9));
        System.out.println(greedyProblems.freeCars(deadline, profit));*/

        // Find minimum hops
        /*System.out.println(greedyProblems.minimumHops("..x.xx..xx....x.."));*/

        // Find coin change if coin denominations are in powers of a single coin k
        /*int coin = 5;
        System.out.println( greedyProblems.coinChangePowersofK(47, coin) );*/

        // Maximum and minimum profit for a ship company
        /*ArrayList<Integer> shipVacancies = new ArrayList<>(Arrays.asList(2,2,2));
        System.out.println(greedyProblems.shipProfits(4, 3, shipVacancies));*/

        // Flip binary string k consecutive times to make it all 1s
        /*System.out.println(greedyProblems.convertBinaryString("00010110", 3));*/

        // Find mouse and holes
        ArrayList<Integer> mice = new ArrayList<>(Arrays.asList(-2));
        ArrayList<Integer> holes = new ArrayList<>(Arrays.asList(-6));
        System.out.println(greedyProblems.findMiceHoles(mice, holes));
    }
}
