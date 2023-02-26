package Tries;

import com.sun.source.tree.Tree;

import java.util.*;

public class TrieProblems {

    public static ArrayList<Integer> SpellingChecker(List<String> A, List<String> B) {
        TreeNode root = new TreeNode(null);
        for (int i = 0; i < A.size(); i++) {
            String s = A.get(i);
            TreeNode temp = root;
            root.childCount++;
            for (int j = 0; j < s.length(); j++) {
                Character c = s.charAt(j);
                //Contains character
                if (temp.children.containsKey(c)) {
                    temp = temp.children.get(c);
                }
                //Does not contain the character
                else {
                    temp.children.put(c, new TreeNode(c));
                    temp = temp.children.get(c);
                }
                temp.childCount++;
            }
            temp.isEnd = true;
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            String s = B.get(i);
            TreeNode temp = root;
            boolean found = true;
            for (int j = 0; j < s.length(); j++) {
                Character c = s.charAt(j);
                //Contains character
                if (temp.children.containsKey(c)) {
                    temp = temp.children.get(c);
                }
                //Does not contain the character
                else {
                    found = false;
                    break;
                }
            }
            if (found && temp.isEnd)
                ans.add(1);
            else
                ans.add(0);
        }

        return ans;
    }

    public static void printTree(TreeNode root){
        if(root.data==null)
            System.out.println("Root\n");
        else {
            System.out.println("Node : "+root.data);
            if(root.children.size()==0)
                System.out.println("No children\n");
            else {
                System.out.println("Children of : " + root.data);
                for (Character c : root.children.keySet()) {
                    System.out.print(root.children.get(c).data + "  ");
                }
                System.out.println();
            }

        }
        if(root.children!=null)
            for(Character c : root.children.keySet())
                printTree(root.children.get(c));
    }

    public static ArrayList<String> ShortestUniquePrefix(List<String> A){
        TreeNode root = new TreeNode(null);
        for(int i=0;i<A.size(); i++){
            String s = A.get(i);
            TreeNode temp = root;
            root.childCount++;
            for(int j=0;j<s.length(); j++){
                Character c = s.charAt(j);
                //Contains character
                if (temp.children.containsKey(c)) {
                    temp = temp.children.get(c);
                }
                //Does not contain the character
                else {
                    temp.children.put(c, new TreeNode(c));
                    temp = temp.children.get(c);
                }
                temp.childCount++;
            }
            temp.isEnd = true;
        }

        ArrayList<String> ans = new ArrayList<>();
        for(int i=0;i<A.size(); i++) {
            String s = A.get(i);
            TreeNode temp = root;
            StringBuilder st = new StringBuilder();
            for(int j=0;j<s.length(); j++){
                Character c = s.charAt(j);
                if(temp.childCount>1)
                    temp = temp.children.get(c);
                else
                    break;
                st.append(c);
            }
            ans.add(st.toString());
        }

        return ans;
    }

    private static void buildTrie(TreeNode root, List<List<Integer>> binaryList) {

        for (int i=0; i<binaryList.size(); i++) {
            List<Integer> binary = binaryList.get(i);
            TreeNode curr = root;
            for(int j=0; j<binary.size(); j++) {
                int val = binary.get(j);
                if (!curr.intChildren.containsKey(val)) {
                    TreeNode node = new TreeNode(val);
                    curr.intChildren.put(val, node);
                }
                curr = curr.intChildren.get(val);
                if(j == binary.size()-1) {
                    curr.index = i;
                }
            }
        }

    }

    private static int findComplementValue(TreeNode root, List<Integer> binary) {
        int val = 0;
        TreeNode curr = root;
        int size = binary.size()-1;
        for(int i=0; i<binary.size(); i++) {
            int b = binary.get(i)==1?0:1;

            //If children contains complement binary value
            if(!curr.intChildren.containsKey(b)) {
                b = (b==1?0:1);
            }
            curr = curr.intChildren.get(b);
            //If next value is 1 then add the value to total
            if(b==1)
                val += Math.pow(2, size);
            size--;
        }
        return curr.index; //Use for maximum sub-array xor value
//        return val; //Use for maximum xor value function
    }

    public static int maximumXOR(List<Integer> A) {

        TreeNode root = new TreeNode(-1);
        List<List<Integer>> binaryList = new ArrayList<>();
        int maxLen=0;
        for (int i=0; i<A.size(); i++) {
            int val = A.get(i);
            List<Integer> binary = new ArrayList<>();
            int len=0;
            while(val>0) {
                len++;
                int rem = val%2;
                binary.add(0,rem);
                val /=2;
            }
            maxLen = Math.max(len,maxLen);
            binaryList.add(binary);
        }

        for (int i=0; i<binaryList.size(); i++) {
            if (binaryList.get(i).size()<maxLen) {
                int diff = maxLen - binaryList.get(i).size();
                for(int j=0; j<diff; j++)
                    binaryList.get(i).add(0,0);

            }
        }

        buildTrie(root, binaryList);

        int ans = 0;
        for (int i=0; i<binaryList.size(); i++) {
            int val = A.get(i);
            int compVal = findComplementValue(root, binaryList.get(i));
            /*System.out.println(binaryList.get(i));
            System.out.println(val+ " | "+compVal);
            System.out.println();*/
            ans = Math.max(ans, (val^compVal));
        }

        return ans;
    }

    public static List<Integer> maxSubarrayXOR(List<Integer> B) {
        TreeNode root = new TreeNode(-1);
        List<Integer> A = new ArrayList<>();
        A.add(B.get(0));

        int ans = B.get(0);
        int start=0, end=0;
        //Build XOR Array
        for(int i=1; i<B.size(); i++) {
            A.add( B.get(i)^A.get(i-1));
            if(ans<B.get(i)) {
                start = i;
                end = start;
                ans = B.get(i);
            }
        }
        System.out.println(A);
        List<List<Integer>> binaryList = new ArrayList<>();
        int maxLen=0;
        for (int i=0; i<A.size(); i++) {
            int val = A.get(i);
            List<Integer> binary = new ArrayList<>();
            int len=0;
            while(val>0) {
                len++;
                int rem = val%2;
                binary.add(0,rem);
                val /=2;
            }
            maxLen = Math.max(len,maxLen);
            binaryList.add(binary);
        }

        for (int i=0; i<binaryList.size(); i++) {
            if (binaryList.get(i).size()<maxLen) {
                int diff = maxLen - binaryList.get(i).size();
                for(int j=0; j<diff; j++)
                    binaryList.get(i).add(0,0);

            }
        }

        buildTrie(root, binaryList);

        boolean xored = false;
        for (int i=0; i<binaryList.size(); i++) {
            int val = A.get(i);
            int compInd = findComplementValue(root, binaryList.get(i));
            System.out.println(binaryList.get(i));
            System.out.println(val+ " | "+A.get(compInd));
            int xoredVal = val^A.get(compInd);
            System.out.println("XORED Val: "+xoredVal);
            if(ans<xoredVal || (ans == xoredVal && Math.abs(i-compInd)<Math.abs(start-end)) ) {
                start = i;
                end = compInd;
                ans = xoredVal;
                xored = true;
            }

            if(ans<A.get(i) || (ans == A.get(i) && Math.abs(i-compInd)<Math.abs(start-end))) {
                start = 0;
                end = i;
                ans = A.get(i);
                xored = false;
            }
            System.out.println(start + " | " + end);
            System.out.println();
        }
        if(start>end) {
            int temp = start;
            start = end;
            end = temp;
        }
        System.out.println(start + " <> " + end);
        start+=1;
        end+=1;
        if(start<end && xored)
            start+=1;
        return Arrays.asList(start, end);
    }

    public static void main(String[] args) {
        //Check if word is in dictionary or not
        /*System.out.println(SpellingChecker(Arrays.asList("bar","bat","car","cat"), Arrays.asList("bar","ba")));*/

        //Find shortest unique prefix
        /*ArrayList<String> temp = ShortestUniquePrefix(Arrays.asList("bar", "bat", "blast","bump", "abort", "aborted", "aborting","abase", "apple", "away", "able", "align"));
        System.out.println(temp);*/

        //Find Max XOR Value
        /*System.out.println(maximumXOR(Arrays.asList(1, 2, 3, 4, 5)));*/

        //Find Maximum XOR sub array
        /*System.out.println(maxSubarrayXOR(Arrays.asList(37, 24, 17, 26, 37, 10, 15, 35, 7, 33)));*/
    }

}
