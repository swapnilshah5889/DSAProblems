package TwoPointers;

import java.util.*;

public class TwoPointers {

    public static List<Long> minimumCost(List<Integer> red, List<Integer> blue, int blueCost) {
        int n = red.size();
        long[][] dsp = new long[n + 1][2];
        dsp[0][0] = 0;
        dsp[0][1] = blueCost;
        List<Long> ans = new ArrayList<>();
        ans.add((long)0);
        /*for(int i = 1; i<= n; i++){
            dsp[i][0] = Math.min(dsp[i-1][0] + red.get(i - 1),dsp[i-1][1] + red.get(i-1));
            dsp[i][1] = Math.min(dsp[i-1][1] + blue.get(i-1), dsp[i-1][0] + blue.get(i-1) + blueCost);
            ans.add((long)Math.min(dsp[i][1],dsp[i][0]));
        }*/


        return ans;
    }

    public static int maxLength(int[] a, int k) {
        // Write your code here
        List<Integer> arr = new ArrayList<>();
        arr.add(a[0]);
        for (int i = 1; i < a.length; i++) {
            arr.add(a[i] + arr.get(i-1));
        }
        System.out.println(arr);
        int i = -1 ;
        int j = a.length - 1;

        while(i < j) {

            int tempI = i == -1 ? 0 : arr.get(i);
            int prevJ = j == 0 ? 0 : arr.get(j - 1);

            if(arr.get(j) - tempI <= k) {
                System.out.println(i+" | "+j);
                System.out.println(arr.get(j) - tempI);
                return j - i;
            }

            int fetchI = arr.get(i + 1) - tempI;
            int fetchJ = arr.get(j) - prevJ;

            if(fetchI > fetchJ) {
                i++;
            } else {
                j--;
            }
        }
        return 0;
    }

    public static String biggerIsGreater(String w) {
        // Write your code here
        boolean found = false;
        boolean allSame = false;
        int index = w.length()-1;
        StringBuilder st = new StringBuilder();
        for(; index>1; index--){
            char c1 = w.charAt(index);
            char c2 = w.charAt(index-1);
            if(c1>c2){
                st.insert(0, c2);
                st.insert(0, c1);
                index-=2;
                found = true;
                break;
            }
            else
                st.insert(0, c1);
        }


        if(index == 1 && !found){
            if(w.charAt(0)>w.charAt(1))
                return "no answer";
            char c = w.charAt(0);
            char charArray[] = w.toCharArray();
            Arrays.sort(charArray);

            if(charArray[0] == charArray[charArray.length-1])
                return "no answer";
            found = false;
            st = new StringBuilder();
            for(int i=0; i<charArray.length; i++){
                if(charArray[i]>c && !found){
                    found = true;
                    c = charArray[i];
                }
                else
                    st.append(charArray[i]);
            }
            st.insert(0, c);
            return st.toString();
        }
        else if(index>0 || found){
            for(; index>=0; index--){
                st.insert(0, w.charAt(index));
            }

            return st.toString();
        }
        else
            return "no answer";

    }

    public static ArrayList<Integer> subArraySum(List<Integer> A, int B) {

        int sum = 0;
        int start = 0, end =-1;
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<A.size(); i++){
            sum += A.get(i);
            if(sum>B){
                while(sum>B) {
                    sum -= A.get(start);
                    start++;
                }
            }
            if(sum==B) {
                end = i;
                break;
            }
        }

        if(end == -1){
            ans.add(-1);
            return ans;
        }
        else {
            ans.add(start);
            ans.add(end);
            return ans;
        }
    }

    //Return total number of pairs in array A with difference B
    public static int pairCountWithDifference(List<Integer> A, int B) {

        Collections.sort(A);
        System.out.println(A);
        int i=0,j=1;
        int count=0;
        while(i<j && j<A.size()) {
            int val = A.get(j)-A.get(i);
            System.out.println(i+" | "+j+" | "+val);
            if(val == B){
                i++;
                j++;
                count++;
                while(j<A.size() && A.get(j)==A.get(j-1)) {
                    j++;
                }
                while(i<j && A.get(i)==A.get(i-1)){
                    i++;
                }
            }
            else if(val<B)
                j++;
            else
                i++;
            if(i==j && j<A.size())
                j++;

        }

        return count;

    }

    public static int pairCountWithSum(List<Integer> A, int B){
        int count = 0;
        int i=0, j=A.size()-1;

        while(i<j){
            int val = A.get(i)+A.get(j);
            if(val==B){
                count++;
                i++;
                j--;
            }
            else if(val<B)
                i++;
            else
                j--;

        }


        return count;
    }

    public static void main(String[] args){


        /*int[] arr1 = new int[]{1,2,3,50,1,4,5,4,6,1,4,4,4};
        System.out.println(maxLength(arr1, 33));*/

        //Sub Array Sum
        /*List<Integer> list = Arrays.asList(42, 9, 38, 36, 48, 33, 36, 50, 38, 8, 13, 37, 33, 38, 17, 25, 50, 50, 41, 29, 34, 18, 16, 6, 49, 16, 21, 29, 41, 7, 37, 14, 5, 30, 35, 26, 38, 35, 9, 36, 34, 39, 9, 4, 41, 40, 3, 50, 27, 17, 14, 5, 31, 42, 5, 39, 38, 38, 47, 24, 41, 5, 50, 9, 29, 14, 19, 27, 6, 23, 17, 1, 22, 38, 35, 6, 35, 41, 34, 21, 30, 45, 48, 45, 37);
        System.out.println(subArraySum(list, 100));*/

        //Pair Difference
        /*List<Integer> list = Arrays.asList(1, 8, 2, 8, 8, 8, 8, 4, 4, 6, 10, 10, 9, 2, 9, 3, 7);
        System.out.println(pairCountWithDifference(list, 1));*/

        //Count distinct pairs with given sum
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(pairCountWithSum(list, 5));
    }

}
