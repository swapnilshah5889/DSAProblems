package Tries;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    Character data;
    int val;
    int index;
    HashMap<Character, TreeNode> children;
    HashMap<Integer, TreeNode> intChildren;
    boolean isEnd;
    int childCount;
    TreeNode(Character c){
        this.data = c;
        this.children = new HashMap<>();
        this.isEnd = false;
        this.childCount=0;
    }

    TreeNode(int val) {
        this.val = val;
        this.intChildren = new HashMap<>();
    }

    public void PrintSubTree(){
        PrintChildren(this);
    }
    private void PrintChildren(TreeNode root){
        if(root==null)
            return;
        if(root.children.size()==0)
            System.out.println(root.data+" : No children\n");
        else {
            if(root.data!=null)
                System.out.print("Children of " + root.data+" : ");
            else
                System.out.print("Children of Root Node : ");
            for (Character c : root.children.keySet()) {
                System.out.print(root.children.get(c).data + "  ");
            }
            System.out.println();
            System.out.println("Total Words : "+root.childCount);
            for(Character c : root.children.keySet())
                PrintChildren(root.children.get(c));
        }

    }
    public void PrintIntSubTree(){
        PrintIntChildren(this);
    }

    private void PrintIntChildren(TreeNode root){
        if(root==null)
            return;
        if(root.intChildren.size()==0)
            System.out.println(root.data+" : No intChildren\n");
        else {
            if(root.data!=null)
                System.out.print("Children of " + root.data+" : ");
            else
                System.out.print("Children of Root Node : ");
            for (Integer c : root.intChildren.keySet()) {
                System.out.print(root.intChildren.get(c).data + "  ");
            }
            System.out.println();
            System.out.println("Total Words : "+root.childCount);
            for(Integer c : root.intChildren.keySet())
                PrintIntChildren(root.intChildren.get(c));
        }

    }

}
