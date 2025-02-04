package DisjointSet;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static class DisjointSet {
        private List<Integer> set;
        private List<Integer> rank;

        DisjointSet(int n) {
            set = new ArrayList<>();
            rank = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                set.add(i);
                rank.add(0);
            }
        }

        public void union(int x, int y) {
            int up_x = find(x);
            int up_y = find(y);

            // Same Parents - Same component
            if (up_x == up_y) return;

            int rank_x = rank.get(x);
            int rank_y = rank.get(y);

            if (rank_y < rank_x) {
                set.set(up_x, up_y); // Add x set to y
                rank.set(up_y, rank.get(up_y)+1); // Increase rank of y
            } else {
                set.set(up_y, up_x); // Add y set to x
                rank.set(up_x, rank.get(up_x)+1); // Increase rank of x
            }
        }

        public int find(int x) {
            if (set.get(x) == x) {
                return x;
            }

            // Path Compression
            int up_x = find(set.get(x));
            set.set(x, up_x);
            return up_x;
        }

        public void printSet() {
            System.out.println(set.toString());
            System.out.println(rank.toString());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet(10);
        set.printSet();
        set.union(1,2);
        set.union(3,4);
        set.union(5,6);
        set.union(7,8);
        set.union(2,3);
        set.union(6,8);

        set.printSet();

        System.out.println(set.find(7));
        System.out.println(set.find(5));
        System.out.println(set.find(8));
        System.out.println(set.find(6));

        set.printSet();
    }
}
