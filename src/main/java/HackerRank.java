import java.util.*;

public class HackerRank {

    public static int minimumDistances(List<Integer> a) {
        // Write your code here

        int minDistance = Integer.MAX_VALUE;
        int currentDistance = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            if(map.containsKey(a.get(i))){
                currentDistance = Math.abs(i - map.get(a.get(i)));
                if(currentDistance < minDistance){
                    minDistance = currentDistance;
                }
            } else {
                map.put(a.get(i), i);
            }
        }
        if(minDistance == Integer.MAX_VALUE) return -1;
        else return minDistance;

    }

    public static int flatlandSpaceStations(int n, int[] c) {
        if(c.length == n)
            return 0;
        int ans = 0;
        Arrays.sort(c);
        for(int i=0; i<c.length; i++)
            System.out.print(c[i]+" ");
        System.out.println("");
        for(int i=1; i<c.length; i++){
            if((c[i] - c[i-1])>1){
                int temp = (int) Math.ceil( ((double)c[i] - c[i-1]-1)/2);
                System.out.println(c[i]+" | "+c[i-1]+" | "+temp);
                if(temp>ans)
                    ans = temp;
            }
        }
        System.out.println(c[0]);
        if((c[0]-1)>ans)
            ans = c[0]-1;
        if((n-c[c.length-1]-1)>ans)
            ans = (n-c[c.length-1]-1);
        return ans;
    }

    public static int stringConstruction(String s) {
        // Write your code here
        int counter = 1;
        HashSet<Character> charSet = new HashSet<>();
        charSet.add(s.charAt(0));
        for(int i=1; i<s.length(); i++){
            char c = s.charAt(i);
            if(!charSet.contains(c)) {
                counter++;
                charSet.add(c);
            }
        }

        return counter;
    }



    public static void main(String args[]) {

    }

}
