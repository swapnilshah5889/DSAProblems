package DynamicProgramming;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.*;

public class DynamicProgramming {

    static class Pair {
        int key;
        int val;
        Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

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

    public static int trianglePathSum(ArrayList<ArrayList<Integer>> A) {
        int dp[][] = new int[A.size()][A.size()];
        for(int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }

        dp[0][0] = A.get(0).get(0);
        for(int i=1; i<dp.length; i++) {
            dp[i][0] = dp[i-1][0] + A.get(i).get(0);
        }


        for(int i=1; i<dp.length; i++) {

            for(int j=1; j<=i; j++) {
                if(j==i) {
                    dp[i][j] = A.get(i).get(j) + dp[i-1][j-1];
                }
                else {
                    dp[i][j] = A.get(i).get(j) + Math.min(dp[i-1][j-1], dp[i-1][j]);
                }
            }

        }

        int min = dp[dp.length-1][0];

        for(int i=1; i<dp.length; i++) {
            min = Math.min(min, dp[dp.length-1][i]);
        }

        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val +" ");
            });
            System.out.println();
        });

        return min;
    }
    private static int minimumPathSum(ArrayList<ArrayList<Integer>> A) {
        int dp[][] = new int[A.size()][A.get(0).size()];

        dp[0][0] = A.get(0).get(0);
        // Set first row
        for(int i=1; i<dp.length; i++) {
            dp[i][0] = dp[i-1][0] + A.get(i).get(0);
        }

        // Set first column
        for(int i=1; i<dp[0].length; i++) {
            dp[0][i] = dp[0][i-1] + A.get(0).get(i);
        }

        // DP
        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + A.get(i).get(j);
            }
        }

        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val +" ");
            });
            System.out.println();
        });

        return dp[dp.length-1][dp[0].length-1];

    }

    private static int maximumRectangles(ArrayList<ArrayList<Integer>> A) {
        int maxArea = 0;

        for(int i=0; i<A.size(); i++) {
            for(int j=1; j<A.get(0).size(); j++) {
                if(A.get(i).get(j) != 0) {
                    A.get(i).set(j, A.get(i).get(j)+A.get(i).get(j-1));
                }
            }
        }

        for(int i=0; i<A.size(); i++) {
            for(int j=0; j<A.get(0).size(); j++) {

                int width=A.get(i).get(j);
                int height=1;
                // If current element not 0
                if(A.get(i).get(j)!=0) {
                    int rowIndex = i;
                    while(rowIndex>=0) {
                        // Take minimum of row's width
                        width = Math.min(width, A.get(rowIndex).get(j));
                        // Break if width of previous row is not more than 0
                        if(width==0) {
                            break;
                        }
                        int area = height * width;
                        maxArea = Math.max(maxArea, area);
                        height++;
                        rowIndex--;
                    }
                }

            }
        }

        A.stream().forEach(list -> {
            list.stream().forEach(val -> {
                System.out.print(val+ " ");
            });
            System.out.println();
        });

        return maxArea;
    }

    private static int knapsackZeroOne(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int dp[][] = new int[A.size()+1][C+1];
        // First column
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 0;
        }
        // First column
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 0;
        }

        for(int i=1; i<dp.length; i++) {
            int currVal = A.get(i-1);
            int currWeight = B.get(i-1);
            for(int j=1; j<dp[0].length; j++) {
                if(currWeight <= j) {
                    // We add current value with previous row's j-currWeight index
                    // Because as we add current value so we are picking one item
                    // So we need to choose decrease one item which is why
                    // we choose previous row for j-currWeight column
                    dp[i][j] = Math.max(currVal+dp[i-1][j-currWeight], dp[i-1][j]);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        /*Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val+" ");
            });
            System.out.println();
        });*/
        return dp[dp.length-1][dp[0].length-1];
    }

    public static int knapsackUnbounded(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int dp[][] = new int[A.size()+1][C+1];
        // First column
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 0;
        }
        // First column
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 0;
        }

        for(int i=1; i<dp.length; i++) {
            int currVal = A.get(i-1);
            int currWeight = B.get(i-1);
            for(int j=1; j<dp[0].length; j++) {
                if(currWeight <= j) {
                    // We use current row as we are now allowed to
                    // repeat the same item so we do not need to decrement
                    // the total number of items remaining
                    dp[i][j] = Math.max(currVal+dp[i][j-currWeight], dp[i-1][j]);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }

    public static Pair flipArrayCompare(Pair p1, Pair p2) {

        // Same weight - compare items
        if(p1.key==p2.key) {
            return p1.val<p2.val? p1 : p2;
        }
        // Different weights - return maximum
        else {
            return p1.key>p2.key? p1 : p2;
        }
    }
    public static int flipArray(ArrayList<Integer> A) {

        int sum = A.stream().reduce(0, (acc, val) -> {
           return acc+val;
        });
        sum/=2;

        Pair[][] dp = new Pair[A.size()+1][sum+1];
        // First Column
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = new Pair(0,0);
        }
        // First Row
        for(int j=0; j<dp[0].length; j++) {
            dp[0][j] = new Pair(0,0);
        }

        // DP
        // Iterate over array
        for(int i=1; i<dp.length; i++) {
            int currVal = A.get(i-1);
            // Iterate over sum/2
            for(int j=1; j<dp[0].length; j++) {
                if(currVal<=j) {
                    Pair tempPair = new Pair(dp[i-1][j-currVal].key, dp[i-1][j-currVal].val);
                    tempPair.key+=currVal;
                    tempPair.val+=1;
                    dp[i][j] = flipArrayCompare(tempPair, dp[i-1][j]);
//                    dp[i][j] = Math.max(currVal+dp[i-1][j-currVal], dp[i-1][j]);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print("("+val.key+","+val.val+") ");
            });
            System.out.println();
        });
        return dp[dp.length-1][dp[0].length-1].val;
    }

    public static Pair compareFood(Pair p1, Pair p2) {

        // Weights different
        if(p2.key != p1.key) {
            return p1.key > p2.key ? p1 : p2;
        }
        // Same weights - return minimum cost
        else {
            return p1.val < p2.val ? p1 : p2;
        }
    }
    public static int partyMinimumCost(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int maxCapacity = A.stream().reduce(0, (acc, val) -> {
            return Math.max(acc,val);
        });

        System.out.println("Max: "+maxCapacity);
        Pair dp[][] = new Pair[B.size()+1][maxCapacity+1];

        // First column
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = new Pair(0,0);
        }

        // First row
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = new Pair(0,0);
        }

        for(int i=1;i<dp.length;i++) {
            int currWeight = B.get(i-1);
            int currCost = C.get(i-1);
            for(int j=1;j<dp[0].length;j++) {

                // If food weight is less than or equal to available capacity
                if(currWeight<=j) {
                    Pair temp = new Pair(currWeight, currCost);
                    temp.key += dp[i][j-currWeight].key;
                    temp.val += dp[i][j-currWeight].val;
                    dp[i][j] = compareFood(temp, dp[i-1][j]);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }

            }
        }

        int partyCost  = A.stream().reduce(0 , (acc, capacity) -> {
            return acc + dp[dp.length-1][capacity].val;
        });

        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(pair -> {
                System.out.print("("+pair.key+", "+pair.val+") ");
            });
            System.out.println();
        });

        return partyCost;
    }

    public static int buyingCandies(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C, int D) {

        // Set sweetness
        for(int i=0; i<B.size(); i++) {
            B.set(i, A.get(i)*B.get(i));
        }
        // First Column
        int dp[][] = new int[B.size()+1][D+1];
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 0;
        }
        // First Row
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 0;
        }

        // DP
        for(int i=1; i<dp.length; i++) {
            int currCost = C.get(i-1);
            int currSweetness = B.get(i-1);
            for(int j=1; j<dp[0].length; j++) {
                if(currCost<=j) {
                    dp[i][j] = Math.max(dp[i-1][j], currSweetness + dp[i][j-currCost]);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val+" ");
            });
            System.out.println();
        });
        return dp[dp.length-1][dp[0].length-1];
    }

    public static int distinctSubsequences(String s, String t) {

        int dp[][] = new int[t.length()][s.length()+1];

        for(int i=0; i<t.length(); i++) {
            dp[i][0] = 0;
        }

        char firstChar = t.charAt(0);
        for(int i=1; i<=s.length(); i++) {
            if(firstChar == s.charAt(i-1)){
                dp[0][i] = dp[0][i-1]+1;
            }
            else {
                dp[0][i] = dp[0][i-1];
            }
        }

        for(int i=1; i<t.length(); i++) {
            int currChar = t.charAt(i);
            for(int j=1; j<=s.length(); j++) {
                if(currChar == s.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                }
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val+" ");
            });
            System.out.println();
        });
        return dp[t.length()-1][s.length()];
    }

    public static int minCostClimbingStairs(int[] cost) {
        int dp[] = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for(int i=2; i<cost.length; i++) {
            dp[i] = cost[i] + Math.min(dp[i-1],dp[i-2]);
        }
        Arrays.stream(dp).forEach(val -> {
            System.out.print(val+" ");
        });
        System.out.println();
        return Math.min(dp[dp.length-1], dp[dp.length-2]);
    }

    public static int knapsackII(ArrayList<Integer> A, ArrayList<Integer> B, int C) {

        int dp[][] = new int[2][C+1];
        // First column
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 0;
        }
        // First column
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 0;
        }

        for(int i=0; i<A.size(); i++) {
            int currVal = A.get(i);
            int currWeight = B.get(i);
            for(int j=1; j<dp[0].length; j++) {
                if(currWeight <= j) {
                    // We add current value with previous row's j-currWeight index
                    // Because as we add current value so we are picking one item
                    // So we need to choose decrease one item which is why
                    // we choose previous row for j-currWeight column
                    dp[1][j] = Math.max(currVal+dp[0][j-currWeight], dp[0][j]);
                }
                else {
                    dp[1][j] = dp[0][j];
                }
            }
            for(int k=0; k<dp[0].length; k++) {
                dp[0][k] = dp[1][k];
            }
        }
        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val+" ");
            });
            System.out.println();
        });
        return dp[dp.length-1][dp[0].length-1];

    }

    public static int coinChange(ArrayList<Integer> A, int B) {
        int dp[][] = new int[2][B+1];
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 1;
        }
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 0;
        }

        for(int i=0; i<A.size(); i++) {
            int currVal = A.get(i);
            for(int j=1; j<dp[0].length; j++) {
                if(currVal <= j) {
                    if(dp[1][j-currVal]>0) {
                        if(currVal==j) {
                            dp[1][j] = 1;
                        }
                        else {
                            if(dp[0][j]>0) {
                                dp[1][j] = Math.min(dp[1][j-currVal] + 1, dp[0][j]);
                            }
                            else{
                                dp[1][j] = dp[1][j-currVal] + 1;
                            }
                        }
                    }
                    else {

                    }
                }
                else {
                    dp[1][j] = dp[0][j];
                }

            }
            for(int k=0; k<dp[0].length; k++) {
                dp[0][k] = dp[1][k];
            }
        }

        if(dp[dp.length-1][dp[0].length-1] == 0)
            return -1;
        return dp[dp.length-1][dp[0].length-1];
    }

    // Find total combinations of coins to make change for a given sum
    public static int coinChangeWays(ArrayList<Integer> A, int B) {
        int dp[][] = new int[2][B+1];
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 1;
        }
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 0;
        }

        for(int i=0; i<A.size(); i++) {
            int currVal = A.get(i);
            for(int j=1; j<dp[0].length; j++) {
                if(currVal <= j) {
                    dp[1][j] = dp[1][j-currVal] + dp[0][j];
                }
                else {
                    dp[1][j] = dp[0][j];
                }

            }
            for(int k=0; k<dp[0].length; k++) {
                dp[0][k] = dp[1][k];
            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }

    public static int longestFibonacciSequence(ArrayList<Integer> A) {

        Map<Integer, Integer> mp = new HashMap<>();
        for(int i=0; i<A.size(); i++) {
            mp.put(A.get(i), i);
        }

        int dp[][] = new int[A.size()+1][A.size()+1];
        for(int i=0;i<dp.length; i++) {
            for(int j=i; j<dp.length;j++) {
                dp[i][j] = 2;
            }
        }

        int max = 0;
        // DP
        for(int i=0; i<A.size(); i++) {
            for(int j=i+1; j<A.size(); j++) {
                if(mp.containsKey(A.get(i)+A.get(j))) {
                    int k = mp.get(A.get(i)+A.get(j));
                    dp[j][k] = 1 + dp[i][j];
                    max = Math.max(max, dp[j][k]);
                }
            }
        }

        return max;
    }

    public static int minEditDistRecur(String A, int indexA, String B, int indexB, int[][] dp,
                                       int ic, int dc, int rc) {
        if(indexA == -1 && indexB == -1) {
            return dp[0][0];
        }
        // Input string reached end - Insert remaining characters of target string
        if(indexA == -1) {
            if(dp[indexA+1][indexB+1] == -1){
                dp[indexA+1][indexB+1] = ic * (indexB+1);
            }
        }
        // Target string reached end - Delete remaining characters of input string
        if(indexB == -1) {
            if(dp[indexA+1][indexB+1] == -1){
                dp[indexA+1][indexB+1] = dc * (indexA+1);
            }
        }

        if(dp[indexA+1][indexB+1] == -1) {

            // If character at the end is equal - go to next character
            if(A.charAt(indexA) == B.charAt(indexB)) {
                return minEditDistRecur(A, indexA-1, B, indexB-1, dp, ic, dc,  rc);
            }

            // Characters not same - return minimum of replace, insert and delete
            int min =  Math.min (Math.min (
                    rc + minEditDistRecur(A, indexA-1, B, indexB-1, dp, ic, dc,  rc),
                    ic + minEditDistRecur(A, indexA, B, indexB-1, dp, ic, dc,  rc)),
                    dc + minEditDistRecur(A, indexA-1, B, indexB, dp, ic, dc,  rc));

            dp[indexA+1][indexB+1] = min;

        }

        return dp[indexA+1][indexB+1];
    }
    public static int minimumEditDistance(String inputString, String targetString) {
        int insertCost = 2;
        int deleteCost = 2;
        int replaceCost = 3;
        int dp[][] = new int[inputString.length()+1][targetString.length()+1];
        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][0] = 0;
        int editDist = minEditDistRecur(inputString, inputString.length()-1,
                targetString, targetString.length()-1, dp,
                insertCost, deleteCost, replaceCost);

        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[0].length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return editDist;
    }

    public static boolean patternMatchRecur(String inputString, String pattern,
                                                int indexI, int indexP, Boolean[][] dp) {

        // If both end reached
        if(indexI == -1 && indexP == -1) {
            return dp[0][0];
        }

        // If any one reached the end
        if(indexI == -1 || indexP == -1) {
            if(dp[indexI+1][indexP+1] == null) {
                // Input String reached end
                if(indexI == -1) {
                    if(pattern.charAt(indexP) == '*') {
                        dp[indexI + 1][indexP + 1] = patternMatchRecur(inputString,
                                pattern, indexI, indexP - 1, dp);
                    }
                    else {
                        dp[indexI + 1][indexP + 1] = false;
                    }
                }
                // Pattern reached end
                else {
                    dp[indexI+1][indexP+1] = false;
                }
            }
        }


        if(dp[indexI+1][indexP+1] == null) {
            // Characters Match
            if(inputString.charAt(indexI) == pattern.charAt(indexP)) {
                dp[indexI+1][indexP+1] = patternMatchRecur(inputString,
                                        pattern, indexI-1, indexP-1, dp);
            }
            // Characters do not match
            else {
                if(pattern.charAt(indexP) == '?') {
                    // Matches exactly 1 character matched
                    dp[indexI+1][indexP+1] = patternMatchRecur(inputString,
                                                pattern, indexI-1, indexP-1, dp);
                }
                else if(pattern.charAt(indexP) == '*') {
                    // Check for 0 to n characters matched
                    dp[indexI+1][indexP+1] = patternMatchRecur(inputString,
                            pattern, indexI, indexP-1, dp)
                            ||
                            patternMatchRecur(inputString,
                                    pattern, indexI-1, indexP, dp);
                }
                else {
                    dp[indexI+1][indexP+1] = false;
                }
            }
        }

        return dp[indexI+1][indexP+1];
    }
    public static boolean wildcardPatternMatchRecurssive(String inputString, String pattern) {
        Boolean dp[][] = new Boolean[inputString.length()+1][pattern.length()+1];

        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[0].length; j++) {
                dp[i][j] = null;
            }
        }
        dp[0][0] = true;
        boolean ans = patternMatchRecur(inputString, pattern, inputString.length()-1,
                    pattern.length()-1, dp);
        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return ans;
    }

    private static boolean wildcardPatternMatchIterative(String inputString, String pattern) {
        Boolean prev[] = new Boolean[inputString.length()+1];
        Boolean curr[] = new Boolean[inputString.length()+1];
        prev[0] = true;

        // First Column
        for(int j=1; j<prev.length; j++) {
            prev[j] = false;
        }

        for(int i=0; i<pattern.length(); i++) {
            char patternChar = pattern.charAt(i);

            if(pattern.charAt(i) == '*')
                curr[0] = prev[0];
            else
                curr[0] = false;

            for(int j=1; j<prev.length; j++) {
                char inputChar = inputString.charAt(j-1);

                // Characters match or ? character
                if(inputChar == patternChar || patternChar == '?') {
                    curr[j] = prev[j-1];
                }
                // Star Character
                else if(patternChar == '*') {
                    curr[j] = prev[j] || curr[j-1];
                }
                // Characters do not match
                else {
                    curr[j] = false;
                }
            }
            prev = curr;
            Arrays.stream(prev).forEach(val -> {
                System.out.print(val + " ");
            });
            System.out.println();
            curr = new Boolean[inputString.length()+1];
        }


        return prev[prev.length-1];
    }

    private static boolean interleaveStringsHelper(String A, String B, String C,
                                                   int aIndex, int bIndex, int cIndex,
                                                   Boolean[][] dp) {
        // Inputs traversed
        if(aIndex == -1 && bIndex == -1) {
            // return if target traversed or not
            return cIndex == -1;
        }
        // Target traversed
        if(cIndex == -1) {
            return false;
        }

        if(dp[aIndex+1][bIndex+1] == null) {
            boolean isValid = false;
            if (aIndex != -1 && A.charAt(aIndex) == C.charAt(cIndex)) {
                isValid = interleaveStringsHelper(A, B, C, aIndex - 1, bIndex, cIndex - 1, dp);
            }

            if (bIndex != -1 && B.charAt(bIndex) == C.charAt(cIndex)) {
                isValid = isValid || interleaveStringsHelper(A, B, C, aIndex, bIndex - 1, cIndex - 1, dp);
            }
            dp[aIndex+1][bIndex+1] = isValid;
        }


        return dp[aIndex+1][bIndex+1];
    }

    private static boolean interleaveStrings(String A, String B, String C) {
        Boolean dp[][] = new Boolean[A.length()+1][B.length()+1];
        dp[0][0] = true;
        for(int i=0; i<A.length(); i++) {
            dp[i+1][0] = A.charAt(i) == C.charAt(i);
        }

        for(int i=0; i<B.length(); i++) {
            dp[0][i+1] = B.charAt(i) == C.charAt(i);
        }

        Boolean ans = interleaveStringsHelper(A, B, C, A.length()-1,
                                B.length()-1, C.length()-1, dp);

        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val+" ");
            });
            System.out.println();
        });
        return ans;
    }

    private static boolean regularExpressionRecur(String input, String pattern,
                                                  int iIndex, int pIndex, Boolean[][] dp) {
        // If both traversed
        if(pIndex == -1 && iIndex == -1) {
            return dp[iIndex+1][pIndex+1];
        }
        // Input traversed
        if(iIndex == -1) {
            // If pattern left with * characters
            if(pattern.charAt(pIndex) == '*') {
                dp[iIndex+1][pIndex+1] = regularExpressionRecur(input, pattern, iIndex, pIndex-2, dp);
            }
            // If pattern left with other characters
            else {
                if(dp[iIndex+1][pIndex+1] == null)
                    dp[iIndex+1][pIndex+1] = false;
            }
        }
        // Pattern traversed
        if(pIndex == -1) {
            if(dp[iIndex+1][pIndex+1] == null)
                dp[iIndex+1][pIndex+1] = false;
        }

        if(dp[iIndex+1][pIndex+1] == null) {
            if(input.charAt(iIndex) == pattern.charAt(pIndex) || pattern.charAt(pIndex) == '.') {
                dp[iIndex+1][pIndex+1] = regularExpressionRecur(input, pattern, iIndex-1, pIndex-1, dp);
            }
            else if(pattern.charAt(pIndex) == '*') {
                // If character match or previous character '.'
                // Consider both, consuming input character and discarding '*'
                if(pattern.charAt(pIndex-1) == input.charAt(iIndex) || pattern.charAt(pIndex-1) == '.') {
                    dp[iIndex + 1][pIndex + 1] = regularExpressionRecur(input, pattern, iIndex, pIndex - 2, dp) ||
                            regularExpressionRecur(input, pattern, iIndex - 1, pIndex, dp);
                }
                // Discard the '*'
                else {
                    dp[iIndex + 1][pIndex + 1] = regularExpressionRecur(input, pattern, iIndex, pIndex - 2, dp);
                }
            }
            else {
                dp[iIndex+1][pIndex+1] = false;
            }
        }

        return dp[iIndex+1][pIndex+1];

    }
    private static boolean regularExpression(String input, String pattern) {

        // Recursive Solution
        /*Boolean dp[][] = new Boolean[input.length()+1][pattern.length()+1];
        Arrays.stream(dp).map(arr->{
            return Arrays.stream(arr).map(val -> {return null;});
        });
        dp[0][0] = true;
        boolean ans = regularExpressionRecur(input, pattern, input.length()-1, pattern.length()-1, dp);
        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val+" ");
            });
            System.out.println();
        });
        return ans;*/

        // Iterative Approach
        int[] prev = new int[pattern.length()+1];
        int[] curr = new int[pattern.length()+1];
        prev[0] = 1;
        for(int i=1; i<prev.length; i++) {
            if(pattern.charAt(i-1) == '*') {
                prev[i] = prev[i-1];
            }
            else{
                prev[i] = 0;
            }
        }

        for(int i=0; i<input.length(); i++) {
            char iChar = input.charAt(i);
            curr[0] = 0;
            for(int j=1; j<prev.length; j++) {
                char pChar = pattern.charAt(j-1);

                // Char match or pattern has '?'
                if(iChar == pChar || pChar == '?') {
                    curr[j] = prev[j-1];
                }
                else if(pChar == '*') {
                    curr[j] = Math.max(prev[j],curr[j-1]);
                }
                else {
                    curr[j] = 0;
                }

            }
            prev = curr;
            curr = new int[pattern.length()+1];
        }

        return prev[prev.length-1] == 1;
    }

    private static int longestPalindromicSubsequence(String A) {
        int prev[] = new int[A.length()+1];
        int curr[] = new int[A.length()+1];

        for(int i=0; i<prev.length; i++) {
            prev[i] = 0;
        }

        for(int i=1; i<=A.length(); i++) {
            char currChar = A.charAt(A.length()-i);
            curr[0] = 0;
            for(int j=1; j<prev.length; j++) {
                char c = A.charAt(j-1);
                if(currChar == c) {
                    curr[j] = 1 + prev[j-1];
                }
                else {
                    curr[j] = Math.max(prev[j], curr[j-1]);
                }

            }
            prev = curr;
            curr = new int[A.length()+1];
        }

        return prev[prev.length-1];
    }

    public static int russianDollEnvelopes(ArrayList<ArrayList<Integer>> A) {
        Collections.sort(A, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if(o1.get(0) != o2.get(0)) {
                    return o1.get(0).compareTo(o2.get(0));
                }
                else {
                    return o1.get(1).compareTo(o2.get(1));
                }
            }
        });

        int dp[] = new int[A.size()];
        for(int i=0; i<dp.length; i++) {
            dp[i] = 1;
        }

        int max = 1;
        for(int i=1; i<dp.length; i++) {
            ArrayList<Integer> ev2 = A.get(i);
            for(int j=0; j<i; j++) {
                ArrayList<Integer> ev1 = A.get(j);
                if(ev1.get(0) < ev2.get(0) && ev1.get(1) < ev2.get(1)) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    max = Math.max(dp[i], max);
                }
            }
        }

        A.stream().forEach(list -> {
            System.out.print("("+list.get(0) + ", "+list.get(1)+")   ");
        });
        System.out.println();

        return max;
    }

    public static int matrixChainMultiplication(ArrayList<Integer> A) {
        int dp[][] = new int[A.size()-1][A.size()-1];


        for(int i=0; i<A.size()-2; i++) {
            dp[i][i+1] = A.get(i)*A.get(i+1)*A.get(i+2);
        }

        for(int index=1; index<dp.length; index++) {
            int i = 0;
            for(int j=index+1; j<dp.length; j++) {

                int max = Integer.MAX_VALUE;
                for(int k=i+1; k<=j; k++) {
                    max = Math.min(dp[i][k-1] + dp[k][j] + (A.get(i) * A.get(k) * A.get(j+1)), max);
                }
                dp[i][j] = max;
                i++;
            }
        }

        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val + " ");
            });
            System.out.println();
        });
        return dp[0][dp.length-1];
    }

    private static List<List<String>> findAllPalindromes(int dp[][], int index, String s,
                                                         List<List<String>>[] stringDp) {

        if(index == s.length()-1) {
            if(stringDp[index] == null) {
                List<List<String>> ans = new ArrayList<>();
                ans.add(Arrays.asList(s.substring(index)));

                stringDp[index] = ans;
            }
            return stringDp[index];
        }

        if(stringDp[index] == null) {
            List<List<String>> ans = new ArrayList<>();

            for (int i = index; i < dp.length; i++) {

                // Palindrome
                if (dp[index][i] == 1) {
                    if (i == s.length() - 1) {
                        ans.add(new ArrayList<>(Arrays.asList(s.substring(index))));
                    } else {
                        List<List<String>> nextStrings = findAllPalindromes(dp, i + 1, s, stringDp);
                        for (List<String> list : nextStrings) {
                            List<String> temp = new ArrayList<>();
                            temp.add(s.substring(index, i + 1));
                            temp.addAll(list);
                            ans.add(temp);
                        }
                    }
                }
            }

            stringDp[index] = ans;
        }
        return stringDp[index];
    }
    private static List<List<String>> getAllPalindromes(String s) {
        int dp[][] = new int[s.length()][s.length()];
        List<List<String>>[] stringDp = new ArrayList[s.length()];
        for(int i=0;i<stringDp.length; i++) {
            stringDp[i] = null;
        }

        for(int i=0; i<dp.length; i++) {
            dp[i][i] = 1;
        }

        for(int i=dp.length-2; i>=0; i--) {
            char currChar = s.charAt(i);
            for(int j=i+1; j<dp.length; j++) {
                char c = s.charAt(j);
                // Characters match
                if(currChar == c) {
                    // 2 Character String
                    if (j - i == 1) {
                        dp[i][j] = 1;
                    }
                    // > 2 character string
                    else {
                        dp[i][j] = dp[i + 1][j - 1] == 1 ? 1 : 0;
                    }
                }
                // characters do not match
                else {
                    dp[i][j] = 0;
                }
            }
        }

        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val + " ");
            });
            System.out.println();
        });

        return findAllPalindromes(dp, 0, s, stringDp);
    }

    public static int palindromePartition(String A) {
        int dp[][] = new int[A.length()][A.length()];

        for(int i=0; i<dp.length; i++) {
            dp[i][i] = 1;
        }

        for(int i=dp.length-2; i>=0; i--) {
            char currChar = A.charAt(i);
            for(int j=i+1; j<dp.length; j++) {
                char c = A.charAt(j);
                // Characters match
                if(currChar == c) {
                    // 2 Character String
                    if (j - i == 1) {
                        dp[i][j] = 1;
                    }
                    // > 2 character string
                    else {
                        dp[i][j] = dp[i + 1][j - 1] == 1 ? 1 : 0;
                    }
                }
                // characters do not match
                else {
                    dp[i][j] = 0;
                }
            }
            System.out.println();
        }

        int[] pal = new int[A.length()];
        int[] cuts = new int[A.length()];
        cuts[0] = 0;
        for(int i=1; i<pal.length; i++) {
            // String from start to i is palindrome
            // So no cuts needed
            if(dp[0][i] == 1) {
                pal[i] = 0;
//                if(i == pal.length-1) {
//                    cuts.add(i);
//                }
                cuts[i] = 0;
            }
            // Take minimum of all substrings
            // That are palindrome
            else {
                int min = Integer.MAX_VALUE;
                for(int j=1; j<=i; j++) {
                    if(dp[j][i] == 1) {
                        int temp = 1 + pal[j - 1];
                        if(temp < min) {
                            min = temp;
                            cuts[i] = j;
                        }
                    }
                }
                pal[i] = min;
            }
        }

        Arrays.stream(dp).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val+" ");
            });
            System.out.println();
        });

        System.out.println("Pal : ");
        Arrays.stream(pal).forEach(val -> {
            System.out.print(val + " ");
        });
        System.out.println();

        System.out.println("Cuts : ");
        Arrays.stream(cuts).forEach(val -> {
            System.out.print(val + " ");
        });
        System.out.println();
        List<String> palindromicStrings = new ArrayList<>();
        int end = cuts.length;
        int start = cuts[end-1];
        while(start>=0) {
            String s = A.substring(start, end);
            palindromicStrings.add(s);
            end = start;
            if(end==0)
                break;
            start = cuts[end-1];
        }
        Collections.reverse(palindromicStrings);
        palindromicStrings.forEach(string -> {
            System.out.print(string + " ");
        });
        System.out.println();
        return pal[pal.length-1];
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
        /*System.out.println(NDigitNumbers(3,15));*/

        // Triangle path sum
        /*ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList(Arrays.asList(2)));
        A.add(new ArrayList(Arrays.asList(3,4)));
        A.add(new ArrayList(Arrays.asList(6,5,7)));
        A.add(new ArrayList(Arrays.asList(4,1,8,3)));
        System.out.println(trianglePathSum(A));*/

        // Minimum path sum
        /*ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList(Arrays.asList(1, -3, 2)));
        A.add(new ArrayList(Arrays.asList(2, 5, 10)));
        A.add(new ArrayList(Arrays.asList(5, -5, 1)));
        System.out.println("Minimum path sum: "+minimumPathSum(A));*/

        // Maximum Rectangle
        /*ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList(Arrays.asList(1,1,1,1,1,1,0,1,1,1,1,1,1,1,1)));
        A.add(new ArrayList(Arrays.asList(1,0,1,1,0,1,1,1,1,1,1,1,1,1,1)));
        A.add(new ArrayList(Arrays.asList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)));
        A.add(new ArrayList(Arrays.asList(0,1,1,1,1,1,1,0,1,1,1,0,1,1,1)));
        A.add(new ArrayList(Arrays.asList(1,0,0,1,1,1,1,1,1,1,1,0,1,1,1)));
        A.add(new ArrayList(Arrays.asList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)));
        A.add(new ArrayList(Arrays.asList(1,1,1,0,1,1,1,1,1,1,1,0,1,1,1)));
        A.add(new ArrayList(Arrays.asList(1,1,1,1,0,0,0,1,1,1,1,1,0,1,0)));
        A.add(new ArrayList(Arrays.asList(1,0,1,1,0,0,0,1,1,1,1,0,1,0,1)));
        A.add(new ArrayList(Arrays.asList(1,0,1,1,1,1,1,1,0,1,1,1,0,1,1)));
        A.add(new ArrayList(Arrays.asList(1,0,1,1,1,1,1,1,1,1,1,1,1,1,1)));
        A.add(new ArrayList(Arrays.asList(1,1,1,0,1,1,1,1,1,1,1,1,1,1,1)));
        A.add(new ArrayList(Arrays.asList(1,1,1,0,0,0,1,0,1,1,1,1,1,1,1)));
        A.add(new ArrayList(Arrays.asList(1,1,1,1,1,1,0,1,1,1,1,1,1,1,1)));
        A.add(new ArrayList(Arrays.asList(1,1,1,1,1,1,1,0,1,1,1,1,1,0,1)));
        System.out.println(maximumRectangles(A));*/

        // 0 1 Knapsack
        /*ArrayList<Integer> values = new ArrayList(Arrays.asList(60, 100, 120));
        ArrayList<Integer> weights = new ArrayList(Arrays.asList(10, 20, 30));
        System.out.println(knapsackZeroOne(values, weights, 50));*/

        // Unbounded Knapsack
        /*System.out.println(knapsackUnbounded(values, weights, 50));*/

        // Flip Array - minimum flips in array to make sum closest to 0
        /*ArrayList<Integer> A = new ArrayList<>(Arrays.asList(3,2,4,5));
        System.out.println(flipArray(A));*/

        // Minimum cost of party
        /*ArrayList<Integer> A = new ArrayList<>(Arrays.asList(2, 4, 6));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(2, 1, 3));
        ArrayList<Integer> C = new ArrayList<>(Arrays.asList(2, 5, 3));
        System.out.println(partyMinimumCost(A,B,C));*/

        // Buying candies
        /*ArrayList<Integer> candiesInPacket = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> sweetnessOfCandies = new ArrayList<>(Arrays.asList(2, 2, 10));
        ArrayList<Integer> costOfCandies = new ArrayList<>(Arrays.asList(2, 3, 9));
        int budget = 8;
        System.out.println(buyingCandies(candiesInPacket,sweetnessOfCandies,costOfCandies, budget));*/

        // Total Distinct Subsequences
        /*System.out.println(distinctSubsequences("rabbbit","rabbit"));*/

        // Min cost to climb stairs
        /*System.out.println(minCostClimbingStairs(new int[]{10,15,20}));*/

        // Knapsack II
        /*ArrayList<Integer> values = new ArrayList(Arrays.asList(6, 10, 12));
        ArrayList<Integer> weights = new ArrayList(Arrays.asList(10, 20, 30));
        System.out.println(knapsackII(values, weights, 50));*/

        // Ways for coin change
        /*ArrayList<Integer> coins = new ArrayList<>(Arrays.asList(2,5));
        System.out.println(coinChange(coins, 3));*/

        // Longest Fibonacci Sequence
        /*ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        System.out.println(longestFibonacciSequence(A));*/

        // Minimum edit distance
        /*System.out.println(minimumEditDistance("acdxy", "abcgx"));*/

        // Wild Character Match / String pattern Match
        // System.out.println(wildcardPatternMatchRecurssive("bcabbbbcb", "?b*?b*b"));
//        System.out.println(wildcardPatternMatchIterative("bcabbbbcb","?b*?b*b"));
        /*System.out.println(wildcardPatternMatchIterative("bbbcbcb","**b"));*/

        // Interleave Strings
        /*System.out.println(interleaveStrings("aabcc", "dbbca","aadbbcbcac"));*/

        // Regular Expression II
        /*System.out.println(regularExpression("aab", "c*a*b"));
        System.out.println(regularExpression("baaaaaabaaaabaaaaababababbaab", "..a*aa*a.aba*a*bab*"));
        System.out.println(regularExpression("ccc", "a*"));*/

        // Longest Palindromic Subsequence
        /*System.out.println(longestPalindromicSubsequence("bebeeed"));
        System.out.println(longestPalindromicSubsequence("aedsead"));*/

        // Russian Doll Envelopes
        /*ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        *//*A.add(new ArrayList<>(Arrays.asList(5,4)));
        A.add(new ArrayList<>(Arrays.asList(6,4)));
        A.add(new ArrayList<>(Arrays.asList(6,7)));
        A.add(new ArrayList<>(Arrays.asList(2,3)));*//*

        *//*A.add(new ArrayList<>(Arrays.asList(6,18)));
        A.add(new ArrayList<>(Arrays.asList(2,14)));
        A.add(new ArrayList<>(Arrays.asList(5,6)));
        A.add(new ArrayList<>(Arrays.asList(4,15)));
        A.add(new ArrayList<>(Arrays.asList(8,11)));
        A.add(new ArrayList<>(Arrays.asList(3,11)));
        A.add(new ArrayList<>(Arrays.asList(11,10)));
        A.add(new ArrayList<>(Arrays.asList(5,11)));
        System.out.println(russianDollEnvelopes(A));*/

        // Matrix Chain Multiplication

        /*ArrayList<Integer> A = new ArrayList<>(Arrays.asList(15,50,3,50,50,47)); // 18915
//        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(5,4,6,2,7));
        System.out.println(matrixChainMultiplication(A));*/

        // Palindrom Partition
//        System.out.println(palindromePartition("beebeeed"));
//        System.out.println(palindromePartition("ababb"));
        System.out.println(getAllPalindromes("bbbb"));

    }


}
