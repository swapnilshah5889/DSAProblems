package BinarySearch;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

    //Find the smallest element in a sorted and rotated element
    public static int findSmallestElement(List<Integer> A){
        //Find starting point
        int l=0, r = A.size()-1;
        int mid = (l+r)/2;
        int startPoint = 0;
        while(l<=r){

            System.out.println(mid);
            //Edge Case: Mid at start
            if(mid == 0){
                startPoint = A.get(0)<A.get(1)?0:1;
                break;
            }
            //Edge Case: Mid at last
            if(mid == A.size()-1){
                startPoint = A.get(A.size()-1)<A.get(A.size()-2)?A.size()-1:A.size()-2;
                break;
            }

            //Found the point of beginning
            if(A.get(mid)>A.get(mid+1)){
                startPoint = mid+1;
                break;
            }
            else if(A.get(mid)<A.get(mid-1)){
                startPoint = mid;
                break;
            }
            //If leftmost greater than rightmost and mid greater than rightmost
            //Reject Left
            else if(A.get(l)>A.get(r) && A.get(mid)>A.get(r)){
                //Mid a Better Contender than Current Contender
                l = mid+1;
            }
            //If leftmost greater than rightmost and mid less than rightmost
            //Reject Right
            else if(A.get(l)>A.get(r) && A.get(mid)<A.get(r)){
                r = mid-1;
            }
            else{
                r = mid-1;
            }

            if(A.get(startPoint)>A.get(mid))
                startPoint = mid;

            mid = (l+r)/2;
        }

        return startPoint;
    }

    public static int findIndexOfSmallestElement(int[] A){
        int i=1;
        while(i<A.length){
            if(A[i-1]>A[i])
                break;
            i++;
        }
        if(i==A.length)
            return 0;
        else
            return i;
    }

    //Find the smallest element in a sorted and rotated element
    //Containing duplicate elements
    public static boolean findSmallestElementII(int[] A, int B) {
        if(A.length==1){
            if(A[0]==B)
                return true;
            return false;
        }
        int startPoint = findIndexOfSmallestElement(A);
        int l = 0, r=A.length-1;

        //Start point at first index: No rotation detected
        if(startPoint==0){

            if(B>=A[0] && B<=A[A.length-1]){
                l=0;
                r = A.length-1;
            }
            else{
                return false;
            }
        }
        //Start Point in the end: Array rotated by 1
        else if(startPoint == A.length-1){
            if(B == A[startPoint])
                return true;
            else {
                l=0;
                r = A.length-2;
            }
        }
        //Element in First half
        else if(B>=A[0] && B<=A[startPoint-1]){
            l = 0;
            r = startPoint-1;
        }
        //Element in the second half
        else if(B>=A[startPoint] && B<=A[A.length-1]){
            l = startPoint;
            r = A.length-1;
        }

        if(B>=A[l] && B<=A[r]){
            int mid = (l+r)/2;
            while(l<=r){
                if(A[mid]==B)
                    return true;
                    //Reject left
                else if(A[mid]<B){
                    l = mid+1;
                }
                //Reject right
                else if(A[mid]>B){
                    r = mid-1;
                }
                mid = (l+r)/2;
            }

            return false;
        }
        else
            return false;
    }

    //Find element B in a sorted and rotated array
    //Uses Function: findSmallestElement
    public static int FindElementRotatedList(List<Integer> A, int B){

        int startPoint = findSmallestElement(A);

        int l = 0, r=A.size()-1;

        //Start point at first index: No rotation detected
        if(startPoint==0){

            if(B>=A.get(0) && B<=A.get(A.size()-1)){
                l=0;
                r = A.size()-1;
            }
            else{
                return -1;
            }
        }
        //Start Point in the end: Array rotated by 1
        else if(startPoint == A.size()-1){
            if(B == A.get(startPoint))
                return startPoint;
            else {
                l=0;
                r = A.size()-2;
            }
        }
        //Element in First half
        else if(B>=A.get(0) && B<=A.get(startPoint-1)){
            l = 0;
            r = startPoint-1;
        }
        //Element in the second half
        else if(B>=A.get(startPoint) && B<=A.get(A.size()-1)){
            l = startPoint;
            r = A.size()-1;
        }

        if(B>=A.get(l) && B<=A.get(r)){
            int mid = (l+r)/2;
            while(l<=r){
                if(A.get(mid)==B)
                    return mid;
                //Reject left
                else if(A.get(mid)<B){
                    l = mid+1;
                }
                //Reject right
                else if(A.get(mid)>B){
                    r = mid-1;
                }
                mid = (l+r)/2;
            }

            return -1;
        }
        else
            return -1;
    }

    //Binary Search : Returns true if element found
    public static boolean binarySearch(List<Integer> A, int B){

        int l = 0;
        int r = A.size()-1;

        if(B>=A.get(l)&&B<=A.get(r)){
            int mid = (l+r)/2;
            while(l<=r){
                System.out.println();
                //Value Found
                if(A.get(mid)==B)
                    return true;
                //Reject Right
                else if(B<A.get(mid))
                    r=mid-1;
                //Reject Left
                else
                    l = mid+1;

                mid = (l+r)/2;
            }

        }

        return false;
    }

    //Search element B in sorted matrix A
    //Uses Function: binarySearch
    public static boolean matrixBinarySearch(List<List<Integer>> A, int B){
        for(List<Integer> list : A){
            if(binarySearch(list, B))
                return true;
        }

        return false;
    }

    //Median of 2 sorted arrays
    public static double findMedianSortedArrays(final List<Integer> a, final List<Integer> b){

        int n = a.size();
        int m = b.size();

        if(n > m)
            return findMedianSortedArrays(b, a);

        int low = 0, high = n;

        while(low <= high) {
            int i = (low+high) / 2;
            int j = (m+n+1)/2 - i;

            int left1 = i==0? Integer.MIN_VALUE : a.get(i-1);
            int left2 = j==0? Integer.MIN_VALUE : b.get(j-1);
            int right1 = i==n? Integer.MAX_VALUE : a.get(i);
            int right2 = j==m? Integer.MAX_VALUE : b.get(j);

            //Median Found
            if(left1<=right2 && left2<=right1){
                if((m+n)%2 == 0)
                    return (Math.max(left1,left2)+Math.min(right1, right2))/2.0;
                else
                    return Math.max(left1,left2);
            }

            if(left1>right2){
                high = i-1;
            }

            if(left2>right1)
                low = i+1;

        }
        return 0.0;

    }

    public static boolean checkSpecialIntegerSum(List<Long> A, int B, int k){

        if(k==0)
            return false;
        if(A.get(k-1)>B)
            return false;

        for(int i=0; i<A.size()-k; i++){
            if( (A.get(i+k) - A.get(i))>B )
                return false;
        }

        return true;
    }
    public static int specialInteger(List<Integer> A, int B){
        int l=0, r=A.size()-1;

        ArrayList<Long> preSum = new ArrayList<>();
        if(A.get(0)>B)
            return 0;
        preSum.add((long)A.get(0));

        for(int i=1; i<A.size(); i++){
            preSum.add(preSum.get(i-1)+A.get(i));
            if(A.get(i)>B)
                return 0;
        }

        int mid = (l+r)/2;
        int ans = 1;

        while(l<=r){
            if(checkSpecialIntegerSum(preSum, B, mid)){
                ans = mid;
                l = mid+1;
                System.out.println("Found: "+mid+" | "+l+" | "+r);
            }
            else{
                r=mid-1;
            }
            mid = (l+r)/2;
        }

        if(ans == A.size()-1 && l>r && checkSpecialIntegerSum(preSum, B, A.size()))
            ans++;
        return ans;
    }

    public static long calcMultiples(long max, long a, long b, long lcm){

        long aMul = max/a;
        long bMul = max/b;
        long abMul = max/lcm;
        return (aMul+bMul-abMul);

    }

    public static long gcd(long a, long b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }

    public static int nthMagicalNumber(int a, int b, int c){
        int mod = (int)Math.pow(10,9)+7;

        long gcd = gcd(b,c);

        long lcm = (b*c)/gcd;
        long l=Math.min(b,c);;
        long r = Long.MAX_VALUE;
        long mid = (r+l)/2;
        long ans = 1;
        while(l<=r){
            long multiples = calcMultiples(mid,b,c,lcm);
            if(multiples == a) {
                ans = mid;
                r = mid-1;
            }
            else if(multiples<a)
                l = mid+1;
            else
                r = mid-1;

            mid = (l+r)/2;
        }

        return (int)(ans%mod);
    }

    public static boolean checkCowDistance(List<Integer> A, int dist, int cows){
        int cowCnt = 1;
        int lastCow = 0;
        for(int i=1; i<A.size(); i++){
            if(A.get(i) - A.get(lastCow) >= dist){
                lastCow = i;
                cowCnt++;
                if(cowCnt==cows)
                    return true;
            }
        }
        return false;
    }

    public static int aggressiveCows(List<Integer> A, int B) {
        Collections.sort(A);
        int l = 1;
        int r = A.get(A.size()-1);
        int mid = (l+r)/2;
        int ans = 1;
        while(l<=r){
            System.out.println(mid);
            if(checkCowDistance(A, mid, B)){
                l = mid+1;
                ans = mid;
            }
            else{
                r = mid-1;
            }
            mid = (l+r)/2;
        }
        return ans;
    }

    public static boolean checkPaintTime(int workers, int time, List<Integer> slots){
        int count = 1;
        int tempWork = slots.get(0);
        for(int i=1; i<slots.size(); i++){
            if((tempWork+slots.get(i))<=time){
                tempWork+=slots.get(i);
            }
            else{
                count++;
                tempWork = slots.get(i);
                if(count>workers)
                    return false;
            }

        }
        return true;
    }

    public static int paintersPartition(int A, int B, List<Integer> C) {
        int mod = 10000003;
        int max = C.get(0);
        int sumTotal = C.get(0);
        for(int i=1; i<C.size();i++){
            sumTotal+=C.get(i);
            if(C.get(i)>max)
                max=C.get(i);
        }
        if(C.size()<=A){
            return (max*B)%mod;
        }
        else{
            int l=max;
            int r = sumTotal;
            int mid = (l+r)/2;
            int ans = sumTotal;
            while(l<=r){
                System.out.println(mid);
                if(checkPaintTime(A,mid,C)){
                    ans = mid;
                    r = mid-1;
                }
                else{
                    l = mid+1;
                }
                mid = (l+r)/2;
            }
            return (ans*B)%mod;
        }
    }

    public static int getSqrt(int x) {
        if(x==0)
            return 0;
        long l = 1;
        long r = x;
        long ans = 1;
        long mid = (l+r)/2;
        while(l<=r){
            long powVal = mid*mid;
            if(powVal == x){
                return (int)mid;
            }
            else if(powVal<x){
                if(mid>ans)
                    ans = mid;
                l = mid+1;
            }
            else{
                r = mid-1;
            }
            mid = (l+r)/2;
        }
        return (int)ans;
    }

    //Find minimum element in a rotated sorted array (Containing duplicate elements)
    public static int findMinimumElementII(int[] A) {
        if(A.length==1)
            return A[0];
        int l=0, r = A.length-1;
        int mid = (l+r)/2;
        int startPoint = mid;
        while(l<=r){

            //Edge Case: Mid at start
            if(mid == 0){
                startPoint = A[0]<A[1]?0:1;
                break;
            }
            //Edge Case: Mid at last
            if(mid == A.length-1){
                startPoint = A[A.length-1]<A[A.length-2]?A.length-1:A.length-2;
                break;
            }

            //Found the point of beginning
            if(A[mid]>A[mid+1]){
                startPoint = mid+1;
                break;
            }
            else if(A[mid]<A[mid-1]){
                startPoint = mid;
                break;
            }
            //If leftmost greater than rightmost and mid greater than rightmost
            //Reject Left
            else if(A[l]>A[r] && A[mid]>A[r]){
                //Mid a Better Contender than Current Contender
                l = mid+1;
            }
            //If leftmost greater than rightmost and mid less than rightmost
            //Reject Right
            else if(A[l]>A[r] && A[mid]<A[r]){
                r = mid-1;
            }
            else{
                r--;
            }

            if(A[startPoint]>A[mid])
                startPoint = mid;

            mid = (l+r)/2;
        }

        return A[startPoint];
    }

    public static int binarySearch(ArrayList<Integer> A, int B, int l, int r, int d) {
        if(l<0 || r>=A.size()) {
            return -1;
        }
        int mid = (l+r) / 2;
        while(l<=r) {

            if(A.get(mid) == B) {
                return mid;
            }
            // Increasing order
            else if(d == 0) {
                if(A.get(mid) > B) {
                    r=mid-1;
                }
                else {
                    l=mid+1;
                }
            }
            // Decreasing order
            else {
                if(A.get(mid) > B) {
                    l=mid+1;
                }
                else {
                    r=mid-1;
                }
            }
            mid = (l+r)/2;
        }
        if(mid<0 || mid>=A.size())
            return -1;
        return A.get(mid)==B? mid : -1;
    }

    public static int findBitonicElement(ArrayList<Integer> A, int B) {
        int l=0, r=A.size()-1;
        int mid = (l+r)/2;
        while(l<r) {
            if(mid==0) {
                if(A.get(mid+1)>A.get(mid)) {
                    mid = mid + 1;
                }
                break;
            }
            else if(mid == A.size()-1) {
                if(A.get(mid-1)>A.get(mid)) {
                    mid = mid-1;
                }
                break;
            }
            // target found
            else if(A.get(mid-1) < A.get(mid) && A.get(mid+1) < A.get(mid)) {
                break;
            }
            // Increasing sequence
            else if(A.get(mid-1) < A.get(mid) && A.get(mid+1) > A.get(mid)) {
                l=mid+1;
            }
            //
            else {
                r=mid-1;
            }

            mid = (l+r)/2;
        }
        int ans = binarySearch(A, B, 0, mid, 0);
        if(ans == -1) {
            ans = binarySearch(A, B, mid, A.size()-1, 1);
        }
        return ans;
    }

    public static int binarySearchCount(ArrayList<Integer> A, int target) {
        int l = 0;
        int r = A.size()-1;
        int mid = (l+r)/2;
        while(l<r) {
            if(A.get(mid) == target) {
                l=mid+1;
            }
            else if(A.get(mid) < target) {
                l = mid+1;
            }
            else {
                r=mid;
            }

            mid = (l+r)/2;

        }
        if(A.get(mid)<=target) {
            return mid+1;
        }
        return mid;
    }

    public static int matrixMedian(ArrayList<ArrayList<Integer>> A) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<A.size(); i++) {
            min = Math.min(min, A.get(i).get(0));
            max = Math.max(max, A.get(i).get(A.get(0).size()-1));
        }

        int goal = (A.size()*A.get(0).size())/2+1;
        System.out.println("Goal: "+goal);
        int mid = (min+max)/2;
        while(min<max) {
            System.out.println(min+" - "+mid+" - "+max);
            int total = 0;
            for(int i=0;i<A.size(); i++) {
                total+= binarySearchCount(A.get(i), mid);
            }
            System.out.println("total: "+total);

            if(total == goal) {
                break;
            }
            else if(total<goal) {
                min = mid+1;
            }
            else {
                max = mid;
            }

            mid = (min+max)/2;

        }
        System.out.println(mid);
        int ans = Integer.MIN_VALUE;
        for(int i=0; i<A.size(); i++) {
            int l = 0;
            int r = A.get(0).size()-1;
            int mid1 = (l+r)/2;
            while(l<=r) {

                if(A.get(i).get(mid1) == mid) {
                    ans = Math.max(ans, A.get(i).get(mid1));
                    break;
                }
                else if(A.get(i).get(mid1) < mid) {
                    ans = Math.max(ans, A.get(i).get(mid1));
                    l = mid1+1;
                }
                else {
                    r = mid1-1;
                }

                mid1 = (l+r)/2;
            }
        }

        return ans;
    }

    public static int kthElement(final List<Integer> A, int B) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i : A) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        int mid = (min+max)/2;
        while(min<max) {
            System.out.println(min+" - "+mid+" - "+max);
            int count = 0;
            for(int i=0; i<A.size(); i++) {
                if(A.get(i)<=mid) {
                    count++;
                }
            }
            System.out.println("Count: "+count);
            if(count == B) {
                break;
            }
            else if(count < B) {
                min = mid+1;
            }
            else {
                max = mid;
            }

            mid = (min+max)/2;

        }
        int ans = Integer.MIN_VALUE;
        for(int i=0;i<A.size(); i++) {
            if(A.get(i)>ans && A.get(i)<=mid) {
                ans = A.get(i);
            }
        }

        return ans;
    }

    public static int findPotionIndex(int[] potions, int success) {
        /*for(long i:potions) {
            System.out.print(i+" ");
        }
        System.out.println();*/
        int l=0, r=potions.length-1;
        int mid = (l+r) / 2;
        while(l<r) {

            // Edge case
            if(mid == potions.length-1) {
                if(potions[mid]<success) {
                    return potions.length;
                }
                else {
                    return potions.length - 1;
                }
            }

            // Target found
            if(potions[mid]<success && potions[mid+1]>=success) {
                break;
            }
            // Right
            else if(potions[mid] >= success) {
                r=mid;
            }
            // Left
            else {
                l = mid+1;
            }
            mid = (l+r)/2;
        }
        if(potions[mid] >= success) {
            mid--;
        }
        return mid;
    }
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] ans = new int[spells.length];
        for(int k=0; k<spells.length; k++) {
//            System.out.println("Spell: "+spells[k]);
            int target = (int) Math.ceil((double)success/spells[k]);
            int index = findPotionIndex(potions, target);
            ans[k] = potions.length - index - 1;
        }

        for(int i : ans) {
            System.out.print(i+" ");
        }
        System.out.println();
        return ans;
    }

    public static long getTotalHours(int[] piles, int capactiy) {
        long sum = 0;
        for(int i : piles) {
            sum += (int)Math.ceil((double) i/capactiy);
        }
        return sum;
    }

    public static int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int min=0, max=piles[0];
        for(int i=1; i<piles.length; i++) {
            max = Math.max(piles[i], max);
        }

        int mid = (min+max)/2;
        while(min<max) {
            long hours = getTotalHours(piles, mid);
            System.out.println(min+" - "+mid+" - "+max+" ==> "+hours);

            if(hours > h) {
                min=mid+1;
            }
            else {
                max=mid;
            }
            mid = (min+max)/2;
        }

        return mid;
    }

    public static void main(String args[]){

        //Find element in rotated array
        /*Integer[] arr = new Integer[]{194, 195, 196, 197, 198, 199, 201, 203, 204, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 20, 21, 22, 23, 24, 25, 26, 27, 29, 30, 31, 32, 33, 34, 35, 36, 37, 39, 40, 42, 43, 44, 45, 47, 48, 49, 50, 51, 52, 53, 54, 55, 57, 58, 59, 60, 61, 63, 65, 66, 68, 69, 70, 71, 73, 74, 76, 77, 79, 80, 81, 82, 83, 84, 86, 87, 88, 89, 91, 92, 93, 94, 95, 97, 98, 99, 101, 103, 104, 105, 106, 107, 108, 109, 110, 113, 114, 115, 117, 118, 120, 121, 122, 123, 124, 127, 128, 130, 131, 133, 134, 135, 136, 137, 139, 140, 141, 142, 143, 144, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 158, 159, 160, 161, 162, 163, 164, 166, 167, 169, 170, 171, 172, 174, 175, 177, 178, 179, 181, 182, 184, 185, 187, 189, 190, 192, 193};
        System.out.println(findSmallestElement(Arrays.asList(arr)));
        System.out.println(FindElementRotatedList(Arrays.asList(arr),1));*/

        //Matrix Binary Search
        /*List<List<Integer>> list = new ArrayList<>();
        Integer[] arr = new Integer[]{2, 3, 4, 6};
        list.add(Arrays.asList(arr));
        arr = new Integer[]{16, 19, 33, 36};
        list.add(Arrays.asList(arr));
        arr = new Integer[]{37, 38, 38, 41};
        list.add(Arrays.asList(arr));
        arr = new Integer[]{47, 47, 50, 51};
        list.add(Arrays.asList(arr));
        arr = new Integer[]{55, 57, 58, 62};
        list.add(Arrays.asList(arr));
        arr = new Integer[]{63, 65, 66, 66};
        list.add(Arrays.asList(arr));
        arr = new Integer[]{68, 70, 75, 77};
        list.add(Arrays.asList(arr));
        arr = new Integer[]{78, 84, 84, 86};
        list.add(Arrays.asList(arr));
        arr = new Integer[]{86, 87, 88, 92};
        list.add(Arrays.asList(arr));
        arr = new Integer[]{94, 95, 96, 97};
        list.add(Arrays.asList(arr));
        System.out.println(matrixBinarySearch(list, 81));*/

        //Find median of 2 sorted arrays
        /*List<Integer> list1 = Arrays.asList(9,10);
        List<Integer> list2 = Arrays.asList(1,5,8);
        System.out.println(findMedianSortedArrays(list1,list2));*/

        //Find the special integer
        /*List<Integer> list = Arrays.asList(78, 45, 80, 78, 83, 89, 71, 68, 70, 41, 21, 76, 50, 66, 40, 35, 32, 90, 70, 95, 95, 65, 55, 80, 67, 29, 64, 68, 89, 59, 58, 5, 29, 6, 13, 33, 91, 5, 72, 3, 34, 63, 13, 96, 75, 91, 27, 28, 100, 47, 45, 93, 39, 2, 1, 75, 29, 22, 51, 71, 46, 63, 31, 75, 72, 6, 97, 34, 16, 34, 76, 89, 23, 82, 84, 33, 83, 62, 31, 56, 47, 21, 53, 76, 70, 14, 30, 35, 45, 38, 94, 2, 43, 48, 86, 38, 87, 67, 67, 52, 72, 88, 50, 56, 86, 18, 79, 3, 85, 18, 3, 9, 84, 63, 86, 78, 98, 14, 94, 83, 85, 52, 14, 28, 69, 68, 92, 36, 39, 62, 13, 63, 95, 81, 30, 75, 71, 35, 89, 70, 58, 25, 43, 56, 18, 52, 82, 39, 81, 9, 32, 33, 97, 42, 79, 98, 70, 85, 6, 75, 32, 41, 84, 30, 24, 92, 93, 30, 16, 38, 85, 1, 56, 17, 3, 92, 76, 67, 88, 42, 61, 21, 4, 10, 62, 45, 5, 67, 16, 71, 33, 33, 1, 69, 23, 97, 96, 49, 49, 3, 66, 97, 84, 47, 41, 58, 15, 13, 52, 99, 56, 77, 29, 35, 55, 8, 74, 50, 83, 36, 100, 30, 20, 98, 20, 93, 23, 22, 75, 33, 69, 95, 13, 94, 35, 51, 42, 33, 45, 10, 81, 77, 63, 29, 10, 63, 92, 69, 43, 5, 23, 24, 56, 58, 70, 22, 37, 39, 4, 18, 4, 16, 28, 63, 66, 3, 32, 82, 35, 65, 67, 95, 39, 27, 51, 88, 84, 50, 100, 85, 23, 55, 34, 60, 4, 12, 82, 11, 33, 26, 23, 38, 83, 17, 77, 91, 40, 80, 48, 87, 51, 49, 71, 45, 63, 48, 97, 90, 19, 85, 70, 26, 70, 2, 22, 6, 21, 18, 17, 32, 13, 8, 1, 68, 64, 85, 38, 77, 76, 96, 1, 75, 12, 21, 89, 76, 1, 17, 8, 81, 37, 54, 62, 97, 19, 93, 14, 96, 35, 95, 89, 51, 12, 47, 84, 59, 85, 8, 29, 49, 80, 78, 41, 25, 82, 48, 44, 42, 85, 26, 38, 1, 100, 6, 88, 12, 89, 33, 9, 11, 46, 8, 78, 88, 32, 100, 20, 71, 13, 46, 10, 9, 91, 88, 59, 16, 56, 37, 34, 54, 27, 27, 29, 1, 28, 51, 21, 1, 13, 16, 49, 68, 69, 45, 74, 7, 4, 38, 65, 53, 30, 89, 50, 98, 74, 23, 26, 85, 66, 81, 30, 28, 45, 22, 86, 78, 37, 82, 30, 39, 92, 93, 27, 41, 8, 20, 37, 22, 71, 91, 18, 42, 26, 43, 30, 87, 35, 32, 57, 5, 8, 21, 7, 62, 13, 62, 64, 41, 99, 16, 36, 96, 72, 53, 1, 9, 47, 96, 93, 35, 1, 14, 70, 88, 28, 34, 60, 91, 64, 71, 96, 99, 9, 34, 95, 25, 26, 77, 88, 94, 34, 73, 100, 18, 53, 38, 52, 19, 72, 43, 47, 92, 21, 79, 93, 7, 52, 44, 28, 13, 1, 78, 29, 66, 1, 49, 57, 88, 8, 35, 100, 56, 24, 63, 30, 32, 15, 77, 55, 84, 14, 11, 21, 72, 78, 94, 39, 52, 81, 67, 71, 9, 29, 65, 19, 36, 34, 89, 92, 65, 28, 21, 23, 92, 51, 11, 15, 72, 73, 49, 81, 2, 22, 34, 24, 95, 27, 45, 76, 18, 60, 98, 70, 81, 92, 48, 72, 61, 4, 31, 65, 89, 51, 39, 57, 81, 58, 4, 85, 34, 58, 71, 16, 100, 57, 55, 37, 55, 37, 64, 41, 64, 31, 34, 74, 89, 32, 96, 24, 10, 14, 96, 55, 7, 11, 47, 97, 3, 44, 61, 94, 54, 18, 3, 20, 1, 12, 81, 34, 84, 31, 22, 22, 67, 76, 11, 45, 95, 63, 99, 48, 9, 58, 69, 38, 6, 10, 7, 75, 48, 72, 25, 30, 12, 53, 93, 94, 23, 54, 55, 17, 32, 46, 44, 64, 94, 98, 62, 31, 29, 56, 73);
        //System.out.println(specialInteger(list, 99693));
        list = Arrays.asList(1, 1000000000);
        System.out.println(specialInteger(list, 1000000000));*/

        //Find nth magical number
        /*System.out.println(nthMagicalNumber(807414236, 3788, 38141));*/

        //Aggressive Cows
        /*List<Integer> cowStands = Arrays.asList(6939, 2057, 3094, 9698, 6214, 9667, 8682, 6309, 928, 1603, 3194, 6188, 1170, 923, 7887, 3932, 9083, 1924, 2795, 1686, 3189, 303, 2254, 1148, 3312, 7357, 6162, 8194, 2923, 4763, 5314, 5499, 1271, 4919, 1067, 9698, 8553, 8097, 1883, 3478, 3542, 1702, 1872, 9795, 4593, 8876, 9385, 4873, 1747, 3290, 6890, 9086, 9782, 4590, 6903, 9879, 9490, 4179, 2588, 8976, 7187, 4223, 5784, 6836, 553, 3685, 9665, 943, 2222, 3969, 8065, 953, 1269, 3293, 8518, 2915, 6713, 3349, 1516, 6851, 6451, 3950, 4186, 6825, 9691, 1418, 2594, 286, 836, 4261, 3261, 3971, 8077, 5323, 1081, 6916, 429, 3074, 7832, 7264, 2915, 5905, 2719, 8433, 669, 2423, 3997, 1971, 9329, 7601, 8609, 8946, 828, 9315, 4980, 9069, 842, 2360, 2675, 6945, 6568, 7320, 1389, 7780, 1267, 9794, 983, 699, 5054, 1474, 3659, 6423, 9781, 8294, 9193, 7450, 9549, 2426, 4218, 3161, 7385, 4354, 6944, 9244, 2901, 8350, 9416, 756, 5405, 9802, 457, 755, 1838, 5560, 3746, 1929, 9178, 9181, 7151, 6960, 6477, 1342, 1417, 4424, 2615, 2008, 8543, 995, 1974, 4946, 2798, 7389, 1793, 7131, 243, 5081, 1721, 5231, 5777, 3159, 8234, 8907, 326, 9823, 4002, 5947, 4584, 1060, 5042, 7445, 9860, 9781, 6533, 2109, 1103, 2320, 1568, 7620, 1883, 5632, 5780, 3461, 6230, 7700, 4753, 4094, 7644, 5788, 497, 3490, 1282, 1934, 7819, 2077, 1345, 2823, 1375, 4980, 2051, 1604, 9482, 6038, 7226, 2550, 1218, 5572, 479, 5516, 9335, 1104, 5248, 6113, 2576, 9313, 1545, 9210, 7574, 4387, 4176, 7933, 2239, 2668, 5373, 7687, 9732, 5922, 3351, 4871, 4493, 5973, 7109, 2969, 9577, 1498, 6939, 4454, 7583, 3801, 9612, 5576, 5851, 2065, 6935, 2507, 264, 228, 6487, 1535, 1001, 6966, 3742, 3066, 7882, 7464, 5503, 9020, 2575, 712, 5570, 5891, 9871, 8824, 4944, 1216, 441, 5537, 483, 9469, 3112, 9539, 7917, 4533, 5496, 4435, 7368, 9814, 8041, 1349, 2166, 3730, 8035, 1940, 4595, 7965, 6930, 9387, 5433, 8094, 6429, 2350, 7709, 1485, 6264, 2295, 8888, 5246, 9291, 7625, 9878, 7856, 7611, 9097, 3697, 1693, 6786, 9698, 1441, 79, 3908, 8752, 8239, 1713, 9252, 4414, 7319, 7377, 586, 903, 6019, 8688, 3795, 6827, 3566, 6304, 3428, 8786, 6831, 5478, 2020, 78, 6889, 161, 7588, 7762, 6186, 2941, 4572, 2596, 3384, 7024, 4687, 5476, 560, 8199, 630, 817, 5304, 9265, 7389, 2093, 8507, 6748, 3047, 7190, 7136, 9913, 3375, 1609, 141, 5033, 2271, 5654, 4775, 3702, 3145, 7692, 85, 2551, 4737, 9347, 4104, 3152, 8527, 50, 1362, 5132, 3869, 7750, 7729, 2549, 1345, 9795, 316, 3450, 4378, 3004, 1204, 5163, 4141, 4765, 5076, 3412, 3008, 7628, 2505, 1564, 7356, 8318, 2610, 7490, 1396);
        System.out.println(aggressiveCows(cowStands, 385));*/

        //Painter's Partition
        /*List<Integer> list = Arrays.asList(185, 186, 938, 558, 655, 461, 441, 234, 902, 681);
        System.out.println(paintersPartition(3,10,list));*/

        //Get Square Root
        /*System.out.println(getSqrt(2147395599));*/

        //Find minimum element in rotated sorted array containing duplicate elements
        /*System.out.println(findMinimumElementII(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1}));*/

        //Search for element in a rotated sorted array containing duplicate elements
        /*System.out.println(findSmallestElementII(new int[]{2,2,3,3,4,5,5,5,6,6,7,7,8,9,9,11,0,0,1,2}, 12));*/

        // Search in bitonic array
        /*System.out.println(findBitonicElement(new ArrayList<>(List.of(1,2,5,6,15,14,12,9,8,5,3,1)), 9));
        System.out.println(findBitonicElement(new ArrayList<>(List.of(3, 9, 10, 20, 3, 1)), 9));
        System.out.println(findBitonicElement(new ArrayList<>(List.of(5, 7, 10, 3, 2, 1)), 9));*/

        // Matrix Median
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        /*A.add(new ArrayList<>(Arrays.asList(1,3,5)));
        A.add(new ArrayList<>(Arrays.asList(2,6,9)));
        A.add(new ArrayList<>(Arrays.asList(3,6,9)));*/

        A.add(new ArrayList<>(Arrays.asList(2)));
        A.add(new ArrayList<>(Arrays.asList(1)));
        A.add(new ArrayList<>(Arrays.asList(4)));
        A.add(new ArrayList<>(Arrays.asList(1)));
        A.add(new ArrayList<>(Arrays.asList(2)));
        A.add(new ArrayList<>(Arrays.asList(2)));
        A.add(new ArrayList<>(Arrays.asList(5)));
        System.out.println(matrixMedian(A));

        // Find kth smallest element
        /*System.out.println(kthElement(new ArrayList<>(Arrays.asList(74,90,85,58,69,77,90,85,18,36)), 1));*/

        // Successful pairs
        /*successfulPairs(new int[]{5,1,3}, new int[]{1,2,3,4,5}, 7);
        successfulPairs(new int[]{3,1,2}, new int[]{8,5,8}, 16);
        successfulPairs(new int[]{15,8,19}, new int[]{38,36,23}, 328);*/

        // Minimum Hours to eat the Piles
        System.out.println(minEatingSpeed(new int[]{3,6,7,11}, 8));
        System.out.println(minEatingSpeed(new int[]{30,11,23,4,20}, 5));
        System.out.println(minEatingSpeed(new int[]{312884470}, 312884469));
        System.out.println(minEatingSpeed(new int[]{805306368,805306368,805306368}, 1000000000));
        /*System.out.println(kthElement(new ArrayList<>(Arrays.asList(74,90,85,58,69,77,90,85,18,36)), 1));*/
    }
}
