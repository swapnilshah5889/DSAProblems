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

    public void combinationSum2(int[] candidates, int index, int target, List<Integer> processed,
                               HashSet<String> uniqueSets, List<List<Integer>> ans, int sum) {

        if(sum > target) {
            return;
        }

        if(sum == target) {
            StringBuffer sb = new StringBuffer();
            for(Integer p : processed) {
                sb.append("*"+p);
            }
            if(!uniqueSets.contains(sb.toString())) {
                uniqueSets.add(sb.toString());
                ans.add(processed);
            }
        }

        for(int i=index; i<candidates.length; i++) {
            ArrayList<Integer> subseq = new ArrayList<>(processed);
            subseq.add(candidates[i]);
            // Next element
            if(i==index || candidates[i] > candidates[index]) {
                combinationSum2(candidates, i+1, target, subseq, uniqueSets, ans, sum+candidates[i]);
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        HashSet<String> uniqueSets = new HashSet<>();
        combinationSum2(candidates, 0, target, new ArrayList<>(), uniqueSets, ans, 0);
        return ans;
    }

    private boolean searchWord(char[][] board, boolean[][] visited, int i, int j, String word, int wIndex) {

        if(i<0 || j<0 || i>=board.length || j>=board[0].length) {
            return false;
        }

        /*Arrays.stream(visited).forEach(arr->{
            for(int x=0; x<arr.length; x++) {
                System.out.print(arr[x]+" ");
            }
            System.out.println();
        });
        System.out.println(wIndex);*/
        boolean found = false;
        if(!visited[i][j]) {
            visited[i][j] = true;
            char currChar = word.charAt(wIndex);
            // Found
            if (board[i][j] == currChar) {
                // Word found
                if (wIndex == word.length() - 1) {
                    found = true;
                }
                // Find the word
                else {
                    // Check all neighbors
                    found = searchWord(board, visited, i + 1, j, word, wIndex + 1)
                            || searchWord(board, visited, i, j + 1, word, wIndex + 1)
                            || searchWord(board, visited, i - 1, j, word, wIndex + 1)
                            || searchWord(board, visited, i, j - 1, word, wIndex + 1);
                }
            }
            visited[i][j] = false;
        }
        return found;
    }

    public boolean wordSearch(char[][] board, String word) {
        boolean visited[][] = new boolean[board.length][board[0].length];

        char firstChar = word.charAt(0);
        boolean found = false;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(board[i][j] == firstChar) {
                    for(int x=0; x<visited.length; x++) {
                        for(int y=0; y<visited[0].length; y++) {
                            visited[x][y] = false;
                        }
                    }

                    if(searchWord(board, visited, i, j, word, 0)){
                        found = true;
                        break;
                    }
                }
            }
        }

        return found;
    }

    public int findAllPaths(int[][] grid, int i, int j, int target, int curr) {

        // Invalid point
        if(i<0 || j<0 || i==grid.length || j == grid[0].length) {
            return 0;
        }
        // Target achieved
        if(grid[i][j] == 2 && target == curr) {
//            System.out.println("Achieved: "+target+ " - "+curr);
            return 1;
        }
        int ans = 0;
        // If not visited
        if(grid[i][j] == 0) {
            grid[i][j] = 1;

            ans = findAllPaths(grid, i+1, j, target, curr+1) +
                        findAllPaths(grid, i-1, j, target, curr+1) +
                        findAllPaths(grid, i, j+1, target, curr+1) +
                        findAllPaths(grid, i, j-1, target, curr+1);
            grid[i][j] = 0;
        }
        return ans;
    }

    public int uniquePathsIII(int[][] grid) {
        int emptySpaces = 0;
        int startx=0, starty=0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    emptySpaces++;
                }
                else if(grid[i][j] == 1) {
                    startx=i; starty=j;
                }
            }
        }
        grid[startx][starty] = 0;
        return findAllPaths(grid, startx, starty, emptySpaces, -1);
    }

    public boolean validSudoku(char[][] A) {
        HashSet<Character> set;
        // Check all rows
        for(int i=0; i<A.length; i++) {
            set = new HashSet<>();
            for(int j=0; j<A.length; j++) {
                // If not empty
                if(A[i][j] != '.') {
                    // If duplicate return false
                    if(set.contains(A[i][j])) {
                        return false;
                    }
                    set.add(A[i][j]);
                }
            }
        }

        // Check all columns
        for(int i=0; i<A.length; i++) {
            set = new HashSet<>();
            for(int j=0; j<A.length; j++) {
                // If not empty
                if(A[j][i] != '.') {
                    // If duplicate return false
                    if(set.contains(A[j][i])) {
                        return false;
                    }
                    set.add(A[j][i]);
                }
            }
        }

        // Check all blocks
        for(int i=0; i<9; i+=3) {
            for(int j=0; j<9; j+=3) {
                set = new HashSet<>();
                // Check the block
                for(int x = i; x<i+3; x++) {
                    for(int y = j; y<j+3; y++) {
                        // If not empty
                        if(A[x][y] != '.') {
                            // If duplicate return false
                            if(set.contains(A[x][y])) {
                                return false;
                            }
                            set.add(A[x][y]);
                        }
                    }
                }

            }
        }
        return true;
    }

    public boolean sudokuRecurr(char[][] A, int k, int n) {
        if(k == 81) {
            return true;
        }
        int i = k/n;
        int j = k%n;
        boolean valid = false;
        if(A[i][j] == '.') {
            for(int val=1; val<=9 ;val++) {
                char c = (char) (val+'0');
                A[i][j] = c;
                // If sudoku is valid
                if(validSudoku(A)) {
                    if (sudokuRecurr(A, k + 1, n)) {
                        valid = true;
                        break;
                    }
                }
            }
            if(!valid)
                A[i][j] = '.';
        }
        else {
            valid = sudokuRecurr(A, k+1, n);
        }
        return valid;
    }

    public void printSudoku(char[][] a) {
        for(int i=0; i<a.length; i++) {
            for(int j=0; j<a.length; j++) {
                System.out.print(a[i][j] +" ");
            }
            System.out.println();
        }
    }

    public char[][] solveSudoku(char[][] A) {
        sudokuRecurr(A, 0, 9);
        return A;
    }

    public void placeQueen(int[][] board, int i, int j) {
        // column
        for(int x=i; x<board.length; x++) {
            board[x][j]++;
        }
        // Diagonal

        for(int x=i+1, y = j+1; x<board.length && y<board[0].length; x++, y++) {
            board[x][y]++;
        }

        // Anti Diagonal
        for(int x=i+1, y = j-1; y>=0 && x<board.length; x++, y--) {
            board[x][y]++;
        }
    }
    public void removeQueen(int[][] board, int i, int j) {
        // column
        for(int x=i; x<board.length; x++) {
            board[x][j]--;
        }
        // Diagonal

        for(int x=i+1, y = j+1; x<board.length && y<board[0].length; x++, y++) {
            board[x][y]--;
        }

        // Anti Diagonal
        for(int x=i+1, y = j-1; y>=0 && x<board.length; x++, y--) {
            board[x][y]--;
        }
    }

    public void printBoard(int[][] board) {
        Arrays.stream(board).forEach(arr -> {
            for(int a : arr) {
                System.out.print((a) +" ");
            }
            System.out.println();
        });
    }

    private boolean solveNQueens(int[][] board, int n, int i, int placed,
                                 ArrayList<ArrayList<String>> ans) {
        // All rows traversed
        if(i == n) {
            if(placed == n) {
                int[][] boardSolution = new int[board.length][board.length];
                for(int x=0; x<board.length; x++) {
                    boardSolution[x] = Arrays.copyOf(board[x], board[x].length);
                }
                ArrayList<String> solution = new ArrayList<>();
                for (int x = 0; x < boardSolution.length; x++) {
                    StringBuffer sb = new StringBuffer();
                    for (int y = 0; y < boardSolution.length; y++) {
                        if (boardSolution[x][y] == 1) {
                            sb.append('Q');
                            removeQueen(boardSolution, x, y);
                        } else {
                            sb.append('.');
                        }
                    }
                    solution.add(sb.toString());
                }
                ans.add(solution);
            }
            return true;
        }

        boolean found = false;
        // Check if possible to place queen for current row
        for (int y = 0; y < n; y++) {
            if(board[i][y] == 0) {
                placeQueen(board, i, y);
                found = solveNQueens(board, n, i + 1, placed+1, ans);
                removeQueen(board, i, y);
            }
        }

        return found;
    }

    public ArrayList<ArrayList<String>> nQueens(int A) {
        int[][] board = new int[A][A];
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board.length; j++) {
                board[i][j] = 0;
            }
        }
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        solveNQueens(board, A, 0, 0, ans);
        return ans;
    }

    public boolean matrixValid(ArrayList<ArrayList<Integer>> matrix, int maxSum) {

        // Check all rows
        for(int i=0; i<matrix.size(); i++) {

            for(int j=0; j<matrix.get(0).size(); j++) {
                int sum = 0;
                for(int k=j; k<matrix.get(0).size(); k++) {
                    sum+=matrix.get(i).get(k);
                    if(sum>maxSum) {
                        return false;
                    }
                }
            }
        }

        // Check all columns
        for(int i=0; i<matrix.get(0).size(); i++) {

            for(int j=0; j<matrix.size(); j++) {
                int sum = 0;
                for(int k=j; k<matrix.size(); k++) {
                    sum+=matrix.get(k).get(i);
                    if(sum > maxSum) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean verticalHorizontalSum(ArrayList<ArrayList<Integer>> matrix, int maxSum, int op) {

        if(op == -1) {
            return false;
        }

        // Check all rows
        for(int i=0; i<matrix.size(); i++) {

            for(int j=0; j<matrix.get(0).size(); j++) {
                int sum = 0;
                for(int k=j; k<matrix.get(0).size(); k++) {
                    sum+=matrix.get(i).get(k);
//                    System.out.println(i + "," + j + " - "+i+","+k+" => "+sum);
                    if(sum>maxSum) {

                        for(int index=j; index<=k; index++) {
                            // Set Backtracking value
                            matrix.get(i).set(index, matrix.get(i).get(index)*-1);
                            if(verticalHorizontalSum(matrix, maxSum, op-1)) {
                                return true;
                            }
                            // Backtrack unset value
                            matrix.get(i).set(index, matrix.get(i).get(index)*-1);
                        }
                        return false;
                    }
                }
            }
        }

        // Check all columns
        for(int i=0; i<matrix.get(0).size(); i++) {

            for(int j=0; j<matrix.size(); j++) {
                int sum = 0;
                for(int k=j; k<matrix.size(); k++) {
                    sum+=matrix.get(k).get(i);
//                    System.out.println(j + "," + i + " - "+k+","+i+" => "+sum);
                    if(sum > maxSum) {
                        for(int index=j; index<=k; index++) {
                            // Set Backtracking value
                            matrix.get(index).set(i, matrix.get(index).get(i)*-1);
                            if(verticalHorizontalSum(matrix, maxSum, op-1)) {
                                return true;
                            }
                            // Backtrack unset value
                            matrix.get(index).set(i, matrix.get(index).get(i)*-1);
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int verticalAndHorizontalSums(int A, ArrayList<ArrayList<Integer>> B, int C) {
        boolean ans = verticalHorizontalSum(B, C, A);
        B.forEach(list -> {
            list.forEach(val -> {
                System.out.print(val +" ");
            });
            System.out.println();
        });
        return ans ? 1 : 0;
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

        // Combination sum: Get unique sub-array combinations that sum up to target
        // And repeating elements is allowed
        /*System.out.println(bt.combinationSum(new int[]{2,2,3,7}, 7));*/

        // Get all combinations from 1 to n numbers of size k
        /*System.out.println(bt.combinations(4,2));*/

        // Combination Sum II: Get unique sub-array combinations that sum up to target
        // And repeating elements is now allowed
        /*System.out.println(bt.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));*/

        // Word Search: Search the given word in a given matrix
        /*char[][] grid = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(bt.wordSearch(grid, "SEB"));*/

        // Unique Paths - Find all unique paths from source to target such that
        // it covers all empty spaces and avoids all monsters
        /*int[][] grid = new int[][]{{1,0,0,0}, {0,0,0,0}, {0,0,2,-1}};
        System.out.println(bt.uniquePathsIII(grid));*/

        // Sudoku solver
        /*char[][] sudoku = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        bt.solveSudoku(sudoku);
        System.out.println(bt.validSudoku(sudoku));
        bt.printSudoku(sudoku);*/

        // N Queens Problem
        /*System.out.println(bt.nQueens(4));*/

        // Vertical and Horizontal Sum [[],[],[]]
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(16,-20,44,-46,-85,-96,89)));
        matrix.add(new ArrayList<>(Arrays.asList(60,-96,-67,-33,91,27,58)));
        matrix.add(new ArrayList<>(Arrays.asList(26,90,-80,-39,47,-42,64)));
        System.out.println(bt.verticalHorizontalSum( matrix, 5, 86));
    }

}