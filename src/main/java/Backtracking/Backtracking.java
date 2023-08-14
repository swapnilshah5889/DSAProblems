package Backtracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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

}

public class Backtracking {



    public static void main(String[] args) {
        Problems bt = new Problems();
        SampleMaze sm = new SampleMaze();

        // Unique paths from start to end and avoid blockages
        System.out.println("Ans: "+bt.mazeUniquePaths(sm.getSampleMaze(0)));
    }
}