package Combinatorics;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Combinatorics {

    public static int getFactorial(int n, int r, int pow, int mod){
        long fact = 1;

        while(n>=r){
            fact = (fact*(n%mod))%mod;
            n--;
        }

        while(pow>1){
            fact = (fact*fact)%mod;
            pow--;
        }

        return (int)fact;
    }

    //Compute nCr%p
    public static int combinationsWithMod(int A, int B, int C) {

        //Calculate A!%C * ((B!)^(C-2))%C * ((A-B)!^(C-2))%C

        int val1 = getFactorial(A, 1, 1, C);
        int val2 = getFactorial(B, 1, C-2, C);
        int val3 = getFactorial(A-B, 1, C-2, C);

        int ans = (((val1*val2)%C)*val3)%C;
        return ans;

    }

    public static int squareRoot(int A) {

        if(A==1)
            return 1;
        else if(A==0)
            return 0;
        int l=1, r = A;
        long mid = ((long)l+r)/2;
        int ans = 1;
        while(l<=r){

            long sq = mid*mid;
            if(sq == A)
                return (int)mid;
            else if(sq<A){
                ans = (int)mid;
                l = (int)mid+1;
            }
            else
                r = (int)mid-1;

            mid = (l+r)/2;
        }

        return ans;
    }

    public static long countPairs(List<Integer> arr) {
        // Write your code here

        HashMap<Integer,Integer> set = new HashMap<>();
        for(int i=0; i<arr.size(); i++)
            if(!set.containsKey(arr.get(i)))
                set.put(arr.get(i),1);
            else
                set.put(arr.get(i),set.get(arr.get(i))+1);

        int count = 0;
        for(int i=0; i<arr.size()-1; i++){

            int mod = 1;
            while(mod<arr.get(i)){
                int temp = (arr.get(i)&mod);
                if( (temp!=0 ) && ( (temp&(temp-1))==0) )
                    if(set.containsKey(temp) && temp!=arr.get(i))
                        count++;
                    else if(set.containsKey(temp) && temp==arr.get(i))
                        if(set.get(temp)>1)
                            count++;
                mod = mod<<1;
            }

        }

        return count;
    }



    public static void main(String[] args){

    }
}
