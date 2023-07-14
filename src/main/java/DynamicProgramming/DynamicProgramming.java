package DynamicProgramming;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;

public class DynamicProgramming {

    // Find Nth Fibonacci Number (Recursive - Top Down Approach)
    public static int findFibTopDown(int n, int[] arr) {
        if(n == 1 || n == 2) {
            return arr[n];
        }

        if(arr[n] != -1) {
            return arr[n];
        }

        int ans = findFibTopDown(n-1, arr) + findFibTopDown(n-2, arr);
        arr[n] = ans;
        return ans;
    }

    // Find Nth Fibonacci Number (Iterative - Bottom Up Approach)
    public static int findFibBottomUp(int n) {

        if(n == 1 || n == 2) {
            return 1;
        }

        int fibPrev = 1;
        int fibPrevPrev = 1;
        int index = 3;
        int fib = fibPrevPrev;
        while(index<=n) {
            fib = fibPrev + fibPrevPrev;
            fibPrevPrev = fibPrev;
            fibPrev = fib;
            index++;
        }

        return fib;

    }

    // Find Minimum Number of Steps (Recursive - Top Down Approach)
    public static int findMinimumStepsRecursive(int n, int[] arr, int initialSteps) {
        if(arr[n] != -1) {
            return arr[n];
        }
        int ans = Integer.MAX_VALUE;
        for(int i=1; i<=initialSteps; i++) {
            ans = Math.min(ans, findMinimumStepsRecursive(n-i,arr, initialSteps)+1);
        }

        arr[n] = ans;
        return ans;
    }

    // Find Minimum Number of Steps (Iterative - Bottom Up Approach)
    public static int findMinimumStepsIterative(int n, int initialSteps) {
        if(n<=initialSteps) {
            return 1;
        }
        int[] arr = new int[initialSteps];
        Arrays.fill(arr, 1);
        int index = initialSteps+1;
        while(index<=n) {
            int ans = Integer.MAX_VALUE;
            for(int i=1; i<=arr.length; i++) {
                ans = Math.min(ans, arr[i-1]+1);
            }

            for(int i=0; i<arr.length-1; i++) {
                arr[i] = arr[i+1];
            }
            arr[arr.length-1] = ans;

            index++;
        }

        return arr[arr.length-1];
    }

    // Get minimum number of perfect squares that sum to N
    public static int findMinimumSquaresToTotal(int n, int arr[]) {
        if(arr[n] != -1) {
            return arr[n];
        }
        int index = (int) Math.sqrt(n);
        int ans = Integer.MAX_VALUE;
        for(int i = index; i>=1; i--) {
            ans = Math.min(ans, findMinimumSquaresToTotal(n-(i*i), arr)+1);
        }
        arr[n] = ans;
        return ans;
    }
    public static int numberOfWaysToClimbStairs(int n, int[] arr) {
        if(arr[n] != -1) {
            return arr[n];
        }

        int ans = numberOfWaysToClimbStairs(n-1, arr) + numberOfWaysToClimbStairs(n-2,arr);
        arr[n] = ans;
        return ans;
    }

    // Max Subarray product
    public static int maxSubarrayProduct(int[] arr) {
        int dpMin[] = new int[arr.length];
        int dpMax[] = new int[arr.length];
        dpMin[0] = arr[0];
        dpMax[0] = arr[0];
        int max = dpMax[0];
        for(int i=1; i<arr.length; i++) {
            int curr = arr[i];
            int currMin = dpMin[i-1]*curr;
            int currMax = dpMax[i-1]*curr;
            dpMin[i] = Math.min(currMin,Math.min(currMax, curr));
            dpMax[i] = Math.max(currMin,Math.max(currMax, curr));
            max = Math.max(max, dpMax[i]);
        }
        /*Arrays.stream(dpMin).forEach(value -> System.out.print(value+" "));
        System.out.println();
        Arrays.stream(dpMax).forEach(value -> System.out.print(value+" "));
        System.out.println();*/
        return max;
    }

    public static int maxSumValue(ArrayList<Integer> A, int B, int C, int D) {
        int [][] matrix = new int[3][A.size()];
        matrix[0][0] = A.get(0)*B;
        for(int i=1; i<A.size(); i++) {
            matrix[0][i] = Math.max(A.get(i)*B, matrix[0][i-1]);
        }
        matrix[1][0] = matrix[0][0] + A.get(0)*C;
        for(int i=1; i<A.size(); i++) {
            matrix[1][i] = Math.max(A.get(i)*C + matrix[0][i], matrix[1][i-1]);
        }
        matrix[2][0] = matrix[1][0] + A.get(0)*D;
        for(int i=1; i<A.size(); i++) {
            matrix[2][i] = Math.max(A.get(i)*D + matrix[1][i], matrix[2][i-1]);
        }
        return matrix[2][A.size()-1];
    }

    public static int findWays(String s) {
        int val  = Integer.parseInt(s);
        if(s.length()==1){

            if(val == 0) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else {
            int num1 = Integer.parseInt(s.substring(0,1));

            // Cannot start with 0
            if(num1 == 0) {
                return 0;
            }

            if(val>=1 && val<=26) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    public static int decodeString(String A){

        int[] dp = new int[A.length()];
        dp[0] = findWays(A.substring(0,1));
        // If length 1
        if(A.length()==1) {
            return dp[0];
        }
        // First character 0
        if(dp[0]==0) {
            return 0;
        }


        int val = Integer.parseInt(A.substring(0,2));
        if(val>=1 && val<=26) {
            if(val == 10 || val == 20)
                dp[1] = 1;
            else
                dp[1] = 2;
        }
        else {
            // 79
            dp[1] = 1;
        }

        Arrays.stream(dp).forEach(a -> {
            System.out.print(a + " ");
        });
        System.out.println();

        // If length 2;
        if(A.length() == 2) {
            return dp[1];
        }
        // If invalid code
        if(dp[1] == 0) {
            return 0;
        }
        int mod = (int)Math.pow(10,9)+7;
        // DP
        for(int i=2; i<A.length(); i++) {
            int ways = (int)((((long)dp[i-1]*findWays(A.charAt(i)+""))%mod +
                    ((long)dp[i-2]*findWays(A.substring(i-1,i+1))%mod))%mod);
            if(ways==0) {
                return 0;
            }
            else {
                dp[i] = ways;
            }
        }


        return dp[dp.length-1];

    }

    //Code not working for all cases
    /*public static int maxSumWithoutAdjacentElements(ArrayList<ArrayList<Integer>> A) {
        int rows = A.size();
        int cols = A.get(0).size();

        int[][] dp = new int[rows][cols];
        dp[0][0] = A.get(0).get(0);
        if(cols>1) {
            dp[0][1] = Math.max(dp[0][0], A.get(0).get(1));
        }
        // Set First Row
        for(int i=2; i<cols; i++) {
            dp[0][i] = Math.max(dp[0][i-1], (dp[0][i-2]+A.get(0).get(i)));
        }
        if(rows>1) {
            dp[1][0] = Math.max(dp[0][0],A.get(1).get(0));
            // Set First Column
            for(int j=2; j<rows; j++) {
                dp[j][0] = Math.max(dp[j-1][0], (dp[j-2][0]+A.get(j).get(0)));
            }


            int[] x = new int[]{0, -1, -2, -2, -2};
            int[] y = new int[]{-2, -2, -2, -1, 0};
            int[] x1 = new int[]{0,-1,-1};
            int[] y1 = new int[]{-1,-1,0};
            for(int i=1; i<rows; i++) {
                for(int j=1; j<cols; j++) {
                    int prevMax = Integer.MIN_VALUE;
                    //Get DP max
                    for(int k=0; k<x.length; k++) {
                        int newX = i+x[k];
                        int newY = j+y[k];
                        //If valid point
                        if(newX>=0 && newY>=0) {
                            prevMax = Math.max(prevMax, dp[newX][newY]);
                        }
                    }
                    int currMax = A.get(i).get(j)+prevMax;
                    if(j>1) {
                        currMax = Math.max(currMax, dp[i - 1][j] + dp[i][j - 2]);
                    }
                    // Calculate Max for current position
                    for(int k=0; k<x1.length; k++){
                        int newX = i+x1[k];
                        int newY = j+y1[k];
                        if(newX>=0 && newY>=0) {
                            currMax = Math.max(currMax, dp[newX][newY]);
                        }
                    }
                    dp[i][j] = currMax;
                }
            }
        }


        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val->{
                System.out.print(val+" ");
            });
            System.out.println();
        });
        return dp[rows-1][cols-1];
    }*/

    // Max sum without adjacent elements
    public static int maxSumWithoutAdjacentElements(ArrayList<ArrayList<Integer>> A) {
        int dp[] = new int[A.get(0).size()];
        for(int i=0; i<dp.length; i++) {
            dp[i] = Math.max(A.get(0).get(i), A.get(1).get(i));
        }

        if(A.get(0).size()>1) {
            dp[1] = Math.max(dp[0],dp[1]);
        }
        if(A.get(0).size()>2) {
            for (int i = 2; i < dp.length; i++) {
                dp[i] = Math.max(dp[i]+dp[i-2], dp[i-1]);
            }
        }

        return dp[dp.length-1];
    }

    public static int dungeonPrincess( ArrayList<ArrayList<Integer>> A ) {

        int rows = A.size();
        int cols = A.get(0).size();
        int dp[][] = new int[rows][cols];

        if(A.get(rows-1).get(cols-1) > 0) {
            dp[rows-1][cols-1] = 0;
        }
        else {
            dp[rows-1][cols-1] = 1 - A.get(rows-1).get(cols-1);
        }

        // Set last row
        for(int i=cols-2; i>=0; i--) {
            int val = dp[rows-1][i+1] - A.get(rows-1).get(i);
            if(val == 0 || (dp[rows-1][i+1]==0  && A.get(rows-1).get(i)<=0)) {
                val+=1;
            }
            dp[rows-1][i] = Math.max(0, val);
        }

        // Set last column
        for(int i=rows-2; i>=0; i--) {
            int val = dp[i+1][cols-1] - A.get(i).get(cols-1);
            if(val == 0 || (dp[i+1][cols-1]==0  && A.get(i).get(cols-1)<=0)) {
                val+=1;
            }
            dp[i][cols-1] = Math.max(0, val);
        }

        // DP
        for(int i=rows-2; i>=0; i--) {
            for(int j=cols-2; j>=0; j--) {

                int minVal = Math.min(dp[i+1][j],dp[i][j+1]);
                if(minVal<=0) {
                    minVal+=1;
                }

                dp[i][j] = Math.max(1, minVal - A.get(i).get(j));

            }
        }

        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val + " ");
            });
            System.out.println();
        });
        return dp[0][0];
    }

    public static int uniquePathsInGrid(ArrayList<ArrayList<Integer>> A) {
        int[][] dp = new int[A.size()][A.get(0).size()];

        if(A.get(0).get(0) == 1) {
            return 0;
        }
        dp[0][0] = 1;
        for(int i=1; i<dp[0].length; i++) {
            // Empty Space
            if(A.get(0).get(i)==0) {
                dp[0][i] = Math.min(1, dp[0][i-1]);
            }
            // Blocked
            else {
                dp[0][i] = 0;
            }
        }

        for(int i=1; i<dp.length; i++) {
            // Empty Space
            if(A.get(i).get(0)==0) {
                dp[i][0] = Math.min(1, dp[i-1][0]);
            }
            // Blocked
            else {
                dp[i][0] = 0;
            }
        }

        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                if(A.get(i).get(j)==0) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
                else {
                    dp[i][j]=0;
                }
            }
        }

        Arrays.stream(dp).forEach(ints -> {
            Arrays.stream(ints).forEach(val -> {
                System.out.print(val+" ");
            });
            System.out.println();
        });
        return dp[dp.length-1][dp[0].length-1];
    }

    public static long findTotalNDigitNumbers(int A, int B, long[][]dp, int mod) {
        if(B == 0) {
            return 1;
        }
        if(A==0) {
            return 0;
        }
//        System.out.println("Call to ("+A+","+B+")");
        if(dp[A][B]==-1) {
            int nextDigit = Math.min(9, B);
            long sum = 0;
            for (int i = nextDigit; i >= 0; i--) {
                long tempSum = findTotalNDigitNumbers(A - 1, B - i, dp, mod);
//                System.out.println((A-1)+" - "+(B-i)+ " -> "+tempSum );
                dp[A - 1][B - i] = tempSum;
                sum = (tempSum+sum)%mod;
            }
            dp[A][B] = sum;
            return sum;
        }
        else {
            return dp[A][B];
        }
    }
    public static int NDigitNumbers(int A, int B) {
        // Edge case
        if(B == 0) {
            return 0;
        }

        long dp[][] = new long[A][B];
        for(int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i],-1);
        }
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 1;
        }

        int totalSum = 0;
        int mod = (int)Math.pow(10, 9) + 7 ;
        int firstDigit = Math.min(9, B);
        for(int i=firstDigit; i>0; i--) {
            long tempSum = findTotalNDigitNumbers(A-1, B-i, dp, mod);
            System.out.println("("+(A-1)+","+(B-i)+") -> " + tempSum);
            dp[A-1][B-i] = tempSum;
            totalSum = (int)((totalSum+tempSum)%mod);
        }
        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val+" ");
            });
            System.out.println();
        });
        return totalSum;

    }

    public static void main(String[] args) {

        // Find Nth Fibonacci number
        /*int n = 1;
        int arr[] = new int[n+1];
        Arrays.fill(arr,-1);
        arr[1] = 1;
        if(arr.length>2)
            arr[2] = 1;
        System.out.println("Fibonacci(Recursive) "+n+": "+findFibTopDown(n, arr));
        System.out.println("Fibonacci(Iterative) "+n+": "+findFibBottomUp(n));*/


        // Find Minimum Number of Steps to reach Nth Stair
        /*int n = 11;
        int arr[] = new int[n+1];
        Arrays.fill(arr,-1);
        //Initial step cost
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        System.out.println("Minimum Steps to Reach Stair " +n+ ": " + findMinimumStepsRecursive(n, arr, 3));
        System.out.println("Minimum Steps to Reach Stair " +n+ ": " + findMinimumStepsIterative(n, 3));*/

        // Find total number of ways to climb n steps
        /*int n = 10;
        int arr[] = new int[n+1];
        Arrays.fill(arr, -1);
        arr[1] = 1;
        arr[2] = 2;
        System.out.println("Total ways to climb "+n+" steps: "+numberOfWaysToClimbStairs(n, arr));*/

        // Find minimum perfect squares that sum to N
        /*int n=6;
        int arr[] = new int[n+1];
        Arrays.fill(arr,-1);
        arr[0] = 0;
        System.out.println("Minimum perfect squares required for "+n+": "+findMinimumSquaresToTotal(n,arr));*/

        // Max Subarray product
        /*int[] arr = new int[]{-2,-2,-2,-2, 0};
        System.out.println("Max subarray product: "+maxSubarrayProduct(arr));*/

        // Ways to decode string
        /*String[] s = new String[]{"12121","10","101", "0", "793"};
        Arrays.stream(s).forEach(val -> {
            System.out.println(val+": "+decodeString(val));
        });*/

        // Max sum without adjustment elements
        /*ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList(Arrays.asList(16,5,54,55,36,82,61,77,66,61)));
        A.add(new ArrayList(Arrays.asList(31,30,36,70,9,37,1,11,68,14)));
        System.out.println(maxSumWithoutAdjacentElements(A));*/

        // Unique Paths in a Grid
        /*ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList(Arrays.asList(0,0,0)));
        A.add(new ArrayList(Arrays.asList(1,1,1)));
        A.add(new ArrayList(Arrays.asList(0,0,0)));
        System.out.println(uniquePathsInGrid(A));*/

        // Dungeon Princess
        /*ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList(Arrays.asList(-39,-65,-93,-51,-97,-46,-32,-89,-70,-56,-14,-95,2,-3,-32,-7,8,-10,-16,-92)));
        A.add(new ArrayList(Arrays.asList(-95,-55,-99,-51,-7,-82,-93,-6,-8,-54,-76,-20,-80,-2,9,-100,-81,-78,-58,-27)));
        A.add(new ArrayList(Arrays.asList(-76,-44,-40,-47,-50,-82,-21,-98,-28,0,-10,2,-90,-6,-12,-91,-28,-98,1,-49)));
        A.add(new ArrayList(Arrays.asList(-18,-54,-95,-51,8,-18,-33,-18,-44,2,3,-11,-81,-35,7,-19,-82,-42,-21,-45)));
        A.add(new ArrayList(Arrays.asList(-57,-63,-42,-70,-66,-65,-52,-81,-17,-23,-91,1,-68,-52,-42,1,-65,-43,-69,-18)));
        A.add(new ArrayList(Arrays.asList(-57,-49,-35,-56,-20,-36,-42,-47,-70,-26,-53,-41,-9,-98,2,-25,8,-6,-99,-47)));
        A.add(new ArrayList(Arrays.asList(-76,-64,-8,-18,-3,9,-23,-6,-93,-43,-82,-82,-47,-30,-48,-2,-54,-6,-19,-47)));
        A.add(new ArrayList(Arrays.asList(-4,-96,-28,10,-95,-25,-29,-37,4,-87,-58,-68,-7,-92,-34,-48,-21,-17,-55,-91)));
        A.add(new ArrayList(Arrays.asList(-28,-8,5,-96,-17,-56,-54,-79,-17,8,-92,-20,-65,-96,-88,-87,6,-68,-46,-1)));
        A.add(new ArrayList(Arrays.asList(-25,-79,-27,-77,-88,7,-70,3,-10,-58,10,6,5,-55,-94,-41,-26,-19,-39,-12)));
        A.add(new ArrayList(Arrays.asList(-46,-92,9,-90,-31,-86,-1,4,-40,-41,-95,1,-60,-69,-42,-67,-45,-65,-47,-91)));
        A.add(new ArrayList(Arrays.asList(-32,-99,4,-65,-10,-83,-67,-96,-69,-63,4,-43,-48,-98,-16,-73,-21,1,-81,-56)));
        A.add(new ArrayList(Arrays.asList(0,-1,-86,-71,-75,-1,-95,-22,-12,-38,-39,10,-98,-53,-84,-60,-42,-85,-21,-98)));
        A.add(new ArrayList(Arrays.asList(-33,-6,-31,-66,-70,-27,-25,-99,-26,8,-86,-68,-92,-63,-62,-95,-8,-65,-13,-31)));
        A.add(new ArrayList(Arrays.asList(-7,-84,-17,-66,-84,-13,2,-34,-22,-96,-81,-89,-61,-34,10,-23,-96,3,-2,-82)));
        A.add(new ArrayList(Arrays.asList(-30,-48,1,-40,-84,-7,-8,-90,-32,-5,7,-53,-64,-25,-73,-82,-85,-40,1,-35)));
        A.add(new ArrayList(Arrays.asList(-80,-83,-91,-90,-73,2,-18,-25,-76,-72,-6,-28,-49,-86,3,-80,-63,4,-85,3)));
        A.add(new ArrayList(Arrays.asList(6,-8,-52,1,-57,-72,-73,-28,-88,-74,-25,-46,-93,-76,-10,-44,-92,-38,-70,-74)));
        A.add(new ArrayList(Arrays.asList(-43,-24,-98,-36,-77,-81,-2,-90,1,-42,3,-82,-2,-32,-80,-32,-12,-60,-79,-32)));
        A.add(new ArrayList(Arrays.asList(-91,-82,-65,8,-12,-64,-42,-82,-66,-16,-97,-96,-79,-29,-79,-6,-6,-19,3,4)));
        System.out.println(dungeonPrincess(A));*/

        // A Digit N numbers that whose digits adds up to sum B
        System.out.println(NDigitNumbers(3,15));
    }
}
