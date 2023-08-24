package Graphs;
import DynamicProgramming.DynamicProgramming;
import com.sun.source.tree.Tree;

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

    public static class GraphEdge2 {
        int node1;
        int node2;
        Integer weight;
        int index;

        public GraphEdge2(int node1, int node2, int weight, int index) {
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
            this.index = index;

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

            case 11:
                graph.add(new ArrayList<>(Arrays.asList(1,2,2)));
                graph.add(new ArrayList<>(Arrays.asList(1,3,2)));
                graph.add(new ArrayList<>(Arrays.asList(2,3,2)));
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

    public static ArrayList<Integer> edgeInMST(int A, ArrayList<ArrayList<Integer>> B) {

        /*PriorityQueue<GraphEdge2> edgesQueue = new PriorityQueue<>(new Comparator<GraphEdge2>() {
            @Override
            public int compare(GraphEdge2 o1, GraphEdge2 o2) {
                return o1.weight.compareTo(o2.weight);
            }
        });

        // Build Edges Priority Queue
        for(int i=0; i<B.size(); i++) {
            int node1 = B.get(i).get(0);
            int node2 = B.get(i).get(1);
            int weight = B.get(i).get(2);

            edgesQueue.add(new GraphEdge2(node1, node2, weight,i));
        }*/

        // Build 2d matrix to store the edges
        int[][] edges = new int[B.size()][4];
        for(int i=0; i<B.size(); i++) {
            edges[i][0] = B.get(i).get(0);
            edges[i][1] = B.get(i).get(1);
            edges[i][2] = B.get(i).get(2);
            edges[i][3] = i;
        }

        // Sort array based on weight
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                Integer data1 = o1[2];
                Integer data2 = o2[2];
                return data1.compareTo(data2);
            }
        });

        int[] kings = new int[A+1];
        for(int i=0; i<=A; i++) {
            kings[i] = i;
        }

        ArrayList<Integer> ans = new ArrayList<Integer>(Collections.nCopies(B.size(), 0));

        // Minimum Spanning Tree
        // Find least cost for building minimum spanning tree
        int i=0;
        while(i<edges.length) {
            int j=i;
            ArrayList<Integer> edgeList = new ArrayList<>();
            // Same weight edges
            while(j<edges.length && edges[i][2] == edges[j][2]) {

                int nodeRoot1 = findRoot(kings, edges[j][0]);
                int nodeRoot2 = findRoot(kings, edges[j][1]);
                if (nodeRoot1 != nodeRoot2) {
                    ans.set(edges[j][3], 1);
                    edgeList.add(j);
                }
                j++;
            }


            // Merge Edges
            for(int index : edgeList) {
                int nodeRoot1 = findRoot(kings, edges[index][0]);
                int nodeRoot2 = findRoot(kings, edges[index][1]);
                if (nodeRoot1 != nodeRoot2) {
                    kings[nodeRoot2] = nodeRoot1;
                }
            }
            i=j;
        }
        /*while (!edgesQueue.isEmpty()){
            int currWeight = edgesQueue.peek().weight;
            List<GraphEdge2> edgeList = new ArrayList<>();

            // Check for same weight edges
            while(!edgesQueue.isEmpty() && edgesQueue.peek().weight == currWeight) {
                GraphEdge2 edge = edgesQueue.poll();
                int nodeRoot1 = findRoot(kings, edge.node1);
                int nodeRoot2 = findRoot(kings, edge.node2);

                // Separate components - Set true for MST
                if (nodeRoot1 != nodeRoot2) {
                    ans.set(edge.index, 1);
                    edgeList.add(edge);
                }
            }

            // Merge Edges
            for(int i=0; i<edgeList.size(); i++) {
                int nodeRoot1 = findRoot(kings, edgeList.get(i).node1);
                int nodeRoot2 = findRoot(kings, edgeList.get(i).node2);
                if (nodeRoot1 != nodeRoot2) {
                    kings[nodeRoot2] = nodeRoot1;
                }
            }

        }*/

        return ans;
    }

    // LC 1926 - Find nearest exit in a maze
    public static int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;

        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] start = new Integer[]{entrance[0],entrance[1]};
        queue.add(start);
        queue.add(null);
        maze[start[0]][start[1]] = '+';

        int[] dx = new int[]{0,0,-1,1};
        int[] dy = new int[]{-1,1,0,0};

        int level = 0;
        boolean exitFound = false;
        while(queue.peek()!=null) {
            level++;
            while(queue.peek()!=null) {
                Integer[] point = queue.remove();
                for(int k=0; k<dx.length; k++) {
                    int newX = point[0]+dx[k];
                    int newY = point[1]+dy[k];

                    // Valid point
                    if(newX>=0 && newX<rows && newY>=0 && newY<cols) {
                        if(maze[newX][newY] == '.') {
                            maze[newX][newY] = '+';
                            queue.add(new Integer[]{newX,newY});
                            if(newX == 0 || newY==0 || newX == rows-1 || newY == cols-1) {
                                exitFound = true;
                                break;
                            }
                        }
                    }
                }
            }
            // Mark end of current level
            queue.remove();
            queue.add(null);
            if(exitFound) {
                break;
            }
        }

        return exitFound ? level : -1;
    }

    public static int findPath(int[][] grid, boolean[][] visited, boolean[][] dp, int startRow, int startCol, int targetRow,
                               int targetCol, int[] dx, int[] dy, int min, int maxMin){

        if(startRow == targetRow && startCol == targetCol) {
            totalPaths++;
            return min;
        }

        // Better route not possible
        if(maxMin != -1 && min <= maxMin) {
            return maxMin;
        }

        min = Math.min(min, grid[startRow][startCol]);

        visited[startRow][startCol] = true;
        PriorityQueue<ArrayList<Integer>> points = new PriorityQueue<>(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if(o2.get(2) == o1.get(2)) {

                    return o1.get(2).compareTo(o2.get(2));
                }
                else {
                    return o2.get(2).compareTo(o1.get(2));
                }
            }
        });
        for(int i=0; i<dx.length; i++) {
            int newX = dx[i] + startRow;
            int newY = dy[i] + startCol;

            // Valid point
            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length) {
                // Not visited
                if(!visited[newX][newY]) {

                    Double diffR1 = Math.pow(targetRow - newX,2);
                    Double diffC1 = Math.pow(targetCol - newY,2);
                    int dist1 = (int)Math.pow(diffR1+diffC1, 0.5);
                    points.add(new ArrayList<>(Arrays.asList(newX, newY, grid[newX][newY], dist1)));
                }
            }
        }

        while(!points.isEmpty()) {
            ArrayList<Integer> point = points.remove();
            totalRecursions++;
            if(maxMin == -1){
                maxMin = findPath(grid, visited, dp, point.get(0), point.get(1),
                        targetRow, targetCol, dx, dy, min, maxMin);
            }
            else {
                maxMin = Math.max(findPath(grid, visited, dp, point.get(0), point.get(1),
                        targetRow, targetCol, dx, dy, min, maxMin), maxMin);
            }
        }
        visited[startRow][startCol] = false;

        return maxMin;

    }
    private static int totalPaths;
    private static int totalRecursions;

    public static int avoidMonsters(int m, int n, int startRow, int startCol,
                                    int endRow, int endCol, ArrayList<Integer> monsterRows,
                                    ArrayList<Integer> monsterCols) {

        totalPaths = 0;
        totalRecursions = 0;
        // Init grid to -1
        int grid[][] = new int[m][n];
        boolean visited[][] = new boolean[m][n];
        boolean dp[][] = new boolean[m][n];

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid.length; j++) {
                grid[i][j] = -1;
                visited[i][j] = false;
                dp[i][j] = false;
            }
        }

        Queue<ArrayList<Integer>> queue = new LinkedList<>();

        // Init monsters in grid and queue
        for(int i=0; i<monsterRows.size(); i++) {
            queue.add(new ArrayList<>(Arrays.asList(
                    monsterRows.get(i),monsterCols.get(i)
            )));
            grid[monsterRows.get(i)][monsterCols.get(i)] = 0;
        }
        queue.add(null);

        int dx[] = new int[]{0,0,1,-1};
        int dy[] = new int[]{1,-1,0,0};
        int level = 1;
        // BFS
        while(queue.peek()!=null) {
            while(queue.peek()!=null) {
                ArrayList<Integer> monster = queue.remove();
                // Iterate over all neighbors
                for(int i=0; i<dx.length; i++) {
                    int newX = dx[i] + monster.get(0);
                    int newY = dy[i] + monster.get(1);

                    // Valid point
                    if(newX>=0 && newX<m && newY>=0 && newY<n) {
                        // If not visited
                        if(grid[newX][newY] == -1) {
                            queue.add(new ArrayList<>(Arrays.asList(newX,newY)));
                            grid[newX][newY] = level;
                        }
                    }
                }
            }
            queue.remove();
            queue.add(null);
            level++;
        }

        Arrays.stream(grid).forEach(arr -> {
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val + " ");
            });
            System.out.println();
        });
        int min = findPath(grid, visited, dp, startRow, startCol, endRow, endCol, dx, dy, Integer.MAX_VALUE, -1);
        System.out.println("Total Paths: "+totalPaths);
        System.out.println("Total Recursions: "+totalRecursions);
        return min;
    }

    public static void getAllPaths(List<List<Integer>> ans, List<Integer> path, int target,
                                   int[][] graph, int edge) {
        // Target reached
        if(edge == target) {
            path.add(edge);
            ans.add(new ArrayList<>(path));
            path.remove(path.size()-1);
            return;
        }

        // If edge has neighbors - Iterate over neighbors
        if(graph[edge].length > 0) {
            int[] neighbors = graph[edge];
            path.add(edge);
            for(Integer neighbor : neighbors) {
                getAllPaths(ans, path, target, graph, neighbor);
            }
            path.remove(path.size()-1);
        }

    }
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        getAllPaths(ans, new ArrayList<>(), graph.length-1, graph, 0);
        return ans;
    }

    public static int[][] updateMatrix(int[][] mat) {
        Queue<Pair> queue = new LinkedList<>();
        for(int i=0; i<mat.length; i++) {
            for(int j=0; j<mat.length; j++) {
                if(mat[i][j] == 0) {
                    queue.add(new Pair(i,j));
                }
                else {
                    mat[i][j] = -1;
                }
            }
        }

        queue.add(null);
        int dx[] = new int[]{1,-1,0,0};
        int dy[] = new int[]{0,0,1,-1};
        int level=1;
        // Breadth first search
        while(queue.peek() != null) {
            // Itereate over the current level
            while(queue.peek() != null) {
                Pair p = queue.remove();
                // Iterate over neighbors
                for(int i=0; i<dx.length; i++) {
                    int newX = p.key + dx[i];
                    int newY = p.val + dy[i];
                    // If valid point
                    if(newX>=0 && newY>=0 && newX<mat.length && newY<mat[0].length) {
                        // If point not visited
                        // Update the level in the matrix and
                        // Add this point to queue
                        if(mat[newX][newY] == -1) {
                            mat[newX][newY] = level;
                            queue.add(new Pair(newX, newY));
                        }
                    }
                }
            }
            // remove previous null
            queue.remove();
            // append null for the next level
            queue.add(null);
            level++;
        }

        return mat;
    }

    public static int damagedRoads(ArrayList<Integer> A, ArrayList<Integer> B) {
        TreeMap<Integer,List<Integer>> map = new TreeMap<>();
        // Rows
        int rows = A.size();
        for(int i=0; i<rows; i++) {
            map.putIfAbsent(A.get(i), new ArrayList<>());
            map.get(A.get(i)).add(1);
        }
        int cols = B.size();
        for(int i=0; i<cols; i++) {
            map.putIfAbsent(B.get(i), new ArrayList<>());
            map.get(B.get(i)).add(0);
        }
        rows++;
        cols++;
        int ans = 0;
        int mod = (int) Math.pow(10,9) + 7;
        for(Map.Entry<Integer, List<Integer>> edge : map.entrySet()) {
            //System.out.println("Edge : "+edge.getKey());
            int currWeight = edge.getKey();
            for(Integer direction : edge.getValue()) {
                // Column edge
                if(direction == 0) {
                    //System.out.println("Column => "+rows +" X "+currWeight);
                    ans = (int)(( ((long)rows * currWeight) + ans )%mod);
                    cols--;
                }
                // Row edge
                else {
                    //System.out.println("Row => "+cols +" X "+currWeight);
                    ans = (int)(( ((long)cols * currWeight) + ans )%mod);
                    rows--;
                }
            }

        }

        return ans;
    }

    static class Edge {
        Integer node, weight;
        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static int[] djkstras(int A, int[][] B, int C) {
        HashMap<Integer, List<Edge>> graph = new HashMap<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight.compareTo(o2.weight);
            }
        });
        int[] distances = new int[A];
        Arrays.fill(distances, -1);
        distances[C] = 0;

        for(int i=0; i<B.length; i++) {
            int node1 = B[i][0];
            int node2 = B[i][1];
            int weight = B[i][2];
            // Node 1 to node 2 edge
            graph.putIfAbsent(node1, new ArrayList<>());
            graph.get(node1).add(new Edge(node2, weight));
            // Node 2 to node 1 edge
            graph.putIfAbsent(node2, new ArrayList<>());
            graph.get(node2).add(new Edge(node1, weight));

            // Add edge to queue if starting node
            if(node1 == C) {
                queue.add(new Edge(node2, weight));
                distances[node2] = weight;
            }
            else if(node2 == C) {
                queue.add(new Edge(node1, weight));
                distances[node1] = weight;
            }
        }

        /*for(Map.Entry<Integer, List<Edge>> node : graph.entrySet()) {
            System.out.println("Edges of node: "+node.getKey());
            for(Edge edge : node.getValue()) {
                System.out.print(edge.node +" - "+edge.weight +" | ");
            }
            System.out.println();
        }*/

        boolean[] visited = new boolean[A];
        while(!queue.isEmpty()) {
            Edge edge = queue.remove();
            if(!visited[edge.node]) {
                visited[edge.node] = true;
                for (Edge neighbor : graph.get(edge.node)) {
                    if(!visited[neighbor.node]) {
                        if (distances[neighbor.node] == -1) {
                            distances[neighbor.node] = edge.weight + neighbor.weight;
                            queue.add(new Edge(neighbor.node, distances[neighbor.node]));
                        } else {
                            int newCost = edge.weight + neighbor.weight;
                            if (newCost < distances[neighbor.node]) {
                                queue.add(new Edge(neighbor.node, newCost));
                                distances[neighbor.node] = newCost;
                            }
                        }
                    }
                }
            }
        }

        return distances;
    }

    public static int[][] floydWarshall(int[][] A) {

        for(int k=0; k<A.length; k++) {
            for(int i=0; i<A.length; i++) {
                // If Not self node
                 if(k != i) {
                    // Check all paths from
                    // i to j passing via middle node k
                    for(int j=0; j<A.length; j++) {
                        // If i to k is reachable and
                        // k to j is reachable
                        // and k to j is not a self node
                        if( k != j && A[i][k] !=-1 && A[k][j] != -1 ) {
                            int newCost = A[k][i] + A[j][k];
                            if(A[i][j] == -1 || newCost < A[i][j]) {
                                A[i][j] = newCost;
                            }
                        }
                    }
                 }
            }
        }

        return A;
    }

    public static void minMazeDistanceRecur(int[][] A, int currI, int currJ, int targetI, int targetJ, int dist) {

        if(currI < 0 || currJ < 0 || currI >= A.length || currJ >= A[0].length) {
            return;
        }



    }
    public static int minMazeDistance(int[][] A, int[][] B, int[][] C) {


        return 0;
    }

    public static ArrayList<Integer> maximumDepth(int A, ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D,
                                   ArrayList<Integer> E, ArrayList<Integer> F) {

        // Adjacency List
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0; i<B.size(); i++) {
            int node1 = B.get(i);
            int node2 = C.get(i);
            graph.putIfAbsent(node1, new ArrayList<>());
            graph.get(node1).add(node2);
            graph.putIfAbsent(node2, new ArrayList<>());
            graph.get(node2).add(node1);
        }

        for(Integer key : graph.keySet()) {
            System.out.println(key+" -> ");
            System.out.println(graph.get(key));
        }
        System.out.println();

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visitedSet = new HashSet<>();
        HashMap<Integer, List<Integer>> graphLevel = new HashMap<>();
        queue.add(1);
        queue.add(null);
        int level = 1;
        graphLevel.put(0, new ArrayList<>(Arrays.asList(D.get(0))));
        // BFS
        while(queue.peek()!=null){
            TreeSet<Integer> set = new TreeSet<>();
            while(queue.peek()!=null) {
                int node = queue.remove();
                if(!visitedSet.contains(node)) {
                    visitedSet.add(node);
                    List<Integer> children = graph.get(node);
                    for (Integer child : children) {
                        if(!visitedSet.contains(child)) {
                            set.add(D.get(child - 1));
                            queue.add(child);
                        }
                    }
                }
            }
            if(set.size()>0) {
                graphLevel.put(level, new ArrayList<>(set));
                queue.remove();
                queue.add(null);
                level++;
            }
            else{
                break;
            }
        }

        for(Integer key : graphLevel.keySet()) {
            System.out.print(key+" = ");
            for(Integer e : graphLevel.get(key))
                System.out.print(e+" ");
            System.out.println();
        }

        // Process Queries
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<E.size(); i++) {
            int currLevel = E.get(i)%level;
//            System.out.println("Curr level : "+currLevel);
            int threshold = F.get(i);
            List<Integer> edges = graphLevel.get(currLevel);
            // Maximum less than threshold
            if(edges.get(edges.size()-1) < threshold) {
                ans.add(-1);
            }
            // Minimum greater than threshold
            else if(edges.get(0) >= threshold) {
                ans.add(edges.get(0));
            }
            // Value in between
            else {
                // Binary search
                int l = 0, r = edges.size() - 1;
                int maxMin = edges.get(r);
                while (l <= r) {
                    int mid = (l + r) / 2;

                    // If value found
                    if(edges.get(mid) == threshold) {
                        maxMin = threshold;
                        break;
                    }
                    // Greater value
                    else if(edges.get(mid) > threshold) {
                        r = mid - 1;
                    }
                    // Less Value
                    else {
                        // If end
                        if(mid == edges.size()-1){
                            if(edges.get(mid-1) >= threshold) {
                                maxMin = edges.get(mid-1);
                            }
                            else{
                                maxMin = edges.get(mid);
                            }
                            break;
                        }
                        else {
                            if(edges.get(mid+1) >= threshold) {
                                maxMin = edges.get(mid+1);
                                break;
                            }
                            else{
                                l = mid+1;
                            }
                        }
                    }
                }
                ans.add(maxMin);
            }
        }

        return ans;
    }

    public static ArrayList<Integer> sheldonAndCities(int A, int B, int C, ArrayList<Integer> D,
                                                      ArrayList<Integer> E, ArrayList<Integer> F,
                                                      ArrayList<Integer> G, ArrayList<Integer> H) {

        int[][] graph = new int[A+1][A+1];
        for(int i=0; i<graph.length; i++) {
            for(int j=0; j<graph.length; j++) {
                graph[i][j] = i==j?0:-1;
            }
        }

        // Adjacency List
        for(int i=0; i<D.size(); i++) {
            int city1 = D.get(i);
            int city2 = E.get(i);

            if (city1 != city2) {
                if(graph[city1][city2] == -1) {
                    graph[city1][city2] = F.get(i);
                }
                else {
                    graph[city1][city2] = Math.min(graph[city1][city2], F.get(i));
                }
                graph[city2][city1] = graph[city1][city2];
            }

        }


        // Floyd Warshall
        for(int k=1; k<graph.length; k++ ){
            for(int i=1; i<graph.length; i++ ){
                // If not self node
                if(i!=k) {
                    // Check for all nodes [j][i] to [i][k] path
                    for (int j = 1; j<graph.length; j++) {
                        // If not self node
                        if( j!=k && graph[i][k] != -1 && graph[k][j] != -1) {
                            int newCost = graph[i][k] + graph[k][j];
                            if(graph[i][j] == -1) {
                                graph[i][j] = newCost;
                            }
                            else {
                                graph[i][j] = Math.min(graph[i][j], newCost);
                            }
                            graph[j][i] = graph[i][j];
                        }
                    }
                }
            }
        }

        for(int i=0; i<graph.length; i++) {
            for(int j=0; j<graph.length; j++) {
                System.out.print(graph[i][j]+ " ");
            }
            System.out.println();
        }

        // Process queries
        ArrayList<Integer> distances = new ArrayList<>();
        int[] idx = { 0 };
        G.stream().forEach(val -> {
            distances.add(graph [G.get(idx[0])][H.get(idx[0])]);
            idx[0]++;
        });
        return distances;
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
        /*System.out.println(knight(384,387,106,134,210,35));
        System.out.println(knight(173,237,46,81,9,235));*/

        // Edges in MST
        /*ArrayList<Integer> ans = edgeInMST(3, getSampleGraph(11));
        ans.forEach(integer -> {
            System.out.print(integer+" ");
        });*/

        // Nearest Exit in a Maze
        /*char[][] maze = new char[][]{{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}};
        System.out.println(nearestExit(maze, new int[]{1,2}));*/
        /*char[][] maze = new char[][]{{'+','+','+'},{'.','.','.'},{'+','+','+'}};
        System.out.println(nearestExit(maze, new int[]{1,0}));*/

        // Avoid Monsters
        /*ArrayList<Integer> monsterRows = new ArrayList<>();
        monsterRows.add(2);
        monsterRows.add(7);
        ArrayList<Integer> monsterCols = new ArrayList<>();
        monsterCols.add(7);
        monsterCols.add(5);
        System.out.println(avoidMonsters(8,8,6,6,
                            0,3,monsterRows,monsterCols));*/

        // Get all paths from source to target
        /*int[][] graph = new int[][]{{1,2}, {3}, {3}, {}};
        System.out.println(allPathsSourceTarget(graph));*/

        // Update 0-1 matrix - returns distance of closest 0 for every cell
        /*int[][] matrix = new int[][]{{0,0,0}, {0,1,0}, {1,1,1}};
        matrix = updateMatrix(matrix);
        Arrays.stream(matrix).forEach(arr -> {
            for(int i : arr) {
                System.out.print(i+" ");
            }
            System.out.println();
        });*/

        // Damaged roads minimum cost to connect cities
        /*System.out.println(damagedRoads(new ArrayList<>(Arrays.asList(2,2,4,1,2,4,2)),
                            new ArrayList<>(Arrays.asList(3,3,4,5)) ));*/

        // Djkstra's Algorithm
        /*int[][] graph = new int[][]{
                {0, 4, 9},
                {3, 4, 6},
                {1, 2, 1},
                {2, 5, 1},
                {2, 4, 5},
                {0, 3, 7},
                {0, 1, 1},
                {4, 5, 7},
                {0, 5, 1}
        };
        int[] distances = djkstras(6, graph, 4);
        Arrays.stream(distances).forEach(val -> {
            System.out.print(val+" ");
        });
        System.out.println();*/

        // Floyd Warshall's Algorithm - Shortest path from all nodes to all nodes
        /*int[][] distances = new int[][]{
                {0 , 3 , 2, -1},
                {-1 , 0 , -1, 1},
                {-1 , 1 , 0, 6},
                {1 , -1 , -1, 0}
        };
        distances = floydWarshall(distances);
        Arrays.stream(distances).forEach(arr ->{
            Arrays.stream(arr).forEach(val -> {
                System.out.print(val +" ");
            });
            System.out.println();
        });*/

        // Maximum Depth
        /*System.out.println(maximumDepth(5, new ArrayList<>(Arrays.asList(1, 4, 3, 1)),
                new ArrayList<>(Arrays.asList(5, 2, 4, 4)),
                new ArrayList<>(Arrays.asList(7, 38, 27, 37, 1)),
                new ArrayList<>(Arrays.asList(1, 1, 2)),
                new ArrayList<>(Arrays.asList(32, 18, 26))
        ));*/
        /*System.out.println(maximumDepth(3, new ArrayList<>(Arrays.asList(1, 2)),
                new ArrayList<>(Arrays.asList(3, 1)),
                new ArrayList<>(Arrays.asList(7, 15, 27)),
                new ArrayList<>(Arrays.asList(1, 10, 1)),
                new ArrayList<>(Arrays.asList(29, 6, 26))
        ));*/

        // Sheldon and pair of cities
        /*System.out.println(sheldonAndCities(4,6,2,
                new ArrayList<>(Arrays.asList(1, 2, 3, 2, 4, 3)),
                new ArrayList<>(Arrays.asList(2, 3, 4, 4, 1, 1)),
                new ArrayList<>(Arrays.asList(4, 1, 1, 1, 1, 1)),
                new ArrayList<>(Arrays.asList(1, 1)),
                new ArrayList<>(Arrays.asList(2, 3))
        ));*/
        System.out.println(sheldonAndCities(15,18,29,
                new ArrayList<>(Arrays.asList(11,2,2,6,2,8,9,3,14,15,4,14,8,7,8,6,2,12)),
                new ArrayList<>(Arrays.asList(2,1,1,2,1,1,7,3,2,13,2,1,6,1,7,1,2,10)),
                new ArrayList<>(Arrays.asList(8337,6651,29,7765,3428,5213,6431,2864,3137,4024,8169,5013,7375,3786,4326,6415,8982,6864)),
                new ArrayList<>(Arrays.asList(6,2,1,15,12,2,14,10,13,15,15,4,8,7,9,4,15,13,12,5,2,10,1,11,14,7,3,13,12)),
                new ArrayList<>(Arrays.asList(5,2,15,13,6,2,8,6,3,13,15,3,1,1,4,4,5,8,1,3,1,10,15,9,2,1,1,10,2))
        ));
    }
}
