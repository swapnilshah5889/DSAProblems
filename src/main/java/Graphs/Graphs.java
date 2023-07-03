package Graphs;
import java.util.*;

public class Graphs {

    public static class Pair {
        Integer key;
        Integer val;

        Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static class GraphEdge {
        int node1;
        int node2;
        Integer weight;

        public GraphEdge(int node1, int node2, int weight) {
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;

        }
    }

    public static void printMatrix(ArrayList<ArrayList<Integer>> graph) {
        for(ArrayList<Integer> row : graph) {
            System.out.println(row);
        }
        System.out.println();
    }

    public static int orangeTimeToRot(ArrayList<ArrayList<Integer>> A) {

        Queue<Pair> queue = new LinkedList<>();
        for(int i=0; i<A.size(); i++) {
            for(int j=0; j<A.get(i).size(); j++) {
                if(A.get(i).get(j) == 2)
                    queue.add(new Pair(i,j));
            }
        }
        int time=0;

        int[] dx = new int[]{-1, 0, 0, 1};
        int[] dy = new int[]{0, -1, 1, 0};
        int rowEnd = A.size()-1;
        int colEnd = A.get(0).size()-1;
        printMatrix(A);
        while(!queue.isEmpty()) {
            int rottenOranges = queue.size();
            //Iterate over current level
            boolean foundGoodOranges = false;
            for(int i=0; i<rottenOranges; i++) {
                Pair rottenOrange = queue.poll();

                //Iterating over all neighbours
                for(int x=0; x<dx.length; x++) {
                    int newX = rottenOrange.key+dx[x];
                    int newY = rottenOrange.val+dy[x];

                    //Check if new positions are inside the grid
                    if(newX>=0 && newX<=rowEnd && newY>=0 && newY<=colEnd) {

                        //Check if new location has orange which is not rotten
                        if(A.get(newX).get(newY) == 1) {
                            //Make it rotten
                            A.get(newX).set(newY, 2);
                            //Add to queue for next level bfs
                            queue.add(new Pair(newX, newY));

                            //Increment time
                            if(!foundGoodOranges) {
                                foundGoodOranges = true;
                                time++;
                            }
                        }
                    }
                }

            }
            printMatrix(A);

        }

        for(int i=0; i<A.size(); i++) {
            for(int j=0; j<A.get(i).size(); j++) {
                if(A.get(i).get(j) == 1)
                    return -1;
            }
        }

        return time;
    }

    public static double circleDistance(int a, int b, int x, int y) {
        double xd = Math.pow(x-a,2);
        double yd = Math.pow(y-b,2);
        return Math.pow(xd + yd, 0.5);
    }
    public static void markMatrixCircles (ArrayList<ArrayList<Integer>> graph, int[] x, int[] y, int radius) {
        int rowEnd = graph.size()-1;
        int colEnd = graph.get(0).size()-1;

        //Iterate over graph
        for(int i=0; i<=rowEnd; i++) {
            for(int j=0; j<=colEnd; j++) {

                //Iterate over all circles
                for(int pos=0; pos<x.length; pos++) {
                    int a = x[pos];
                    int b = y[pos];
                    double dist = circleDistance(a,b,i,j);
                    if(dist<=radius) {
                        System.out.println(dist);
                        graph.get(i).set(j, 1);
                    }
                }

            }
        }

        //printMatrix(graph);
    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int x, int y, int[] dx, int[] dy) {

        if(graph.get(x).get(y)!=1) {

            graph.get(x).set(y,1);

            for(int i=0; i<dx.length; i++) {
                int row = x+dx[i];
                int col = y+dy[i];
                //DFS on all unvisited neighbors
                if(row>=0 && row<graph.size() && col>=0 && col<graph.get(0).size() && graph.get(row).get(col) == 0)
                    dfs(graph, row, col, dx, dy);
            }

        }
    }

    public static boolean validPath(ArrayList<ArrayList<Integer>> graph, int[] x, int[] y, int radius) {
        markMatrixCircles(graph, x, y, radius);
        int rowEnd = graph.size()-1;
        int colEnd = graph.get(0).size()-1;

        //If target is already inside circle return false
        if(graph.get(rowEnd).get(colEnd) == 1)
            return false;

        //Check path to dfs
        int[] dx = new int[]{-1,-1,-1,0,0,1,1,1};
        int[] dy = new int[]{-1,0,1,-1,1,-1,0,1};

        dfs(graph, 0, 0, dx, dy);

        return graph.get(rowEnd).get(colEnd) == 1;

    }
    public static ArrayList<ArrayList<Integer>> getSampleGraph(int number) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        switch (number) {
            case 1:
                graph.add(new ArrayList<>(Arrays.asList(1,0,1,2,1)));
                graph.add(new ArrayList<>(Arrays.asList(1,1,1,1,1)));
                graph.add(new ArrayList<>(Arrays.asList(0,2,0,1,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,1,1,1,1)));
                graph.add(new ArrayList<>(Arrays.asList(1,1,1,2,0)));
                break;
            case 2:
                graph.add(new ArrayList<>(Arrays.asList(1,0,1,0,1)));
                graph.add(new ArrayList<>(Arrays.asList(1,1,1,1,1)));
                graph.add(new ArrayList<>(Arrays.asList(0,2,0,1,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,1,1,1,1)));
                graph.add(new ArrayList<>(Arrays.asList(1,1,1,0,0)));
                break;
            case 3:
                graph.add(new ArrayList<>(Arrays.asList(1, 1, 0, 1, 2, 0)));
                graph.add(new ArrayList<>(Arrays.asList(1, 2, 2, 1, 2, 2)));
                graph.add(new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0)));
                graph.add(new ArrayList<>(Arrays.asList(0, 2, 0, 2, 1, 0)));
                graph.add(new ArrayList<>(Arrays.asList(0, 2, 0, 1, 0, 0)));
                graph.add(new ArrayList<>(Arrays.asList(2, 0, 0, 0, 0, 0)));
                graph.add(new ArrayList<>(Arrays.asList(1, 2, 2, 0, 0, 1)));
                graph.add(new ArrayList<>(Arrays.asList(1, 2, 0, 1, 0, 0)));
                break;
            //All Zeros
            case 4:
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                graph.add(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0)));
                break;
            //Undirected Weighted Graph
            case 5:
                graph.add(new ArrayList<>(Arrays.asList(2, 4, 10)));
                graph.add(new ArrayList<>(Arrays.asList(3, 4, 1)));
                graph.add(new ArrayList<>(Arrays.asList(3, 6, 1)));
                graph.add(new ArrayList<>(Arrays.asList(1, 2, 4)));
                graph.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
                break;

            case 6:
                graph.add(new ArrayList<>(Arrays.asList(1,2)));
                graph.add(new ArrayList<>(Arrays.asList(1,3)));
                graph.add(new ArrayList<>(Arrays.asList(2,3)));
                graph.add(new ArrayList<>(Arrays.asList(1,4)));
                graph.add(new ArrayList<>(Arrays.asList(4,3)));
                graph.add(new ArrayList<>(Arrays.asList(4,5)));
                graph.add(new ArrayList<>(Arrays.asList(3,5)));
                break;

            case 7:
                graph.add(new ArrayList<>(Arrays.asList(1,2)));
                graph.add(new ArrayList<>(Arrays.asList(2,3)));
                graph.add(new ArrayList<>(Arrays.asList(4,3)));
                graph.add(new ArrayList<>(Arrays.asList(4,5)));
                graph.add(new ArrayList<>(Arrays.asList(6,1)));
                break;

            case 8:
                graph.add(new ArrayList<>(Arrays.asList(1,2)));
                graph.add(new ArrayList<>(Arrays.asList(2,3)));
                graph.add(new ArrayList<>(Arrays.asList(5,6)));
                graph.add(new ArrayList<>(Arrays.asList(5,7)));
                break;

            // Weighted Undirected Graph
            case 9:
                graph.add(new ArrayList<>(Arrays.asList(1,2,1)));
                graph.add(new ArrayList<>(Arrays.asList(2,3,4)));
                graph.add(new ArrayList<>(Arrays.asList(1,4,3)));
                graph.add(new ArrayList<>(Arrays.asList(4,3,2)));
                graph.add(new ArrayList<>(Arrays.asList(1,3,10)));
                break;

            case 10:
                graph.add(new ArrayList<>(Arrays.asList(1,2,1)));
                graph.add(new ArrayList<>(Arrays.asList(2,3,2)));
                graph.add(new ArrayList<>(Arrays.asList(3,4,4)));
                graph.add(new ArrayList<>(Arrays.asList(1,4,3)));
                break;
        }

        return graph;
    }

    public static ArrayList<Integer> Djkstras(int A, ArrayList<ArrayList<Integer>> B, int C) {

        ArrayList<Integer> cost = new ArrayList<>();
        for(int i=0; i<A; i++)
            cost.add(-1);

        cost.set(C,0);

        PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.key.compareTo(o2.key);
            }
        });

        //Build Graph
        HashMap<Integer, List<Pair>> graph = new HashMap<>();
        for(int i=0; i<B.size(); i++) {
            int key = B.get(i).get(0);
            Pair neigh = new Pair(B.get(i).get(2), B.get(i).get(1));
            if(graph.containsKey(key)) {
                graph.get(key).add(neigh);
            }
            else    {
                ArrayList<Pair> neighbors = new ArrayList<>();
                neighbors.add(neigh);
                graph.put(key, neighbors);
            }
            if(graph.containsKey(neigh.val)) {
                graph.get(neigh.val).add(new Pair(neigh.key, key));
            }
            else {
                ArrayList<Pair> neighbors = new ArrayList<>();
                neighbors.add(new Pair(neigh.key, key));
                graph.put(neigh.val, neighbors);
            }
            if(key == C) {
                cost.set(neigh.val, neigh.key);
                queue.add(neigh);
            }
            else if(neigh.val == C) {
                cost.set(key, neigh.key);
                queue.add(new Pair(neigh.key, key));
            }
        }

        while(!queue.isEmpty()) {
            Pair currNode = queue.remove();
            List<Pair> neighbors = graph.get(currNode.val);
            for(int i=0; i<neighbors.size(); i++) {
                Pair neighNode = neighbors.get(i);
                if(cost.get(neighNode.val) == -1) {
                    cost.set(neighNode.val, currNode.key + neighNode.key);
                    queue.add(new Pair(cost.get(neighNode.val), neighNode.val));
                }
                else {
                    int newCost = currNode.key + neighNode.key;
                    if(newCost < cost.get(neighNode.val)) {
                        queue.add(new Pair(newCost, neighNode.val));
                        cost.set(neighNode.val, newCost);
                    }
                }
            }
        }

        return cost;

    }

    public static boolean dfsDetectCycle(HashMap<Integer, ArrayList<Integer>> graph, int node,
                       HashSet<Integer> currPath, HashSet<Integer> visited) {
        if(currPath.contains(node))
            return true;

        if(graph.containsKey(node) && !visited.contains(node)) {

            visited.add(node);
            currPath.add(node);
            ArrayList<Integer> neighbors = graph.get(node);
            for(int i =0; i<neighbors.size(); i++) {
                if(dfsDetectCycle(graph, neighbors.get(i), currPath, visited)) {
                    return true;
                }
            }
            currPath.remove(node);
        }
        return false;
    }

    public static boolean detectCycle(int A, ArrayList<ArrayList<Integer>> B) {
        //Build Graph
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int i=0; i<B.size(); i++) {
            int node = B.get(i).get(0);
            int neighbor = B.get(i).get(1);
            if(graph.containsKey(node)) {
                graph.get(node).add(neighbor);
            }
            else {
                ArrayList<Integer> neighbors = new ArrayList<>();
                neighbors.add(neighbor);
                graph.put(node, neighbors);
            }

        }

        HashSet<Integer> visited = new HashSet<>();
        //BFS
        for(int j=1; j<=A; j++) {
            if(!visited.contains(j)) {
                HashSet<Integer> currPath = new HashSet<>();
                if(dfsDetectCycle(graph, j, currPath, visited)){
                    return true;
                }
            }
        }

        return false;
    }

    public static int reverseEdges(int A, ArrayList<ArrayList<Integer>> B) {

        // Initialize cost array with -1
        ArrayList<Integer> cost = new ArrayList<>();
        for(int i=0; i<=A; i++)
            cost.add(-1);

        // Set distance of starting node to 0
        int startNode = 1; // Starting Node
        cost.set(startNode,0);

        // Create a priority queue with Pair Object
        PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.key.compareTo(o2.key);
            }
        });

        // Build Graph
        HashMap<Integer, List<Pair>> graph = new HashMap<>();
        for(int i=0; i<B.size(); i++) {

            //Curr Node
            int currNode = B.get(i).get(0);

            //Neighbor Pair with weight and neighbor id
            Pair neigh = new Pair(0, B.get(i).get(1));

            //Add neighbor to the graph
            if(graph.containsKey(currNode)) {
                graph.get(currNode).add(neigh);
            }
            else {
                ArrayList<Pair> neighbors = new ArrayList<>();
                neighbors.add(neigh);
                graph.put(currNode, neighbors);
            }

            //Add reverse edge
            if(graph.containsKey(neigh.val)) {
                graph.get(neigh.val).add(new Pair(1, currNode));
            }
            else {
                ArrayList<Pair> neighbors = new ArrayList<>();
                neighbors.add(new Pair(1, currNode));
                graph.put(neigh.val, neighbors);
            }

            // Add all starting node edges to the queue
            if(currNode == startNode) {
                cost.set(neigh.val, neigh.key);
                queue.add(neigh);
            }
            // Add Reverse Edge to Starting Node
            else if(neigh.val == startNode) {
                cost.set(currNode, 1);
                queue.add(new Pair(1, currNode));
            }
        }

        System.out.println(cost);
        /*while(!queue.isEmpty()) {
            Pair p = queue.poll();
            System.out.println(p.val +" => "+p.key);
        }*/
        while(!queue.isEmpty()) {
            Pair currNode = queue.remove();
            List<Pair> neighbors = graph.get(currNode.val);
            for(int i=0; i<neighbors.size(); i++) {
                Pair neighNode = neighbors.get(i);
                if(cost.get(neighNode.val) == -1) {
                    cost.set(neighNode.val, currNode.key + neighNode.key);
                    queue.add(new Pair(cost.get(neighNode.val), neighNode.val));
                }
                else {
                    int newCost = currNode.key + neighNode.key;
                    if(newCost < cost.get(neighNode.val)) {
                        queue.add(new Pair(newCost, neighNode.val));
                        cost.set(neighNode.val, newCost);
                    }
                }
            }
            System.out.println(cost);
        }

        return cost.get(A);
    }

    public static int batchesDfs(HashMap<Integer, List<Integer>> graph, int node,
                                 boolean[] visited, ArrayList<Integer> strengths) {

        if(visited[node]) {
            return 0;
        }
        else {
            int sum = strengths.get(node);
            visited[node] = true;
            if(graph.containsKey(node)) {
                for (int i = 0; i < graph.get(node).size(); i++) {
                    sum += batchesDfs(graph, graph.get(node).get(i), visited, strengths);
                }
            }
            return sum;
        }
    }

    public static int batches(int A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> C, int D) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<C.size(); i++) {
            int key = C.get(i).get(0)-1;
            int neigh = C.get(i).get(1)-1;
            if(graph.containsKey(key)) {
                graph.get(key).add(neigh);
            }
            else    {
                ArrayList<Integer> neighbors = new ArrayList<>();
                neighbors.add(neigh);
                graph.put(key, neighbors);
            }

            if(graph.containsKey(neigh)) {
                graph.get(neigh).add(key);
            }
            else {
                ArrayList<Integer> neighbors = new ArrayList<>();
                neighbors.add(key);
                graph.put(neigh, neighbors);
            }
        }
        boolean[] visited = new boolean[A];
        Arrays.fill(visited, false);
        System.out.println(graph);

        int count = 0;
        for(int i=0; i<A; i++) {
            if(!visited[i]) {
                int sum = batchesDfs(graph, i, visited, B );
                System.out.println("Sum : "+sum);
                if(sum>=D) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int findRoot(int[] treeArr, int node) {
        if(treeArr[node] == node)
            return node;
        int temp = findRoot(treeArr, treeArr[node]);
        treeArr[node] = temp;
        return temp;
    }
    public static int commutableIslands(int A, ArrayList<ArrayList<Integer>> B) {

        PriorityQueue<GraphEdge> edgesQueue = new PriorityQueue<>(new Comparator<GraphEdge>() {
            @Override
            public int compare(GraphEdge o1, GraphEdge o2) {
                return o1.weight.compareTo(o2.weight);
            }
        });

        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        // Build Graph
        for(int i=0; i<B.size(); i++) {
            int node1 = B.get(i).get(0);
            int node2 = B.get(i).get(1);
            int weight = B.get(i).get(2);

            // Node 1
            if(graph.containsKey(node1)) {
                graph.get(node1).add(node2);
            }
            else {
                List<Integer> connections = new ArrayList<>();
                connections.add(node2);
                graph.put(node1, connections);
            }

            // Node 2
            if(graph.containsKey(node2)) {
                graph.get(node2).add(node1);
            }
            else {
                List<Integer> connections = new ArrayList<>();
                connections.add(node1);
                graph.put(node2, connections);
            }

            edgesQueue.add(new GraphEdge(node1, node2, weight));
        }

        int[] kings = new int[A+1];
        for(int i=0; i<=A; i++) {
            kings[i] = i;
        }

        int cost = 0;

        // Minimum Spanning Tree
        // Find least cost for building minimum spanning tree
        while (!edgesQueue.isEmpty()){
            GraphEdge edge = edgesQueue.poll();
            int nodeRoot1 = findRoot(kings, edge.node1);
            int nodeRoot2 = findRoot(kings, edge.node2);

            // Separate components
            if (nodeRoot1 != nodeRoot2) {
                kings[nodeRoot2] = nodeRoot1;
                cost+=edge.weight;
            }
        }

        return cost;
    }

    public static int getHashValue(int row, int col, int rows, int cols) {
        // Max row size is 500 so multiplying it by 501 will always make
        // this hash-value unique for every point
        return ( ((row)*501) + (col+1) );
    }

    public static int knight(int A, int B, int C, int D, int E, int F) {

        // Edge Case
        if(C == E && D == F) {
            return 0;
        }

        int [] rowPoints = new int[]{-2, -2, 1, -1, 2, 2, 1, -1};
        int [] colPoints = new int[]{-1, 1, 2, 2, -1, 1, -2, -2};

        Queue<Pair> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(new Pair(C,D));
        visited.add(getHashValue(C, D, A, B));
        int levels = 0;
        while(!queue.isEmpty()) {
            int qSize = queue.size();
            levels++;
            for(int j=0; j<qSize; j++) {
                Pair p = queue.remove();
                for(int i=0; i<rowPoints.length; i++) {
                    int rowNew = p.key + rowPoints[i];
                    int colNew = p.val + colPoints[i];
                    int hashValue = getHashValue(rowNew, colNew, A, B);
                    // If destination found
                    if(rowNew == E && colNew == F) {
                        return levels;
                    }
                    // If valid point & not in queue
                    else if( (rowNew>0 && rowNew<=A && colNew>0 && colNew<=B)
                            && !visited.contains(hashValue) ) {
                        visited.add(hashValue);
                        queue.add(new Pair(rowNew, colNew));
                    }
                }
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        //Find all rotten oranges
        /*System.out.println(orangeTimeToRot(getSampleGraph(3)));*/

        //Find valid path in matrix having circles
        /*int[] x = new int[]{3,9,6,0};
        int[] y = new int[]{3,1,2,4};
        System.out.println(validPath(getSampleGraph(4),x,y,2));*/

        //Djkstras Algorithm for minimum cost to destination from source
        /*System.out.println(Djkstras(7, getSampleGraph(5), 2));*/

        //Detect Cycle in Graph
        /*System.out.println(detectCycle(5, getSampleGraph(6)));*/

        //Batches
        /*System.out.println(batches(7, new ArrayList<Integer>(Arrays.asList(1,6,7,2,9,4,5))
                , getSampleGraph(8),12));*/

        // Krushkal's Algorithm
        /*System.out.println(commutableIslands(4, getSampleGraph(9)));*/

        // Knight's problem - Find out if knight can reach destination
        // from a given point in a chess board
        // System.out.println(knight(4,7,2,6,2,4));
        System.out.println(knight(384,387,106,134,210,35));
        System.out.println(knight(173,237,46,81,9,235));

    }
}
