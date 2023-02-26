package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sorting {

    public static int BubbleSort(List<Integer> A) {
        for(int i=0; i<A.size(); i++){

            for(int j=1; j<A.size()-i; j++){
                if(A.get(j-1)>A.get(j)){
                    int temp = A.get(j);
                    A.set(j, A.get(j-1));
                    A.set(j-1, temp);
                }
            }
        }
        System.out.println(A);
        for(int i=1; i<A.size(); i++){
            if( (A.get(i) - A.get(i-1)) > 1){
                System.out.println(A.get(i)+ " | " + A.get(i-1));
                return 0;
            }
        }

        return 1;
    }

    public static List<Integer> mergeSort(List<Integer> list, int l, int r){
        if(l==r)
            return list;

        int mid = (l+r)/2;
        mergeSort(list, l, mid);
        mergeSort(list, mid+1, r);

        return merge(list, l, mid+1, r);
    }

    public static List<Integer> merge(List<Integer> list, int l, int mid, int r){

        System.out.println(list);
        System.out.println(l+" | "+mid+" | "+r);
        int p1 = l;
        int p2 = mid;
        int p3 = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        if(list.size()==0)
            return temp;
        while(p1<mid && p2<=r){

            if(list.get(p1)<=list.get(p2)){
                temp.add(list.get(p1));
                p1++;
            }
            else{
                temp.add(list.get(p2));
                p2++;
            }
            p3++;
        }

        while(p1<mid){
            temp.add(list.get(p1));
            p1++;
        }

        while(p2<=r){
            temp.add(list.get(p2));
            p2++;
        }

        for(int i=l; i<=r; i++){
            list.set(i, temp.get(i-l));
        }

        return list;
    }

    public static int invCount(List<Integer> list, int l, int r, int modVal){
        if(l==r)
            return 0;
        int mid = (l+r)/2;
        //Find inversion count in first half
        int x = invCount(list, l, mid, modVal);
        //Find inversion count in second half
        int y = invCount(list, mid+1, r, modVal);
        //Find inversion count accross first and second half
        int z = invCountmerge(list, l, mid+1, r, modVal);
        //Return sum of all inversion counts
        return ((x+y)%modVal+z)%modVal;
    }

    public static int invCountmerge(List<Integer> list, int l, int mid, int r, int modVal){

        //Return 0 if original list is empty
        if(list.size()==0)
            return 0;

        int p1 = l;
        int p2 = mid;
        int count=0;
        ArrayList<Integer> temp = new ArrayList<>();

        //Merge
        while(p1<mid && p2<=r){
            //If smaller element found in first half then add it in the list
            //count will not be incremented as smaller element won't make an
            //inversion pair with second half's current element as it's greater
            //in value
            if(list.get(p1)<=list.get(p2)){
                temp.add(list.get(p1));
                p1++;
            }
            //Else if smaller element in second half then add it in the list
            //Increment the count by elements not visited in the first half
            //as they all will be greater than the current element in the
            //second half so all will contribute in the inversion pair for
            //that particular element
            else{
                temp.add(list.get(p2));
                count+=(mid-p1)%modVal;
                p2++;
            }
        }

        //Add remaining elements in first half
        while(p1<mid){
            temp.add(list.get(p1));
            p1++;
        }

        //Add remaining elements in second half
        while(p2<=r){
            temp.add(list.get(p2));
            p2++;
        }

        //Update Original List
        for(int i=l; i<=r; i++){
            list.set(i, temp.get(i-l));
        }

        return count;
    }

    public static int uniqueElements(List<Integer> A) {
        //Sort elements using merge or quick sort
        Collections.sort(A);
        System.out.println(A);
        int count=0;
        for(int i=1; i<A.size(); i++){
            if(A.get(i-1)>=A.get(i)){
                //Difference in previous value and current value + 1 to make it unique
                int val = (A.get(i-1) - A.get(i))+1;
                //Add the value to current element to make it unique
                A.set(i, A.get(i)+val);
                //Add the value to overall counter to keep track of total value incremented
                count+=val;
            }
        }
        System.out.println(A);

        return count;
    }


    public static int reversePairs(List<Integer> list, int l, int r, int modVal){
        if(l==r)
            return 0;
        int mid = (l+r)/2;

        int x = reversePairs(list, l, mid, modVal);
        int y = reversePairs(list, mid+1, r, modVal);
        int z = reversePairsMerge(list, l, mid+1, r, modVal);

        return ((x+y)%modVal+z)%modVal;
    }

    public static int reversePairsMerge(List<Integer> list, int l, int mid, int r, int modVal){

        if(list.size()==0)
            return 0;

        int p1 = l;
        int p2 = mid;
        int count=0;

        //Count
        while(p1<mid){
            while(p2<=r && 2l*list.get(p2)<list.get(p1)){
                p2++;
            }
            count+=(p2-mid);
            p1++;
        }


        p1 = l;
        p2 = mid;
        ArrayList<Integer> temp = new ArrayList<>();

        //Merge
        while(p1<mid && p2<=r){
            if(list.get(p1)<=list.get(p2)){
                temp.add(list.get(p1));
                p1++;
            }
            else{
                temp.add(list.get(p2));
                p2++;
            }
        }

        while(p1<mid){
            temp.add(list.get(p1));
            p1++;
        }

        while(p2<=r){
            temp.add(list.get(p2));
            p2++;
        }

        for(int i=l; i<=r; i++){
            list.set(i, temp.get(i-l));
        }
        return count;
    }

    public static int QuickSortMerge(List<Integer> A, int l, int r){

        int p1 = l+1;
        int p2 = r;
        int val = A.get(l);

        //Use two pointers to bring larger elements in the right and smaller in the left
        while(p1<p2){

            if(A.get(p1)<=val){
                p1++;
            }
            else if(A.get(p2)>val){
                p2--;
            }
            else{
                int temp = A.get(p1);
                A.set(p1, A.get(p2));
                A.set(p2, temp);
                p1++;
                p2--;
            }
        }

        //Swap first with left array's last
        int temp = A.get(l);
        A.set(l, A.get(p2));
        A.set(p2, temp);

        return p2;

    }

    //Sort subarray of a partially sorted array
    public static ArrayList<Integer> subUnsort(List<Integer> A) {

        int maxIndex = -1, minIndex = -1;
        ArrayList<Integer> ans = new ArrayList<>();
        int size = A.size();
        for(int i=1; i<size; i++) {
            if (A.get(i) < A.get(i - 1)) {
                if (maxIndex == -1)
                    maxIndex = i;
                else if (A.get(maxIndex) > A.get(i))
                    maxIndex = i;
                if (minIndex == -1)
                    minIndex = i - 1;
                else if (A.get(minIndex) < A.get(i - 1))
                    minIndex = i - 1;
            }
        }

        if(maxIndex ==-1 && minIndex == -1){
            ans.add(-1);
            return ans;
        }

        else{

            int rightRange = A.get(minIndex);
            int leftRange = A.get(maxIndex);
            int k = minIndex;

            //Find minimum index
            while(k>=0){
                if(A.get(k)<=rightRange && A.get(k)>leftRange)
                    minIndex = k;
                k--;
            }
            k = maxIndex;
            //Find maximum index
            while(k<size){
                if(A.get(k)<rightRange)
                    maxIndex = k;
                k++;
            }

            ans.add(minIndex);
            ans.add(maxIndex);
            return ans;
        }

    }


    public static int SumTheDifference(List<Integer> A) {
        Collections.sort(A);
        long max = 0;
        long min = 0;
        int mod = (int)Math.pow(10,9)+7;

        for(int i=0; i<A.size()-1; i++){
            max = (max+(A.get(i+1)%mod)*((long) (Math.pow(2,i+1)-1) %mod)%mod);
            min = (min+(A.get(i)%mod)*((long) (Math.pow(2,A.size()-1-i))-1 %mod)%mod);
        }
        return (int)(max-min+mod)%mod;
    }
    public static void main(String args[]) {

        //Merge sort
        /*Integer[] arr = new Integer[]{3,4,2,1,6,5};
        System.out.println(mergeSort(Arrays.asList(arr), 0, arr.length-1));*/

        //Inversion Count
        /*Integer[] arr = new Integer[]{3,4,2,1,6,5};
        int modVal = ((int)Math.pow(10,9))+7;
        System.out.println(invCount(Arrays.asList(arr), 0, arr.length-1, modVal));*/

        //Unique Elements
        /*Integer[] arr = new Integer[]{51, 6, 10, 8, 22, 61, 56, 48, 88, 85, 21, 98, 81, 76, 71, 68, 18, 6, 14, 23, 72, 18, 56, 30, 97, 100, 81, 5, 99, 2, 85, 67, 46, 32, 66, 51, 76, 53, 36, 31, 81, 56, 26, 75, 69, 54, 54, 54, 83, 41, 86, 48, 7, 32, 85, 23, 47, 23, 18, 45, 79, 95, 73, 15, 55, 16, 66, 73, 13, 85, 14, 80, 39, 92, 66, 20, 22, 25, 34, 14, 51, 14, 17, 10, 100, 35, 9, 83, 31, 60, 24, 37, 69, 62};
        System.out.println( uniqueElements(Arrays.asList(arr)) );*/

        //Reverse Pairs
        /*Integer[] arr = new Integer[]{2000000000, 2000000000, -2000000000};
        int modVal = ((int)Math.pow(10,9))+7;
        System.out.println(modVal);*/

        //Quick Sort
        /*Integer[] arr = new Integer[]{10, 15,2,4,12,6,19,20,1,3};
        List<Integer> list = Arrays.asList(arr);
        QuickSortMerge(list,0,arr.length-1);
        System.out.println(list);*/

        //Sort Sub Array
        /*Integer[] arr = new Integer[]{4, 6, 8, 8, 9, 10, 11, 13, 13, 14, 17, 20, 20, 17, 20};
        System.out.println(subUnsort(Arrays.asList(arr)));*/

        //Sum The Difference
        Integer[] arr = new Integer[]{5,4,2};
        System.out.println(SumTheDifference(Arrays.asList(arr)));

    }
}
