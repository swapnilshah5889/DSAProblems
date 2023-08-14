package Backtracking;

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
        System.out.println(bt.phoneLetterCombinations("012"));

    }
}
