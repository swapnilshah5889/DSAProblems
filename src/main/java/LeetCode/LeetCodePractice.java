package LeetCode;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

class RecentCounter {
    Queue<Integer> queue;
    int millis;
    public RecentCounter() {
        queue = new LinkedList<>();
        millis = 0;
    }

    public int ping(int t) {
        millis = t-3000;
        queue.add(t);
        System.out.println(queue);
        System.out.println(millis);
        while(!queue.isEmpty() && queue.peek()<millis) {
            System.out.println("Peek:"+queue.peek());
            queue.remove();
        }
        return queue.size();
    }
}
public class LeetCodePractice {

    public static int findFirstOccurence(String haystack, String needle) {
        int end = 0;
        int len = needle.length();
        for(int i=0; i<haystack.length(); i++) {
            if(haystack.charAt(i) == needle.charAt(end) ) {
                end++;
                if(end == len) {
                    System.out.println("In : "+i);
                    return (i - end + 1);
                }
            }
            else {
                int iend = i+end;
                i-=end;
                while(i<iend && haystack.charAt(i) != needle.charAt(0)) {
                    i++;
                }
                end = 0;
            }
        }

        if(end == len)
            return len-end;
        else
            return -1;
    }

    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == ']') {
                Character c1 = stack.pop();
                StringBuilder st = new StringBuilder();
                while(c1 != '['){
                    st.insert(0, c1);
                    c1 = stack.pop();
                }
                StringBuilder numberSt = new StringBuilder();
                c1 = stack.peek();
                while (((int)c1 >= 48 && (int)c1 <= 57)) {
                    c1 = stack.pop();
                    numberSt.insert(0,c1);
                    if(!stack.isEmpty())
                        c1 = stack.peek();
                    else
                        break;
                }
                if(numberSt.length()>0) {
                    int count = Integer.parseInt(numberSt.toString());
                    for(int k=0; k<count; k++) {
                        for(int j=0; j<st.length(); j++) {
                            stack.push(st.charAt(j));
                        }
                    }
                }
            }
            else {
                stack.push(c);
            }
        }

        StringBuilder ans = new StringBuilder();
        while(!stack.isEmpty()) {
            ans.insert(0,stack.pop());
        }
        return ans.toString();
    }

    public static int distinctElements(Integer[] arr, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i],0)+1);
        }

        Stream<Map.Entry<Integer,Integer>> sorted = map.entrySet().stream().sorted((o1, o2) -> {
            return o2.getValue().compareTo(o1.getValue());
        });

        HashSet<Integer> distinctElements = new HashSet<>();
        sorted.forEach(entry -> {
            int val = entry.getKey();
            int freq = entry.getValue();
            if(!distinctElements.contains(val) && freq>0) {
                distinctElements.add(val);
                freq--;
            }
            if(!distinctElements.contains(val+k) && freq>0) {
                distinctElements.add(val+k);
                freq--;
            }
            if(!distinctElements.contains(val-k) && freq>0) {
                distinctElements.add(val-k);
            }
        });

        return distinctElements.size();
    }


    public static int checkPalindrome(String s, int l, int r, int count) {
        if(l>r || l==r) {
            System.out.println(count);
            return count;
        }

        char lChar = s.charAt(l);
        char rChar = s.charAt(r);
        if(lChar == rChar) {
            return checkPalindrome(s, l+1, r-1, count);
        }
        else {
            return Math.min(
                    checkPalindrome(s, l+1, r, count+1),
                    checkPalindrome(s, l, r-1, count+1)
            );
        }

    }

    public static int checkPalindrome(String s, int l, int r, int count, Integer[][] dp) {
        if(l>r || l==r) {
            dp[l][r] = count;
            System.out.println(count);
        }

        if(dp[l][r] == null) {
            char lChar = s.charAt(l);
            char rChar = s.charAt(r);
            int minCount;
            if(lChar == rChar) {
                minCount = checkPalindrome(s, l+1, r-1, count, dp);
            }
            else {
                minCount = Math.min(
                        checkPalindrome(s, l+1, r, count+1, dp),
                        checkPalindrome(s, l, r-1, count+1, dp)
                );
            }
            dp[l][r] = minCount;
        }

        return dp[l][r];

    }

    public static int minimumInsertions(String s) {
        Integer[][] dp = new Integer[s.length()][s.length()];
        return checkPalindrome(s, 0, s.length()-1, 0);
//        return checkPalindrome(s, 0, s.length()-1, 0, dp);
    }


    public static String getNearestCity(String[] cities, int[] x, int[] y, String city) {
        int index = -1;
        for (int i = 0; i < cities.length; i++) {
            if (city.equalsIgnoreCase(cities[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return "NONE";
        }
        int currX = x[index];
        int currY = y[index];
        Integer minDist = null;
        String closestCity = "NONE";
        for (int i = 0; i < cities.length; i++) {
            if (i != index && (x[i] == currX || y[i] == currY)) {
                int dist = Math.abs(x[i] - currX) + Math.abs(y[i] - currY);
                if (minDist == null || dist < minDist) {
                    minDist = dist;
                    closestCity = cities[i];
                } else if (dist == minDist && cities[i].compareTo(closestCity) < 0) {
                    closestCity = cities[i];
                }
            }
        }
        return closestCity;
    }

    public static List<String > solve(String[] cities, int[] x, int[] y, String[] queries) {
        List<String> ans = new ArrayList<>();
        for(String q : queries) ans.add(getNearestCity(cities, x, y, q));
        return ans;
    }

    public static String getNearestCity(Map<Integer, List<String>> xMap, Map<Integer, List<String>> yMap, Map<String, int[]> cityMap, String city) {
        if (!cityMap.containsKey(city)) {
            return "NONE";
        }

        int[] coords = cityMap.get(city);
        int currX = coords[0];
        int currY = coords[1];
        Integer minDist = null;
        String closestCity = "NONE";

        List<String> sameX = xMap.get(currX);
        List<String> sameY = yMap.get(currY);

        if (sameX != null) {
            for (String c : sameX) {
                if (!c.equals(city)) {
                    int[] cCoords = cityMap.get(c);
                    int dist = Math.abs(cCoords[0] - currX) + Math.abs(cCoords[1] - currY);
                    if (minDist == null || dist < minDist || (dist == minDist && c.compareTo(closestCity) < 0)) {
                        minDist = dist;
                        closestCity = c;
                    }
                }
            }
        }

        if (sameY != null) {
            for (String c : sameY) {
                if (!c.equals(city)) {
                    int[] cCoords = cityMap.get(c);
                    int dist = Math.abs(cCoords[0] - currX) + Math.abs(cCoords[1] - currY);
                    if (minDist == null || dist < minDist || (dist == minDist && c.compareTo(closestCity) < 0)) {
                        minDist = dist;
                        closestCity = c;
                    }
                }
            }
        }

        return closestCity;
    }

    public static List<String> solve2(String[] cities, int[] x, int[] y, String[] queries) {
        Map<Integer, List<String>> xMap = new HashMap<>();
        Map<Integer, List<String>> yMap = new HashMap<>();
        Map<String, int[]> cityMap = new HashMap<>();

        for (int i = 0; i < cities.length; i++) {
            cityMap.put(cities[i], new int[]{x[i], y[i]});

            xMap.putIfAbsent(x[i], new ArrayList<>());
            xMap.get(x[i]).add(cities[i]);

            yMap.putIfAbsent(y[i], new ArrayList<>());
            yMap.get(y[i]).add(cities[i]);
        }

        List<String> ans = new ArrayList<>();
        for (String q : queries) {
            ans.add(getNearestCity(xMap, yMap, cityMap, q));
        }

        return ans;
    }



    public static void main(String[] args) {

        // Find first occurrence of a substring
        /*System.out.println(findFirstOccurence("mississippi", "issip"));*/

        // Decode String
        /*System.out.println(decodeString("3[a]2[bc]"));*/

        // Queue
        /*RecentCounter rc = new RecentCounter();
        System.out.println(rc.ping(2196));
        System.out.println(rc.ping(3938));
        System.out.println(rc.ping(4723));
        System.out.println(rc.ping(4775));
        System.out.println(rc.ping(5952));*/

        // Distinct Elements
        /*System.out.println(distinctElements(new Integer[]{5,5,5,5,1,7}, 2));
        System.out.println(distinctElements(new Integer[]{1,5,9}, 2));*/

        // Minimum Insertions
//        System.out.println(minimumInsertions("mynameisswapnilshah"));


        System.out.println(solve(new String[]{"c1", "c2", "c3"}, new int[]{3,2,1}, new int[]{3,2,3}, new String[] {"c1", "c2", "c3"}));
        System.out.println(solve2(new String[]{"c1", "c2", "c3"}, new int[]{3,2,1}, new int[]{3,2,3}, new String[] {"c1", "c2", "c3"}));

        System.out.println(solve(new String[]{"fastcity", "bigbanana", "xyz"}, new int[]{23,23,23}, new int[]{1,10,20}, new String[] {"fastcity", "bigbanana", "xyz"}));
        System.out.println(solve2(new String[]{"fastcity", "bigbanana", "xyz"}, new int[]{23,23,23}, new int[]{1,10,20}, new String[] {"fastcity", "bigbanana", "xyz"}));

        System.out.println(solve(new String[]{"london", "warsaw", "hackerland"}, new int[]{1,10,20}, new int[]{1,10,10}, new String[] {"london", "warsaw", "hackerland"}));
        System.out.println(solve2(new String[]{"london", "warsaw", "hackerland"}, new int[]{1,10,20}, new int[]{1,10,10}, new String[] {"london", "warsaw", "hackerland"}));


        System.out.println(solve(
                new String[]{"green", "apple", "blue", "red", "pink"},
                new int[]{100,300,300,300,500},
                new int[]{100,200,300,400,500},
                new String[] {"green", "apple", "blue", "red", "pink"}));

        System.out.println(solve2(
                new String[]{"green", "apple", "blue", "red", "pink"},
                new int[]{100,300,300,300,500},
                new int[]{100,200,300,400,500},
                new String[] {"green", "apple", "blue", "red", "pink"}));

    }

}
