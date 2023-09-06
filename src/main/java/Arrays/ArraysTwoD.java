package Arrays;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ArraysTwoD {


    public static void MaxOnes(ArrayList<ArrayList<Integer>> A){

        int n = A.get(0).size();
        int index = n;
        int row = -1;
        for(int i=0; i<A.size(); i++){

            for(int j=index-1; j>=0; j--){
                if(A.get(i).get(j)==1){
                    index=j;
                    row=i;
                }
            }

        }

        System.out.println("Row with max Ones: "+(row+1));

        System.out.println("Max number of Ones: "+(n-index));
    }

    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public static ArrayList<Interval> MergeIntervals(ArrayList<Interval> intervals, Interval newInterval){

        ArrayList<Interval> ans = new ArrayList<>();
        if(newInterval.end<newInterval.start){
            int temp = newInterval.start;
            newInterval.start = newInterval.end;
            newInterval.end = temp;
        }
        for(int i=0; i<intervals.size(); i++){

            //Case 1 : If new interval does not overlaps
            if(intervals.get(i).end < newInterval.start){
                System.out.println("CASE 1");
                ans.add(intervals.get(i));
            }
            //Case 2: If new interval overlaps
            else if(intervals.get(i).start< newInterval.end){
                System.out.println("CASE 2");
                newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
                newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            }
            //Case 3: If end of interval does not overlaps
            else if(intervals.get(i).start > newInterval.end){
                System.out.println("CASE 3");
                ans.add(newInterval);
                while(i<intervals.size()){
                    ans.add(intervals.get(i));
                    i++;
                }
                return ans;
            }

        }

        ans.add(newInterval);
        return ans;

    }

    //Merge intervals : intervals are not sorted
    public static int[][] MergeIntervalsII(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> Double.compare(a[0], b[0]));
        int index=0;
        for(int i=1;i<intervals.length; i++){
            int[] currInterval = intervals[i];
            int[] prevInterval = intervals[index];

            //Case1: Intervals overlap : Merge
            if(currInterval[0]<=prevInterval[1]){
                //If end of current is greater than previous
                if(prevInterval[1]<currInterval[1]){
                    prevInterval[1]=currInterval[1];
                }
            }
            //Case2: Intervals do not overlap
            else{
                index++;
                if(index!=i){
                    intervals[index][0] = currInterval[0];
                    intervals[index][1] = currInterval[1];
                }
            }
        }
        int[][] ans = new int[index+1][2];
        for(int i=0; i<=index;i++){
            ans[i][0] = intervals[i][0];
            ans[i][1] = intervals[i][1];
        }
        return ans;
    }

    public static int maxWaterInContainer(int[] A) {
        int ans = 0;

        int l=0, r=A.length-1;
        while(l<r){
            int width = r-l;
            int height = Math.min(A[l], A[r]);
            int area = width*height;
            if(area>ans)
                ans = area;

            if(A[l]<A[r])
                l++;
            else if(A[l]>A[r])
                r--;
            else{
                l++;
                r--;
            }
        }

        return ans;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result=new ArrayList();
        if(numRows == 0){
            return result;
        }

        for(int i=1; i<=numRows; i++){
            List<Integer> row=new ArrayList();
            for(int j=0; j<i; j++){
                if(j==0 || j==i-1)
                {
                    row.add(1);
                }
                else
                {
                    row.add(result.get(i-2).get(j)+result.get(i-2).get(j-1));
                }
            }
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {

        //Merger Interval I
        /*ArrayList<Interval> intervals = new ArrayList<>();
        Interval interval = new Interval(10,8);
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(3,6));
        ArrayList<Interval> ans = MergeIntervals(intervals, interval);

        for(int i=0; i<ans.size(); i++){
            System.out.println(ans.get(i).start+" | "+ans.get(i).end);
        }*/

        //Merger Intervals II
        /*int[][] ans = MergeIntervalsII(new int[][]{{4,6},{2,5},{1,3},{9,11}});
        System.out.println();
        for(int[] temp:ans){
            System.out.print("["+temp[0]+", "+temp[1]+"], ");
        }*/

        //Max water in a container
        /*System.out.println(maxWaterInContainer(new int[]{1,8,6,2,5,4,8,3,7}));*/

        System.out.println(generate(4   ));

    }

}
