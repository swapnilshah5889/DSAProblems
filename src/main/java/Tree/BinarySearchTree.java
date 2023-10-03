package Tree;
import Tree.TreeNode;
import com.sun.source.tree.Tree;

import java.util.Arrays;
import java.util.List;

public class    BinarySearchTree {

    public static void BSTInsertNode(TreeNode root, TreeNode child, int val){
        if(child==null) {
            if (val <= root.val)
                root.left = new TreeNode(val);
            else
                root.right = new TreeNode(val);
        }
        else{
            if (val <= child.val)
                BSTInsertNode(child, child.left, val);
            else
                BSTInsertNode(child, child.right, val);
        }
    }

    public static void InorderTraversal(TreeNode root){
        if(root == null)
            return;
        InorderTraversal(root.left);
        System.out.print(root.val + " ");
        InorderTraversal(root.right);
    }

    public static void BuildBSTTree(List<Integer> data){
        TreeNode root = new TreeNode(data.get(0));
        for(int i=1; i<data.size(); i++){
            if(data.get(i) <= root.val)
                BSTInsertNode(root, root.left, data.get(i));
            else
                BSTInsertNode(root, root.right, data.get(i));
        }

        InorderTraversal(root);
    }

    public static int divisorSubstrings(int num, int k) {
        StringBuilder st = new StringBuilder(String.valueOf(num));
        int count = 0;
        StringBuilder st1 = new StringBuilder();
        for(int i=0; i<k-1; i++){
            st1.append(st.charAt(i));
        }

        for(int i=k-1; i<st.length(); i++){
            st1.append(st.charAt(i));
            int val = Integer.parseInt(st1.toString());
            if(num%val == 0)
                count++;
            st1.deleteCharAt(0);
        }

        return count;
    }

    public static void main(String[] args) {

        //Build BST Tree
        /*List<Integer> data = Arrays.asList(20,1,2,60,54,23,5,8,7,4,27,32);
        BuildBSTTree(data);*/

        System.out.println(divisorSubstrings(240,2));
    }
}
