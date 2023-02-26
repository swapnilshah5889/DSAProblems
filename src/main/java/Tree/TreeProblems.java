package Tree;

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

    public static TreeNode BuildDemoTree(){

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(20);

        return root;
    }

    public static void main(String[] args) {


        //Get vertical order traversal
        /*TreeNode root = BuildDemoTree();
        verticalMap = new HashMap<>();
        minLevel = 0;
        maxLevel = 0;
        VerticalOrderTraversal(root,minLevel);
        System.out.println(verticalMap);
        for(int i=minLevel; i<=maxLevel; i++){
            System.out.println(verticalMap.get(i));
        }*/

        //Get top view of binary tree
        /*TreeNode root = BuildDemoTree();
        topViewMap = new HashMap<>();
        minLevel = 0;
        maxLevel = 0;
        TopView(root,minLevel);
        System.out.println(topViewMap);
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=minLevel; i<=maxLevel; i++)
            ans.add(topViewMap.get(i));
        System.out.println(ans);*/

    }

}
