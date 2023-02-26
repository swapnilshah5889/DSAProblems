package Arrays;

import java.util.*;

public class ArraysOneD {

    //Rain Water Trap
    public static int RainWaterTrap(final List<Integer> A) {

        int size = A.size();

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        leftList.add(A.get(0));
        rightList.add(A.get(size-1));
        for(int i=1; i<size; i++){
            int rightIndex = size-1-i;
            if(leftList.get(i-1)<A.get(i))
                leftList.add(A.get(i));
            else
                leftList.add(leftList.get(i-1));

            if(rightList.get(i-1)<A.get(rightIndex))
                rightList.add(A.get(rightIndex));
            else
                rightList.add(rightList.get(i-1));

        }

        System.out.println(leftList);
        System.out.println(rightList);
        int ans = 0;
        for(int i=0; i<size; i++){
            int rightIndex = size-1-i;
            int minBarrier = Math.min(leftList.get(i), rightList.get(rightIndex));
            System.out.println(A.get(i)+" - "+minBarrier);
            if(minBarrier > A.get(i))
                ans+= (minBarrier-A.get(i));
        }

        return ans;

    }

    //Smaller than current numbers
    public static int[] countSmallerElements(int[] nums){
        //Another approach using hashmap
        /*int[] nums2 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums2);
        int[] ans = new int[nums.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(nums2[0],0);
        for(int i=1; i<nums2.length; i++){
            if(nums2[i]>nums2[i-1])
                map.put(nums2[i],i);
        }
        for(int i=0; i<nums.length; i++)
            ans[i] = map.get(nums[i]);

        return ans;*/

        int[] count = new int[101]; //Given that value of nums[i] is from 0 to 100
        for(int num:nums)
            count[num]++;
        int[] ans = new int[nums.length];
        for(int i=1; i<101; i++)
            count[i] +=count[i-1];
        for(int i=0; i<nums.length;i++)
            ans[i] = nums[i]==0?0:count[nums[i]-1];
        return ans;
    }

    //Check if sum exists in sub array
    //Using two pointers
    public static boolean checkSubarraySum(int[] nums, int k) {

        int start=0, end=1;
        for(int i=0; i<nums.length; i++)
            nums[i] = nums[i]%k;

        int sum =nums[start]+nums[end];
        while(end<nums.length){
            System.out.println(sum);
            if(sum==k)
                return true;
            else if(sum<k){
                end++;
                if(end==nums.length)
                    break;
                sum+=nums[end];
            }
            else {
                sum-=nums[start];
                start++;
                if(start==end){
                    end++;
                    if(end==nums.length)
                        break;
                    sum+=nums[end];
                }
            }
        }

        return false;
    }

    //Find max sum for consecutive non negative elements
    public static ArrayList<Integer> maxset(List<Integer> A) {
        long tempMax=-1;
        int tempStart=-1;
        int start=-1;
        int end=-1;
        long max=0;
        for(int i=0; i<A.size(); i++){
            long val=A.get(i);
            if(val>-1){
                if(tempStart==-1)
                    tempStart=i;
                if(tempMax==-1)
                    tempMax=val;
                else
                    tempMax+=val;
            }
            else if(val<0){
                int tempEnd = i-1;
                if(tempStart>-1){
                    if(tempMax>max){
                        max=tempMax;
                        start=tempStart;
                        end=tempEnd;
                    }
                    else if(max==tempMax){
                        if(end-start < tempEnd-tempStart){
                            start=tempStart;
                            end=tempEnd;
                        }
                    }
                    tempStart=-1;
                    tempMax=0;
                }
            }
        }

        if(tempMax>max){
            start=tempStart;
            end=A.size()-1;
        }

        ArrayList<Integer> ans = new ArrayList<>();
        if(start>-1)
            for(int i=start; i<=end;i++){
                ans.add(A.get(i));
            }
        return ans;
    }

    public static boolean checkValue(HashMap<Integer, Integer> map,int i, int j, int val){

        if(map.containsKey(val)){
            int reqFreq = 1;
            if(i==val)
                reqFreq++;
            if(j==val)
                reqFreq++;
            int freq = map.get(val);
            if(freq<=reqFreq)
                return true;
        }

        return false;
    }
    public static List<List<Integer>> threeSum(int[] nums) {

        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i])+1);
            else
                map.put(nums[i], 1);
        }

        Set<List<Integer>> ans = new HashSet<>();
        for(int i=0;i<nums.length; i++){
            for(int j=i+1;j<nums.length; j++){
                int sum = nums[i]+nums[j];
                int val = 0-sum;
                System.out.println(sum+" | "+val);
                if(checkValue(map,nums[i],nums[j],val)){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(val);
                    ans.add(temp);
                }
            }
        }
        return new ArrayList<>(ans);
    }


    public static int countSwaps(List<Integer> A, int B){
        int elements = 0;
        for(int i=0; i<A.size(); i++){
            if(A.get(i)<=B)
                elements++;
        }
        int desiredElements = 0;
        int j=elements-1;
        for(int i=0;i<=j;i++){
            if(A.get(i)<=B)
                desiredElements++;
        }
        int swaps = elements-desiredElements;
        for(int i=j+1;i<A.size();i++){
            if(A.get(i-elements)<=B){
                desiredElements--;
            }
            if(A.get(i)<=B)
                desiredElements++;

            if(elements-desiredElements<swaps)
                swaps = elements-desiredElements;
        }

        return swaps;
    }

    public static void main(String[] args) {

        //Rain water trapping
        /*Integer[] arr = new Integer[]{4,2,0,3,2,5};
        List<Integer> a = Arrays.asList(arr);
        System.out.println(RainWaterTrap(a));*/

        //Count smaller elements than current
        /*int[] ans = countSmallerElements(new int[]{8,0,0,1,2,2,3});
        for(int a:ans)
            System.out.print(a+" ");*/

        //Check if sum exits in subarray
        /*System.out.println(checkSubarraySum(new int[]{23,2,4,6,6}, 7));*/

        //Find max sum for consecutive non negative elements
        /*System.out.println(maxset(Arrays.asList( 24115, -75629, -46517, 30105, 19451, -82188, 99505, 6752, -36716, 54438, -51501, 83871, 11137, -53177, 22294, -21609, -59745, 53635, -98142, 27968, -260, 41594, 16395, 19113, 71006, -97942, 42082, -30767, 85695, -73671 )));*/

        //Check if sum of 3 distinct elements sum up to 0
        /*System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));*/

        //Count minimum swaps
        System.out.println(countSwaps(Arrays.asList(52, 7, 93, 47, 68, 26, 51, 44, 5, 41, 88, 19, 78, 38, 17, 13, 24, 74, 92, 5, 84, 27, 48, 49, 37, 59, 3, 56, 79, 26, 55, 60, 16, 83, 63, 40, 55, 9, 96, 29, 7, 22, 27, 74, 78, 38, 11, 65, 29, 52, 36, 21, 94, 46, 52, 47, 87, 33, 87, 70),19));
    }
}
