package DynamicProgramming;

import java.util.Arrays;

public class DynamicProgramming {

    // Find Nth Fibonacci Number
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

    public static void main(String[] args) {

        // Find Nth Fibonacci number
        int n = 1;
        int arr[] = new int[n+1];
        Arrays.fill(arr,-1);
        arr[1] = 1;
        if(arr.length>2)
            arr[2] = 1;
        System.out.println("Fibonacci(Recursive) "+n+": "+findFibTopDown(n, arr));
        System.out.println("Fibonacci(Iterative) "+n+": "+findFibBottomUp(n));
    }
}
