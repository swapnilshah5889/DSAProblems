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

    public static ArrayList<Integer> oddEvenSpecialIndex(ArrayList<Integer> A) {
        ArrayList<Integer> even = new ArrayList<>();
        ArrayList<Integer> odd = new ArrayList<>();

        even.add(A.get(0));
        odd.add(0);
        for(int i=1; i<A.size(); i++) {
            //Even index
            if(i%2==0) {
                even.add(even.get(i-1)+A.get(i));
                odd.add(odd.get(i-1));
            }
            //Odd index
            else {
                odd.add(odd.get(i-1)+A.get(i));
                even.add(even.get(i-1));
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        int lastIndex = A.size()-1;
        //Edge cases
        //Remove value at first index
        if((even.get(lastIndex) - even.get(0)) == odd.get(lastIndex))
            ans.add(0);

        //Remove value at last index
        if(lastIndex%2==0) {
            if(even.get(lastIndex-1) == odd.get(lastIndex))
                ans.add(lastIndex);
        }
        else {
            if(even.get(lastIndex) == odd.get(lastIndex-1))
                ans.add(lastIndex);
        }

        for(int i=1; i<A.size()-1; i++) {
            int evenCnt = even.get(i-1) + odd.get(lastIndex) - odd.get(i);
            int oddCnt = odd.get(i-1) + even.get(lastIndex) - even.get(i);
            if(evenCnt == oddCnt)
                ans.add(i);
        }

        return ans;
    }

    public static int equilibriumIndex(ArrayList<Integer> A) {
        ArrayList<Integer> presum = new ArrayList<>();
        ArrayList<Integer> presumRev = new ArrayList<>();
        System.out.println(A.size());
        presum.add(A.get(0));
        presumRev.add(A.get(A.size()-1));
        int lastindex = A.size()-1;
        for(int i=1; i<A.size(); i++) {
            presum.add(presum.get(i-1) + A.get(i));
            presumRev.add(A.get(lastindex-i) + presumRev.get(i-1));
        }

        for(int i=0, j = A.size()-1; i<presum.size(); i++,j--) {

            //using == for integer comparision gives wrong answer,
            //Hence used .equals() method
            if(presum.get(i).equals(presumRev.get(j)))
                return i;
        }

        return -1;
    }

    //Return array with product for every index where the product
    //is of all elements in the array except for that index
    public static ArrayList<Integer> productArray(ArrayList<Integer> A) {
        ArrayList<Integer> prod = new ArrayList<>();
        prod.add(A.get(0));
        int lastIndex = A.size()-1;
        ArrayList<Integer> prodRev = new ArrayList<>();
        prodRev.add(A.get(lastIndex));
        for(int i=1, j=lastIndex-1; i<A.size(); i++, j--) {
            prod.add(A.get(i) * prod.get(i-1));
            prodRev.add(0, A.get(j) * prodRev.get(0));
        }

        System.out.println(prod);
        System.out.println(prodRev);
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(prodRev.get(1));

        for(int i=1; i<lastIndex; i++) {
            int val = prod.get(i-1) * prodRev.get(i+1);
            ans.add(val);
        }

        ans.add(prod.get(lastIndex-1));

        return ans;
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
        /*System.out.println(countSwaps(Arrays.asList(52, 7, 93, 47, 68, 26, 51, 44, 5, 41, 88, 19, 78, 38, 17, 13, 24, 74, 92, 5, 84, 27, 48, 49, 37, 59, 3, 56, 79, 26, 55, 60, 16, 83, 63, 40, 55, 9, 96, 29, 7, 22, 27, 74, 78, 38, 11, 65, 29, 52, 36, 21, 94, 46, 52, 47, 87, 33, 87, 70),19));*/

        //Get Equilibrium index of the given array
        /*System.out.println(equilibriumIndex(new ArrayList<>(Arrays.asList(-9567, 4282, -8150, -507, -4368, 7650, 7834, 5994, 6029, 5977, -8131, 8327, 3421, 9402, -4050, 7917, -5939, -942, -6335, -2009, 5579, 2814, -2182, 2974, 6038, -7243, -9261, 8571, -74, 5663, -1680, -1180, -7929, -5241, -4283, -7102, 8716, -2030, 9839, 4754, 7722, -4286, 3694, 109, -59, -7893, -8036, 8136, 704, -7362, 6050, 5782, 1408, -9171, -2858, 9037, -9765, -8160, -8906, 1405, 2724, -1525, -3142, -2331, -6559, -6712, 5717, 6692, -2836, 4884, -9282, -8093, -1108, -4037, -6764, 6636, 1113, 2372, 2314, -863, 5107, 1962, 332, 9255, -952, -3059, -763, -1010, -8148, -4187, -6949, -9266, -6649, -1086, -1823, 1172, 7468, 3576, -2651, -2810, 1327, -4869, -2638, -194, 1668, 4050, 4008, 4203, -8614, -4107, 3707, -4164, 759, -3964, 4149, -8391, -5467, 5070, 6646, 2723, 904, 5479, -3756, 4658, 4062, -9748, -7888, 3846, 5479, -1837, 9681, 7306, -6371, -8648, -9198, 8204, 1090, -1853, 405, -1200, -4693, 3426, 548, -442, 9966, 9007, -4362, 1204, -1166, -8452, -9174, 3597, -2045, -2079, -3192, 4880, -7953, -2939, 7343, 1277, -2347, -2526, 7729, -7062, -3200, 9711, -8148, -5192, 2015, -9040, 4451, -2866, -9280, -1428, 1601, -3913, -7821, 2407, -8553, 3208, -8036, 5504, 230, 5581, 2992, 4554, -1305, 5040, 1350, 6145, -8674, 1838, -8340, 5663, 5000, 6941, -3385, -4323, 5479, 5561, 2765, 7494, 2773, 3704, -3010, -636, -3644, -4567, 2449, 3915, -353, -8880, -370, 2873, 9045, 8600, 6881, 9702, 4767, -587, 4684, 2002, 3471, -6580, -5013, -8027, -3364, -3163, 2114, -6655, 4191, -5744, -4798, 7604, -2697, -2367, -3678, -4537, -7843, 748, 910, 2253, 2549, -4975, 7251, -2691, 9460, -19, 338, -1454, 4184, -4846, 9527, -9705, 6109, -5078, 3366, 5740, -1281, -5422, 6854, 4534, 2823, 9693, -8986, -6408, 4579, -4497, -6209, -8900, 5450, -4754, -3622, -3314, -1627, 265, 5987, 8514, -9536, -324, 6247, 1841, 2614, 9397, 3512, -7554, 1691, 4304, -1112, 3789, 8212, -4595, -729, 5671, -3167, -3448, 6861, -2790, 5387, 2572, -4657, -6065, 565, -2281, -8854, -8131, 7003, 5690, 2997, 4270, 1647, 405, -9540, -3289, -8486, -9280, -4131, 1922, 2244, 6311, -3125, 3156, -2776, -7586, 6614, 4065, -3392, 336, -5931, -4488, -967, -7013, -1896, 4905, 3854, -352, -2333, 3925, -4450, -2947, 3731, 3545, -7588, 3047, 7058, -652, -3031, 9708, -5050, 9896, -1990, 7437, 2033, 1836, 1020, 1184, -1879, 6986, -1442, 3706, 5075, -3121, -9057, -2218, -3482, 4503, 3820, -6814, -5543, 2953, 5419, -4234, 5083, -7989, -9044, 4759, 5001, -4816, -1455, -8982, 6230, 3789, 2067, 8913, -7197, -5700, -9605, -8917, 1602, 9068, 6983, -9851, 6490, -3681, -4583, 7416, -9859, -4794, -7003, -5078, -6571, 2147, -3032, -3745, 4378, 552, -6223, 1308, 3407, -2074, -8225, -9206, -811, 3335, -8321, -2336, -9302, -8629, 1250, 876, -2147, -9869, -684, -4668, 5552, 1592, -1862, -2060, -6691, 7282, -231, -6022, -8175, 3090, 23, -1629, 4508, -777, 6424, 9660, 5148, 487, -8604, -9050, -8296, -5051, 1232, 2182, -5361, -2854, 1218, -7355, -6655, -1729, -5164, -1944, -306, -4147, 8714, -6277, 1108, -9160, 7330, -6345, 4768, 2387, 1281, 2193, 9069, 5224, -1424, -5967, -5322, 8263, 7794, -7210, 4531, 1829, 4972, -3197, 2109, 1927, -7533, -4550, 6185, 4942, 6430, -2290, 9205, 2406, 4109, 4017, 8010, -4812, 287, 7817, -9775, 8070, 3510, 5722, -7001, -9778, 1177, -7398, 7125, -9041, -8945, 8841, 4140, 8770, 1732, 4705, -9492, 2868, -9211, -3526, -4864, -9068, -4073, -5433, 6376, 7314, 7935, 8647, 2811, -2688, 2510, -1002, -3529, -8859, -3419, 9237, -4087, -9237, 6027, -5347, -5957, -3261, -9781, -905, 405, -8287, 2930, -1011, -9123, -7448, -482, 3555, -5767, 9878, -7135, 8479, -3306, -6033, 9624, 5480, -3615, 1633, 8225, -1602, 4878, 3622, -7586, -2040, 7472, 3912, -1787, 4356, 4270, -3100, -3538, -4678, -588, 3346, 6, -8615, -7688, -5733, -6071, -9479, 9312, 7716, -9107, -9610, -1293, 914, -5513, 7474, -2969, -8765, -8385, -5457, -1915, -5540, -3095, -8457, 6298, -8301, 8233, 1029, -4112, 2573, 2999, 9666, -809, 2257, 2203, -3387, 570, 6012, 5346, -3049, -9127, -1107, -8724, 6999, -1803, 6059, -7293, 3701, 9062, 4683, -936, 7469, -4921, 4463, -7966, 6283, 4111, -7324, 2921, -6848, -7417, -4208, 4091, 4538, -5774, 4881, -7206, 5092, -5863, -8729, -4802, 9619, -2294, 8733, 4161, 7050, -2423, -9985, -2006, 1193, -9158, -3103, 3704, -9680, 5482, 3379, 7462, -7796, 7093, -7317, -7236, -1509, -4516, -7529, -5514, 7038, 3530, 5514, 6408, 2528, 3836, 2356, -6926, -5410, -5950, -8132, 2058, -6010, -978, -4080, -5457, 4366, -6540, 9812, -5810, 6537, -9194, -5296, 6306, -5225, -3031, -7157, 9866, 3675, 9126, -7522, 5244, -295, -5015, -2796, -6973, -2139, 7290, -3673, -2610, -7329, -810, 8879, 3250, -6699, 500, -960, 5795, -6513, 6157, -5470, 8876, -3657, 2257, 2402, -7110, 5820, -7334, 2321, -3148, 6831, -7754, 2049, -6758, 5024, 6154, -6541, -6987, -7337, 7168, 546, -8561, 4313, 7753, 2398, -7691, -3355, 5309, 6411, -5404))));*/

        //Find indexes such that removing those indexes makes the even and odd sum equal
        /*System.out.println(oddEvenSpecialIndex(new ArrayList<>(Arrays.asList(2, 1, 6, 4))));
        System.out.println(oddEvenSpecialIndex(new ArrayList<>(Arrays.asList(1, 1, 1))));*/

        //Find product for every index in array where it has product of all elements except for that index
        System.out.println(productArray(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5))));

    }
}
