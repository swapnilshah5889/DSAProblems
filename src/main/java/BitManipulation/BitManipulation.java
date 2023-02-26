package BitManipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BitManipulation {


    //Find single non repeating number in array of 2 times repeating numbers
    public static int singleNumber(final List<Integer> B) {

        int ans = B.get(0);
        for(int i=1; i<B.size(); i++){
            ans = ans^B.get(i);
        }

        return ans;
    }

    //Find single non repeating number in array of 3 times repeating numbers
    public static int singleNumberThreeRepeating(final List<Integer> B) {

        // This repeat count can be any number
        // you can set it to 4 or 5 or any number for that many repetition of numbers in the array
        int repeatCount = 3;

        ArrayList<Integer> A = new ArrayList<>();
        for(int i=0; i<B.size(); i++){
            A.add(B.get(i));
        }
        StringBuilder st = new StringBuilder();
        ArrayList<Integer> bitCount = new ArrayList<>();
        for(int i=0; i<32; i++){
            int count = 0;
            for(int j=0; j<A.size();j++){
                int val = A.get(j);
                if(val%2==1){
                    count++;
                }
                A.set(j, val/2);
            }
            bitCount.add(count);
        }
        int ans = 0;
        for(int i=0; i<bitCount.size(); i++){
            if(bitCount.get(i)%repeatCount>0){
                ans+= Math.pow(2,i);
            }
        }

        return ans;

    }

    //Find number of set bits (Count number of 1s in the binary representation)
    public static int numSetBits(int A) {
        int count = 0;
        while(A>0){
            if(A%2==1)
                count++;
            A/=2;
        }
        return count;
    }

    //Add two binary numbers in string format
    public static String addBinaryNumbers(String A, String B) {

        boolean hasCarry = false;

        //Append 0s in B to make length equal to A
        if(B.length() < A.length()){
            int diff = A.length()-B.length();
            StringBuilder st = new StringBuilder();
            for(int i=0; i<diff; i++){
                st.append('0');
            }
            B = st.toString()+B;
        }
        //Append 0s in A to make length equal to B
        else if(B.length() > A.length()){
            int diff = B.length()-A.length();
            StringBuilder st = new StringBuilder();
            for(int i=0; i<diff; i++){
                st.append('0');
            }
            A = st.toString()+A;

        }

        int size = A.length();
        StringBuilder st = new StringBuilder();

        for(int i=0; i<size; i++){
            char a = A.charAt(size-1-i);
            char b = B.charAt(size-1-i);

            // 1==1 || 0==0
            if(a==b){

                //Carry = 1
                if(hasCarry)
                    st.insert(0,'1');
                    //Carry = 0
                else
                    st.insert(0,'0');

                // if 1==1
                if(a=='1')
                    hasCarry = true;
                    //if 0==0
                else
                    hasCarry = false;

            }

            // 1==0 || 0==1
            else{

                //Carry = 1
                if(hasCarry){
                    st.insert(0,'0');
                    hasCarry = true;
                }
                //Carry = 0
                else{
                    st.insert(0,'1');
                    hasCarry = false;
                }

            }

        }

        //Carry = 1
        if(hasCarry)
            st.insert(0,'1');

        return st.toString();

    }

    //If you can do 2 operations
    // 1) divide number into two numbers
    // 2) XOR of 2 numbers
    // Find out if you can reduce down the input array to 0
    public static String InterstingArray(ArrayList<Integer> A) {

        int ans = 0;
        for(int i=0; i<A.size(); i++){
            ans = ans^A.get(i);
        }

        //If all numbers were xor'd and the end product is even then it can be divided into 2 equal even numbers and xor'd to 0
        if((ans&1)==0)
            return "Yes";
        //If odd number remains in the end it is not possible to break it down to 0
        //As xor of any number even and odd number will always be odd as last bit would be 0(even) and 1(odd) whose xor is 1
        else
            return "No";
    }

    public static int firstMissingPositive(List<Integer> A) {
        int start = 1;
        int end = A.size();

        int i=0;
        while(i<end-1){

            //Case 1 : In our range
            if(A.get(i)>=start && A.get(i)<=end){

                //if not at correct position
                if( (i+1) != A.get(i)){

                    //check value at correct position
                    if( A.get(A.get(i)-1) != A.get(i) ){
                        int temp = A.get(A.get(i)-1);
                        A.set(A.get(i)-1, A.get(i));
                        A.set(i, temp);
                    }
                    //if value already at correct position, go to next position
                    else
                        i++;
                }
                //if at correct position then go to next position
                else
                    i++;

            }
            //if value not in range go to next position
            else
                i++;

        }


        for(i=0; i<end; i++){
            if(A.get(i)!=(i+1))
                return i+1;
        }

        return end+1;
    }

    public static int cntBits(List<Integer> A) {
        int mod = (int)Math.pow(10,9) + 7;
        long totalDiff = 0;
        for(int i=0; i<32; i++){
            int setCount = 0;
            int bitChecker = (1<<i);
            for(int j=0; j<A.size(); j++)
                if( (A.get(j) & bitChecker) > 0 )
                    setCount++;

            long val = (long)setCount * ((long)A.size()-setCount)*2;

            totalDiff += (val%mod);
            totalDiff = totalDiff%mod;

        }

        return (int)totalDiff;
    }

    //Binary to decimal conversion
    public static int BinaryToDecimal(String binary){

        int ans = 0;
        for(int i=0; i<binary.length(); i++){
            if(binary.charAt(binary.length()-1-i)=='1')
                ans+= Math.pow(2,i);
        }

        return ans;
    }

    //Decimal to binary conversion
    public static String DecimalToBinary(int num) {
        StringBuilder st = new StringBuilder();
        while (num > 0) {
            if (num % 2 == 1)
                st.insert(0, '1');
            else
                st.insert(0, '0');
            num /= 2;
        }
        return st.toString();
    }

    public static void main(String[] args) {

        //System.out.println( BinaryToDecimal(addBinaryNumbers(DecimalToBinary(10), DecimalToBinary(6))) );

        //Integer[] arr = new Integer[]{1,3,5};
        //System.out.println(cntBits(Arrays.asList(arr)));

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        map.put(2,1);
        map.put(3,1);
        if(map.get(1)>1)
        System.out.println(map.get(1));
    }
}
