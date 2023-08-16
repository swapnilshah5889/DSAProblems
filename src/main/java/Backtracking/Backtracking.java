package Backtracking;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.*;

class SampleMaze {
    public ArrayList<ArrayList<Integer>> getSampleMaze(int type) {
        ArrayList<ArrayList<Integer>> maze = new ArrayList<>();
        switch (type) {
            case 0:
                maze.add(new ArrayList<>(Arrays.asList(1, 0, 0, 0)));
                maze.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
                maze.add(new ArrayList<>(Arrays.asList(0, 0, 2, -1)));
                break;
        }

        return maze;
    }
}

class Problems {

    int uniquePaths;
    private void mazeUniquePaths(ArrayList<ArrayList<Integer>> A, int startR, int startC,
                                 boolean[][] visited, int totalZeros, int count) {

        // Invalid point
        if(startR<0 || startR==A.size() || startC<0 || startC==A.get(0).size()) {
            return;
        }
        // If already visited
        if(visited[startR][startC]) {
            return;
        }
        // Avoid montser
        if(A.get(startR).get(startC) == -1) {
            return;
        }
        // Target reached
        if(A.get(startR).get(startC) == 2) {
            if(count == totalZeros) {
                uniquePaths++;
            }
        }

        // Check neighbors
        visited[startR][startC] = true;
        mazeUniquePaths(A, startR-1, startC, visited, totalZeros, count+1);
        mazeUniquePaths(A, startR, startC-1, visited, totalZeros, count+1);
        mazeUniquePaths(A, startR, startC+1, visited, totalZeros, count+1);
        mazeUniquePaths(A, startR+1, startC, visited, totalZeros, count+1);
        visited[startR][startC] = false;

    }
    public int mazeUniquePaths(ArrayList<ArrayList<Integer>> A) {
        boolean visited[][] = new boolean[A.size()][A.get(0).size()];
        uniquePaths = 0;
        int startR = 0, startC=0;
        int totalZeros = 0;
        for(int i=0; i<A.size(); i++) {
            for(int j=0; j<A.get(0).size(); j++) {
                visited[i][j] = false;
                if(A.get(i).get(j) == 1) {
                    startR = i;
                    startC = j;
                }
                else if(A.get(i).get(j) == 0) {
                    totalZeros++;
                }
            }
        }

        mazeUniquePaths(A, startR, startC, visited, totalZeros, -1);
        return uniquePaths;
    }

    public ArrayList<ArrayList<Integer>> getPermutations(ArrayList<Integer> A) {
        if(A.size() == 0) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i=0; i<A.size(); i++) {
            int val = A.remove(i);
            ArrayList<ArrayList<Integer>> permutations = getPermutations(A);
            if(permutations.size()>0) {
                for (ArrayList<Integer> subp : permutations) {
                    subp.add(0, val);
                    ans.add(subp);
                }
            }
            else {
                ans.add(new ArrayList<>(Arrays.asList(val)));
            }
            A.add(i, val);
        }

        return ans;
    }

    ArrayList<ArrayList<Integer>> allPermutations;
    public void getPermutationsOptimized(ArrayList<Integer> A, int index) {
        if(index == A.size()-1) {
            ArrayList<Integer> clone = new ArrayList<>();
            for(Integer p : A) clone.add(p);
            allPermutations.add(clone);
        }
        for(int i=index; i<A.size(); i++) {
            int temp = A.get(i);
            A.set(i, A.get(index));
            A.set(index, temp);
            getPermutationsOptimized(A, index+1);
            temp = A.get(i);
            A.set(i, A.get(index));
            A.set(index, temp);
        }
    }
    public ArrayList<ArrayList<Integer>> permutations(ArrayList<Integer> A) {
        // Approach 1: By inserting and removing elements at indexes
        /*return getPermutations(A);*/

        // Approach 2: Optimized approach using swapping instead of remove and insert at indexes
        allPermutations = new ArrayList<>();
        getPermutationsOptimized(A, 0);
        return allPermutations;
    }

    public ArrayList<ArrayList<Integer>> getUniquePermutations(ArrayList<Integer> A, HashSet<String> uniqueSet, int size) {
        if(A.size() == 0) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int currSize = A.size();
        for(int i=0; i<A.size(); i++) {
            int val = A.remove(i);
            ArrayList<ArrayList<Integer>> permutations = getUniquePermutations(A, uniqueSet, size);
            if(permutations.size()>0) {
                for (ArrayList<Integer> subp : permutations) {
                    subp.add(0, val);
                    StringBuffer sb = new StringBuffer();
                    for(Integer t : subp) {
                        sb.append(t+"");
                    }
                    if(size == currSize) {
                        if (!uniqueSet.contains(sb.toString())) {
                            ans.add(subp);
                            uniqueSet.add(sb.toString());
                        }
                    }
                    else {
                        ans.add(subp);
                    }
                }
            }
            else {
                ans.add(new ArrayList<>(Arrays.asList(val)));
            }
            A.add(i, val);
        }

        return ans;
    }

    public ArrayList<ArrayList<Integer>> uniquePermutations(ArrayList<Integer> A) {
        HashSet<String> uniqueSet = new HashSet<>();
        return getUniquePermutations(A, uniqueSet, A.size());
    }

    public void getPhoneLetterCombinations(String A, int index, StringBuffer processed,
                                           HashMap<Character, String> digits, ArrayList<String> ans) {
        if(index == A.length()) {
            ans.add(processed.toString());
            return;
        }

        char c = A.charAt(index);
        String values = digits.get(c);
        for(int j=0; j<values.length(); j++) {
            processed.append(values.charAt(j));
            getPhoneLetterCombinations(A, index+1, processed, digits, ans);
            processed.deleteCharAt(processed.length()-1);
        }

    }

    public ArrayList<String> phoneLetterCombinations(String A) {
        HashMap<Character, String> digits = new HashMap<>();
        digits.put('0', "0");
        digits.put('1', "1");
        digits.put('2', "abc");
        digits.put('3', "def");
        digits.put('4', "ghi");
        digits.put('5', "jkl");
        digits.put('6', "mno");
        digits.put('7', "pqrs");
        digits.put('8', "tuv");
        digits.put('9', "wxyz");
        ArrayList<String> ans = new ArrayList<>();
        getPhoneLetterCombinations(A, 0, new StringBuffer(), digits, ans);
        return ans;
    }

    public boolean checkSquarefulPermutation(ArrayList<Integer> a, int start, int end) {
        if(a.size() < 2)
            return false;
        if(start<0)
            start = 0;
        for(int i=start+1; i<=end; i++) {
            int prev = a.get(i-1);
            int curr = a.get(i);
            double sqrt = Math.pow(prev+curr, 0.5);
            double sqrt1 = ((int)sqrt);
            if(sqrt1 != sqrt) {
                return false;
            }
        }
        return true;
    }

    public int getSquarefulPermutations(ArrayList<Integer> A,
                          HashSet<String> uniqueSet, int index) {
        if(index == A.size()-1) {
            StringBuffer sb = new StringBuffer();
            for(Integer i : A) sb.append(i);

            if(!uniqueSet.contains(sb.toString())) {
                uniqueSet.add(sb.toString());
                if(checkSquarefulPermutation(A, index-2, index)) {
                    System.out.println(A);
                    System.out.println(checkSquarefulPermutation(A, 0 , index));
                    return 1;
                }
                else {
                    return 0;
                }
            }

        }

        if(index >= 2) {
            if(!checkSquarefulPermutation(A, index-2, index-1)) {
                return 0;
            }
        }

        int total=0;
        for(int i=index; i<A.size(); i++) {
            int temp = A.get(i);
            A.set(i, A.get(index));
            A.set(index, temp);
            total += getSquarefulPermutations(A, uniqueSet, index+1);
            temp = A.get(i);
            A.set(i, A.get(index));
            A.set(index, temp);
        }

        return total;
    }

    public int squarefulArrays(ArrayList<Integer> a) {
        HashSet<String> uniqeSet = new HashSet<>();
        return getSquarefulPermutations(a, uniqeSet, 0);
    }

    public void removeInvalidParenthesis(String A, int index, HashSet<String> uniqueSets, int rightOffset,
                                        int leftOffset, String processed, ArrayList<String> ans) {
        if(rightOffset>leftOffset) {
            return;
        }
        if(index == A.length()) {
            // Valid parenthesis
            if(rightOffset == leftOffset) {
                // Add to ans
                if(ans.size() == 0) {
                    ans.add(processed);
                    uniqueSets.add(processed);
                }
                // Verify max length
                else {
                    // If better solution
                    if(processed.length() > ans.get(0).length()) {
                        ans = new ArrayList<>();
                        uniqueSets = new HashSet<>();
                        ans.add(processed);
                        uniqueSets.add(processed);
                    }
                    // If same length - add to ans
                    else if(processed.length() == ans.get(0).length() && !uniqueSets.contains(processed)) {
                        ans.add(processed);
                        uniqueSets.add(processed);
                    }
                }
            }
            return;
        }

        char currChar = A.charAt(index);
        if(currChar == '(') {
            removeInvalidParenthesis(A, index+1, uniqueSets, rightOffset, leftOffset+1,
                    processed+(currChar+""), ans);
            removeInvalidParenthesis(A, index+1, uniqueSets, rightOffset, leftOffset,
                    processed, ans);
        }
        else if(currChar == ')') {
            removeInvalidParenthesis(A, index+1, uniqueSets, rightOffset+1, leftOffset,
                    processed+(""+currChar), ans);
            removeInvalidParenthesis(A, index+1, uniqueSets, rightOffset, leftOffset,
                    processed, ans);
        }
        else {
            removeInvalidParenthesis(A, index+1, uniqueSets, rightOffset, leftOffset,
                    processed+(""+currChar), ans);
        }
    }

    public ArrayList<String> removeInvalidParenthesis(String A) {
        ArrayList<String> ans = new ArrayList<>();
        HashSet<String> uniqueSets = new HashSet<>();
        removeInvalidParenthesis(A, 0, uniqueSets, 0, 0, "", ans);
        return ans;
    }

    public void getUniqueClone(ArrayList<Integer> a, HashSet<String> uniqueSets,
                                             ArrayList<ArrayList<Integer>> ans) {
        StringBuffer sb = new StringBuffer();
//        System.out.println(a);
        for(Integer i : a) {
            sb.append(i+"");
        }
        if(sb.length() == 0){
            sb.append("empty");
        }
        if(!uniqueSets.contains(sb.toString())) {
            uniqueSets.add(sb.toString());
            ArrayList<Integer> clone = new ArrayList<>(a);
            ans.add(clone);
        }

    }
    public void getSubsets(ArrayList<Integer> A, int index, ArrayList<Integer> processed,
                   ArrayList<ArrayList<Integer>> ans, HashSet<String> uniqueSets) {
        if(index == A.size()) {
            getUniqueClone(processed, uniqueSets, ans);
            return;
        }

        getUniqueClone(processed, uniqueSets, ans);
        for(int i=index; i<A.size(); i++) {
            processed.add(A.get(i));
            getSubsets(A, i+1, processed, ans, uniqueSets);
            processed.remove(processed.size()-1);
        }

    }

    public ArrayList<ArrayList<Integer>> subsetsII(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        HashSet<String> uniqueSets = new HashSet<>();
        Collections.sort(A);
        getSubsets(A, 0, new ArrayList<>(), ans, uniqueSets);
        return ans;
    }

    public void generateParenthesis(int left, int right, List<String> ans, StringBuffer processed) {
        // Valid parenthesis
        if(left == 0 && right == 0) {
            ans.add(processed.toString());
        }
        // Invalid parenthesis
        // because closing brackets cannot be more than
        // opening brackets at any point
        if(left>right) {
            return;
        }

        // If opening brackets available
        if(left>0) {
            generateParenthesis(left-1, right, ans, processed.append("("));
            processed.deleteCharAt(processed.length()-1);
        }
        // If closing brackets available
        if(right>0) {
            generateParenthesis(left, right-1, ans, processed.append(")"));
            processed.deleteCharAt(processed.length()-1);
        }

    }

    public List<String> generateParenthesis(int i) {
        List<String> ans = new ArrayList<>();
        generateParenthesis(i, i, ans, new StringBuffer());
        return ans;
    }

    public void generateUniqueSubsets(int[] nums, List<Integer> processed, int index,
                                      HashSet<String> uniqueSets, List<List<Integer>> ans) {
        if(nums.length == index-1) {
            processed.add(nums[index]);
            System.out.println(processed);
            return;
        }

        for(int i=index; i<nums.length; i++) {
            processed.add(nums[i]);
            StringBuffer sb = new StringBuffer();
            for (Integer p : processed) {
                sb.append(p);
            }
            if(!uniqueSets.contains(sb.toString())) {
                ArrayList<Integer> subset = new ArrayList<>(processed);
                ans.add(subset);
                uniqueSets.add(sb.toString());
            }
            generateUniqueSubsets(nums, processed, i+1, uniqueSets, ans);
            processed.remove(processed.size()-1);
        }

    }

    public List<List<Integer>> generateUniqueSubsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        HashSet<String> uniqueSets = new HashSet<>();
        generateUniqueSubsets(nums, new ArrayList<>(), 0, uniqueSets, ans);
        return ans;
    }

    public void combinationSum(int[] candidates, int index, int target, List<Integer> processed,
                               HashSet<String> uniqueSets, List<List<Integer>> ans, int sum) {

        int[] temp = new int[]{2,2,22,4};
        if(temp.length == processed.size()) {
            boolean found = true;
            for (int i=0; i<processed.size(); i++) {
                if(processed.get(i) != temp[i]) {
                    found=false;
                    break;
                }
            }
            if(found) {
                System.out.println(processed);
                System.out.println(sum);
            }
        }

        if(sum > target) {
            return;
        }

        if(sum == target) {
            StringBuffer sb = new StringBuffer();
            for(Integer p : processed) {
                if(p<10)
                    sb.append("0"+p);
                else
                    sb.append(""+p);
            }
            if(!uniqueSets.contains(sb.toString())) {
                uniqueSets.add(sb.toString());
                ans.add(processed);
            }
            return;
        }

        for(int i=index; i<candidates.length; i++) {
            ArrayList<Integer> subseq = new ArrayList<>(processed);
            subseq.add(candidates[i]);
            // Repeat element
            combinationSum(candidates, i, target, subseq, uniqueSets, ans, sum+candidates[i]);
            // Next element
            combinationSum(candidates, i+1, target, subseq, uniqueSets, ans, sum+candidates[i]);
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        HashSet<String> uniqueSets = new HashSet<>();
        combinationSum(candidates, 0, target, new ArrayList<>(), uniqueSets, ans, 0);
        return ans;
    }

    public void getCombinations(int n, int index, int k, List<Integer> processed, List<List<Integer>> ans) {
        if(k<0) {
            return;
        }
        if(k == 0) {
            ans.add(processed);
            return;
        }

        for(int i=index; i<=n; i++) {
            ArrayList<Integer> subset = new ArrayList<>(processed);
            subset.add(i);
            getCombinations(n, i+1, k-1, subset, ans);
        }

    }
    public List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        getCombinations(n,1,k,new ArrayList<>(), ans);
        return ans;
    }
}

public class Backtracking {
    public static void main(String[] args) {
        Problems bt = new Problems();
        SampleMaze sm = new SampleMaze();

        // Unique paths from start to end and avoid blockages
        /*System.out.println("Ans: "+bt.mazeUniquePaths(sm.getSampleMaze(0)));*/

        // Get all permutations
        /*ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1,2,3));
        System.out.println(bt.permutations(A));*/

        // Get all unique permutations
        /*ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1,2));
        System.out.println(bt.uniquePermutations(A));*/

        // Get all phone letter combinations
        /*System.out.println(bt.phoneLetterCombinations("012"));*/

        // Get total squareful arrays
        /*ArrayList<Integer> A = new ArrayList<>(Arrays.asList(428,56,88,12));
        System.out.println(bt.squarefulArrays(A));*/

        // Remove invalid parenthesis
        /*System.out.println(bt.removeInvalidParenthesis("(a)())()"));*/

        // Subsets II - get all unique subsets in sorted manner
        /*ArrayList<Integer> A = new ArrayList<>(Arrays.asList(5,4));
        System.out.println(bt.subsetsII(A));*/
        
        // Generate parenthesis
        /*System.out.println(bt.generateParenthesis(3));*/

        // Generate unique subsets
        /*System.out.println(bt.generateUniqueSubsets(new int[]{1,2,2}));*/

        // Combination sum
        /*System.out.println(bt.combinationSum(new int[]{2,2,3,7}, 7));*/

        // Get all combinations from 1 to n numbers of size k
        System.out.println(bt.combinations(4,2));
    }
}
