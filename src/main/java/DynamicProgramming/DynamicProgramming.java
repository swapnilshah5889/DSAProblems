package DynamicProgramming;

import java.util.Arrays;

public class DynamicProgramming {

    // Find Nth Fibonacci Number
    public static int findFib(int n, int[] arr) {
        if(n == 1 || n == 2) {
            return arr[n];
        }

        if(arr[n] != -1) {
            return arr[n];
        }

        int ans = findFib(n-1, arr) + findFib(n-2, arr);
        arr[n] = ans;
        return ans;
    }

    public static void main(String[] args) {

        // Find Nth Fibonacci number
        int n = 8;
        int arr[] = new int[n+1];
        Arrays.fill(arr,-1);
        arr[1] = 1;
        arr[2] = 1;
        System.out.println("Fibonacci "+n+": "+findFib(n, arr));
    }
}
