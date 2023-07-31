package Tree;

import com.sun.source.tree.Tree;

import java.util.*;

public class TreeProblems {

    public static class Pair{
        TreeNode node;
        Integer level;

        public Pair(TreeNode node, Integer level) {
            this.node = node;
            this.level = level;
        }

    }
    public static HashMap<Integer, List<Integer>> verticalMap;
    public static HashMap<Integer, Integer> topViewMap;
    public static int minLevel, maxLevel;

    public static void VerticalOrderTraversal(TreeNode root, int level){

        Queue<Pair> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        q.add(new Pair(root, 0));
        while(!q.isEmpty()){
            ArrayList<Integer> tempList = new ArrayList<>();
            int i=q.size();
            while(i>0){
                Pair p = q.remove();
                if(verticalMap.containsKey(p.level))
                    verticalMap.get(p.level).add(p.node.val);
                else{
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(p.node.val);
                    verticalMap.put(p.level, temp);
                }

                if(p.level<minLevel)
                    minLevel = p.level;
                if(p.level>maxLevel)
                    maxLevel = p.level;

                if(p.node.left!=null)
                    q.add(new Pair(p.node.left, p.level-1));
                if(p.node.right!=null)
                    q.add(new Pair(p.node.right, p.level+1));
                i--;
            }
            ans.add(tempList);
        }

    }

    public static void TopView(TreeNode root, int level){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while(!q.isEmpty()){
            int i=q.size();
            while(i>0){
                Pair p = q.remove();
                if(!topViewMap.containsKey(p.level))
                    topViewMap.put(p.level, p.node.val);

                if(p.level<minLevel)
                    minLevel = p.level;
                if(p.level>maxLevel)
                    maxLevel = p.level;

                if(p.node.left!=null)
                    q.add(new Pair(p.node.left, p.level-1));
                if(p.node.right!=null)
                    q.add(new Pair(p.node.right, p.level+1));
                i--;
            }
        }
    }

    public static TreeNode BuildDemoTree(int type){

        TreeNode root = null;
        switch (type) {
            case 1:
                root = new TreeNode(0);
                root.left = new TreeNode(1);
                root.left.left = new TreeNode(5);
                root.left.right = new TreeNode(7);
                root.right = new TreeNode(10);
                root.right.left = new TreeNode(11);
                root.right.right = new TreeNode(20);
                break;

            // Path sum tree
            case 2:
                root = new TreeNode(10);
                root.left = new TreeNode(5);
                root.left.left = new TreeNode(3);
                root.left.left.left = new TreeNode(3);
                root.left.left.right = new TreeNode(-2);
                root.left.right = new TreeNode(2);
                root.left.right.right = new TreeNode(1);
                root.right = new TreeNode(-3);
                root.right.right = new TreeNode(11);
                break;

            case 3:
                root = new TreeNode(0);
                root.left = new TreeNode(1);
                root.right = new TreeNode(1);
                break;

            case 4:
                root = new TreeNode(0);
                root.left = new TreeNode(1);
                root.left.left = new TreeNode(0);
                root.left.right = new TreeNode(0);
                root.right = new TreeNode(1);
                root.right.left = new TreeNode(0);
                root.right.right = new TreeNode(0);
                break;

            case 5:
                root = new TreeNode(1);
                root.left = new TreeNode(7);
                root.left.left = new TreeNode(7);
                root.left.right = new TreeNode(-8);
                root.right = new TreeNode(0);
                break;
        }

        return root;
    }

    public static int pathSumIIITraverse(TreeNode root, int sum, int prevSum, HashSet<Integer> set){
        if(root == null) {
            return 0;
        }

        prevSum += root.val;
        int count = 0;
        if(set.contains(prevSum - sum)){
            count += 1;
        }
        if(root.val == sum) {
            count++;
        }
//        System.out.println(set);
//        System.out.println(root.val);
        set.add(prevSum);
        count += pathSumIIITraverse(root.left, sum, prevSum, set);
        count += pathSumIIITraverse(root.right, sum, prevSum, set);
        set.remove(prevSum);
        return count;
    }

    // Count all unique paths starting from any node going only in bottom direction
    // That sum to the given value
    public static int pathSumIII(TreeNode root, int sum) {
        HashSet<Integer> set = new HashSet<>();
//        set.add(0);
        return pathSumIIITraverse(root, sum, 0, set);
    }

    public static int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int max = Integer.MIN_VALUE;
        int maxLevel = 0;
        int currLevel = 0;
        while(queue.peek()!=null) {
            int tempMax = 0;
            currLevel++;
            while(queue.peek()!=null) {
                TreeNode node = queue.remove();
                tempMax += node.val;
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
            queue.remove();
            queue.add(null);
            if(tempMax>max) {
                max = tempMax;
                maxLevel = currLevel;
            }
        }

        return maxLevel;
    }

    public static void main(String[] args) {


        //Get vertical order traversal
        /*TreeNode root = BuildDemoTree(1);
        verticalMap = new HashMap<>();
        minLevel = 0;
        maxLevel = 0;
        VerticalOrderTraversal(root,minLevel);
        System.out.println(verticalMap);
        for(int i=minLevel; i<=maxLevel; i++){
            System.out.println(verticalMap.get(i));
        }*/

        //Get top view of binary tree
        /*TreeNode root = BuildDemoTree(1);
        topViewMap = new HashMap<>();
        minLevel = 0;
        maxLevel = 0;
        TopView(root,minLevel);
        System.out.println(topViewMap);
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=minLevel; i<=maxLevel; i++)
            ans.add(topViewMap.get(i));
        System.out.println(ans);*/

        //Path Sum III
        TreeNode root = BuildDemoTree(3);
        System.out.println(pathSumIII(BuildDemoTree(4), 1));
        System.out.println(pathSumIII(root, 0));

        // Max level sum
        /*System.out.println(maxLevelSum(BuildDemoTree(5)));*/
    }

}
