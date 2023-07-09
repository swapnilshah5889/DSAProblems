package DynamicProgramming;

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

    // Find minimum number of squares
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
        int n=6;
        int arr[] = new int[n+1];
        Arrays.fill(arr,-1);
        arr[0] = 0;
        System.out.println("Minimum perfect squares required for "+n+": "+findMinimumSquaresToTotal(n,arr));

    }
}
