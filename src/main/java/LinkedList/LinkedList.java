package LinkedList;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class LinkedList {

    static class ListNode {
        int val;
        ListNode right, down;
        ListNode(int x) {
            val = x;
            right = down = null;
        }
    }

    public static Node head = null;
    public static int size=0;

    public static void insert_node(int position, int value) {
        // @params position, integer
        // @params value, integer
        Node n = new Node(value);
        if(position==1 && head==null){
            head = n;
            size++;
        }
        else if(head!=null){
            if(position==1){
                n.next = head;
                head=n;
                size++;
            }
            else if(position<=(size+1) && position>0){
                Node temp = head;
                int i=1;
                while(i<(position-1)){
                    temp = temp.next;
                    i++;
                }
                n.next = temp.next;
                temp.next = n;
                size++;
            }
        }
    }

    public static void delete_node(int position) {
        // @params position, integer
        if(position<=size && position>0){
            if(position==1){
                Node temp = head;
                head = head.next;
                temp.next = null;
                size--;
            }
            else{
                Node temp = head;
                int i=1;
                while(i<(position-1)){
                    temp = temp.next;
                    i++;
                }
                temp.next = temp.next.next;
                size--;
            }

            if(size==0)
                head=null;
        }
    }

    public static void print_ll() {
        // Output each element followed by a space

        Node temp = head;
        while(temp!=null){
            System.out.print(temp.val+" ");
            temp=temp.next;
        }
        System.out.println();
    }

    public static void reverse_ll(){
        Node mid = head;
        Node right = head.next;
        Node left = null;
        while (mid != null) {
            mid.next = left;
            left = mid;
            mid = right;
            if(right!=null)
                right = right.next;
        }
        head = left;
    }

    public static void printList(Node A) {
        while(A!=null) {
            System.out.print(A.val);
            A = A.next;
            if(A!=null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static Node reverseList(Node A) {
        Node prev = null, curr = A, next = A.next;
        while(curr!=null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            if(next!=null)
                next = next.next;
        }
        return prev;
    }

    public static Node reorderList(Node A) {
        Node fast=A, slow=A, prev=null;
        while(fast != null && fast.next!=null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        Node n2 = reverseList(slow);
        Node n1 = A;
        while(n1!=null && n2!=null) {
            Node temp1 = n1;
            Node temp2 = n2;
            n1 = n1.next;
            n2 = n2.next;
            temp1.next = temp2;
            temp2.next = n1;
        }
        if(n2!=null) {
            n1=A;
            while(n1.next!=null) {
                n1 = n1.next;
            }
            n1.next = n2;
        }

        return A;
    }

    private static Node reversePartList(Node head, int start, int end) {
        if(head == null || head.next == null) {
            return head;
        }

        int index = 0;
        Node temp = head, prev=null;
        while(index < start && temp !=null) {
            prev = temp;
            temp = temp.next;
            index++;
        }
        if(temp!=null) {
            int len = end - start;
            Node n1 = temp, n2 = temp.next;
            while (n2 != null && len > 0) {
                Node n3 = n2.next;
                n2.next = n1;
                n1 = n2;
                n2 = n3;
                len--;
            }

            if (prev != null) {
                prev.next = n1;
            } else {
                head = n1;
            }

            temp.next = n2;
            return head;
        }
        else {
            return null;
        }
    }

    public static Node getSampleLinkedList(int type) {
        Node n;
        switch (type){
            case 1:
                n = new Node(2);
                n.next = new Node(3);
                n.next.next = new Node(4);
                n.next.next.next = new Node(5);
                n.next.next.next.next = new Node(6);
            break;

            case 2:
                n = new Node(2);
                n.next = new Node(3);
                n.next.next = new Node(4);
                n.next.next.next = new Node(3);
                n.next.next.next.next = new Node(2);
                break;

            case 3:
                n = new Node(2);
                n.next = new Node(3);
                n.next.next = new Node(4);
                n.next.next.next = new Node(4);
                n.next.next.next.next = new Node(3);
                n.next.next.next.next.next = new Node(2);
                break;

            default:
                n = new Node(null);
                break;
        }

        return n;
    }

    public static Node getLLFromArray(int[] arr) {
        Node head = new Node(arr[0]);
        Node temp = head;
        for(int i=1; i<arr.length; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head;
    }

    public static ListNode getLLFromArrayII(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode temp = head;
        for(int i=1; i<arr.length; i++) {
            temp.right = new ListNode(arr[i]);
            temp = temp.right;
        }
        return head;
    }

    public static boolean palindromeList(Node head) {
        if(head == null)
            return false;
        if(head.next == null) {
            return true;
        }

        Node slow = head, fast = head.next;
        int index = 1;
        while(fast != null && fast.next != null) {
            index++;
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = reverseList(slow.next);
        Node n1=head, n2 = slow.next;
        while(n1!=null && n2!=null) {
            if(n1.val != n2.val) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }

    // Longest palindrome using stack or reversing mid linkedlist
    private static int longestPalindrome(Node head) {
        if(head == null)
            return 0;
        if(head.next == null)
            return 1;

        Node i = head;
        int max = 1;
        while(i!=null) {
            Node j=i.next;
            int len = 2;
            while(j!=null) {
                if(checkPartPalindromeII(i, len)) {
                    max = Math.max(max, len);
                }
                j = j.next;
                len++;
            }
            i = i.next;
        }

        return max;
    }

    // By reversing mid linked list
    private static boolean checkPartPalindrome(Node node, int len) {
        Node m = node;
        int mid = (int) Math.ceil((float)len/2)-1;
        while(m!=null && mid>0){
            mid--;
            m = m.next;
        }
        Node listMid = m;
        listMid.next = reversePartList(listMid.next, 0,(len/2)-1);
        m = node;
        Node m2 = listMid.next;
        int len1 = (len/2);
        while(len1>0) {
            if(m.val != m2.val) {
                listMid.next = reversePartList(listMid.next, 0,(len/2)-1);
                return false;
            }
            len1--;
            m = m.next;
            m2 = m2.next;
        }

        listMid.next = reversePartList(listMid.next, 0,(len/2)-1);
        return true;
    }

    // Using stack
    private static boolean checkPartPalindromeII(Node node, int len) {
        Node m = node;
        Stack<Integer> stack = new Stack<>();
//        stack.push(node.val);
        int mid = (int) Math.ceil((float)len/2)-1;
        while(m!=null && mid>0){
            mid--;
            stack.push(m.val);
            m = m.next;
        }
        if(len%2==0) {
            stack.push(m.val);
        }
        System.out.println(stack);
        Node listMid = m.next;
        int len1 = (len/2);
        while(!stack.isEmpty() && listMid != null && len1>0) {
            if(stack.pop() != listMid.val) {
                return false;
            }
            listMid = listMid.next;
            len1--;
        }
        return true;
    }

    private static Node getReversedLinkedList(Node head) {
        Node temp = head;
        Node prev = null;
        while(temp!=null) {
            Node tail = new Node(temp.val);
            tail.next = prev;
            prev = tail;
            temp=temp.next;
        }

        return prev;
    }

    public static int getLongestPalindrome(Node a, Node b) {
        Node n1 = a;
        Node n2 = b;
        // Even Palindrome
        int len=0;
        while(n1 != null && n2 != null) {
            if(n1.val != n2.val) {
                break;
            }
            len++;
            n1 = n1.next;
            n2 = n2.next;
        }
        len = len*2;

        int len1 = 0;
        // Odd length palindrome
        if(a!=null && a.next!=null) {
            n1 = a.next;
            n2 = b;
            while (n1 != null && n2 != null) {
                if (n1.val != n2.val) {
                    break;
                }
                len1++;
                n1 = n1.next;
                n2 = n2.next;
            }
            len1 = (len1*2) + 1;
        }

        return Math.max(len1, len);
    }

    private static int longestPalindromeII(Node head) {
        if(head == null)
            return 0;
        if(head.next == null)
            return 1;

        Node n1 = head;
        int i=0;
        Node rHead = null;
        int max = 0;
        while(n1!=null) {
            Node n2 = n1;
            n1 = n1.next;
            n2.next = rHead;
            rHead = n2;
            /*printList(n1);
            printList(rHead);
            System.out.println("Longest: "+getLongestPalindrome(n1,rHead));
            System.out.println();*/
            max = Math.max(getLongestPalindrome(n1,rHead), max);
        }

        return max;
    }

    private static ListNode mergeLists(ListNode a, ListNode b) {
        if(a.val>b.val){
            return mergeLists(b,a);
        }
        ListNode prev=a, next=a.right, n2=b;
        while(next!=null && n2!=null) {
            // B value in between
            if(n2.val >= prev.val && n2.val<=next.val) {
                ListNode temp = n2;
                n2 = n2.right;
                prev.right = temp;
                temp.right = next;
                prev = temp;

            }
            else {
                prev = next;
                next = next.right;
            }
        }
        if(next==null) {
            prev.right = n2;
        }

        return a;

    }

    private static ListNode downToFlatList(ListNode n) {
        ListNode head = n;
        while(n!=null) {
            ListNode temp = n.down;
            n.down = null;
            n.right = temp;
            n = n.right;
        }
        return head;
    }

    private static ListNode flattenList(ListNode root) {

        ListNode n1 = root;
        while(n1!=null) {
            if(n1.down!=null) {
                ListNode start = root;
                ListNode n2 = downToFlatList(n1.down);
                n1.down = null;
                root = mergeLists(start, n2);
            }
            n1 = n1.right;
        }

        return root;
    }

    public static ListNode merge(ListNode a, ListNode b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        ListNode result;
        if(a.val <= b.val) {
            result = a;
            result.right = merge(a.right, b);
        }
        else {
            result = b;
            result.right = merge(a, b.right);
        }
        return result;
    }

    public static ListNode flatten(ListNode root) {
        if (root == null)
            return root;
        root.right = flatten(root.right);
        ListNode downFlat = flatten(root.down);
        root.down=null;
        return merge(root, downFlat);
    }

    private static Node reverseListKGroups(Node head,int k) {
        Node prev = head;

        Node newHead = head;
        int index = 0;
        while(prev!=null) {

            int j = k;
            while(prev!=null && j>0) {
                prev = prev.next;
                j--;
            }
            if(prev==null && j>0){
                break;
            }

            if(newHead==head){
                newHead=reversePartList(head, index, index+k-1);
            }
            else {
                reversePartList(newHead, index, index+k-1);
            }
            index+=k;

        }
        return newHead;
    }

    private static Node swapNodes(Node head, int k) {
        if(head == null || head.next == null || k==0) {
            return head;
        }
        System.out.println("Position: "+k);
        int len = 0;
        Node n = head;
        while(n!=null) {
            n=n.next;
            len++;
        }
        k=k%len;
        if(k<=0){
            k+=len;
        }
        k--;

        Node fPrev=null, fCurr=head;
        int first = k;
        while(first>0) {
            fPrev = fCurr;
            fCurr = fCurr.next;
            first--;
        }
        int second = len-k-1;

        Node lPrev=null, lCurr=head;
        while(second>0) {
            lPrev = lCurr;
            lCurr = lCurr.next;
            second--;
        }

        int temp = lCurr.val;
        lCurr.val = fCurr.val;
        fCurr.val = temp;

        /*if(fCurr.next == lCurr) {
            Node lNext = lCurr.next;
            fPrev.next = lCurr;
            lCurr.next = fCurr;
            fCurr.next = lNext;
        }
        else if(lCurr.next == fCurr) {
            Node fNext = fCurr.next;
            lPrev.next = fCurr;
            fCurr.next = lCurr;
            lCurr.next = fNext;
        }
        else if(fPrev!=null && lPrev!=null) {
            Node fNext = fCurr.next;
            Node lNext = lCurr.next;

            fPrev.next = lCurr;
            lCurr.next = fNext;

            lPrev.next = fCurr;
            fCurr.next = lNext;
        }

        else {
            Node fNext = fCurr.next;
            Node lNext = lCurr.next;
            fCurr.next = lNext;
            lCurr.next = fNext;
            // First index
            if(lPrev!=null) {
                lPrev.next = fCurr;
                head = lCurr;
            }
            else {
                fPrev.next = lCurr;
                head = fCurr;
            }
        }*/

        System.out.println(fCurr.val);
        System.out.println(lCurr.val);
        return head;

    }

    private static void LRUCache() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    public static void main(String[] args) {

        // Reorder list
        /*Node n = new Node(1);
        n.next = new Node(2);
        n.next.next = new Node(3);
        n.next.next.next = new Node(4);
        n.next.next.next.next = new Node(5);
        n.next.next.next.next.next = new Node(6);
        n = reorderList(n);
        printList(n);*/

        // Reverse Part List
        /*printList(reversePartList(getSampleLinkedList(1), 3,4));*/

        // Palindrome List
        /*Node head = getLLFromArray(new int[]{1,1,2,3,2,1,1});
        System.out.println(palindromeList(head));*/

        // Longest Palindrome - Not optimized
        // Method 1: reverse subsets from its mid and then check palindrome (method: checkPartPalindrome)
        // Method 2: using stack, time optimized but space O(n) (method: checkPartPalindromeII)
        /*Node head = getLLFromArray(new int[]{1,2,2,4,1,3,1,3,1,3,2,6});
        System.out.println(longestPalindrome(head));*/

        // Longest Palindrome - Optimized and TC:O(N^2) SC:O(1)
        /*System.out.println(longestPalindromeII(getLLFromArray(new int[]{1,2,3,3,2,1})));*/

        // Flatten Linked List
        /*ListNode head = getLLFromArrayII(new int[]{3,4});
        head.down = new ListNode(7);
        head.down.right = new ListNode(9);
        head.down.down = new ListNode(7);
        head.down.down.right = new ListNode(10);
        head.down.down.down = new ListNode(8);
        head.right.down = new ListNode(5);
        head.right.down.right = new ListNode(6);
        head.right.down.down = new ListNode(7);
        head.right.down.down.down = new ListNode(11);
        head.right.down.down.down.down = new ListNode(15);
//        head.right.right.right.right.down = getLLFromArrayII(new int[]{31,39});
        ListNode flatNode = flatten(head);
        while(flatNode!=null) {
            System.out.print(flatNode.val);
            if(flatNode.right!=null) {
                System.out.print("->");
            }
            flatNode = flatNode.right;
        }
        System.out.println();*/

        // Reverse list in k groups
        /*Node head = getLLFromArray(new int[]{1,2,3,4,5,6,7,8,9});
        printList(reverseListKGroups(head,4));*/

        // Swap k nodes
//        printList(swapNodes(getLLFromArray(new int[]{1,2,3,4,5,6,7}), 0));
//        printList(swapNodes(getLLFromArray(new int[]{1,2,3,4,5,6,7}), 1));
//        printList(swapNodes(getLLFromArray(new int[]{1,2,3,4,5,6,7}), 2));
//        printList(swapNodes(getLLFromArray(new int[]{1,2,3,4,5,6,7}), 3));
//        printList(swapNodes(getLLFromArray(new int[]{1,2,3,4,5,6,7}), 4));
//        printList(swapNodes(getLLFromArray(new int[]{1,2,3,4,5,6,7}), 5));
//        printList(swapNodes(getLLFromArray(new int[]{1,2,3,4,5,6,7}), 6));
//        printList(swapNodes(getLLFromArray(new int[]{7,9,6,6,7,8,3,0,9,5}), 6));
//        printList(swapNodes(getLLFromArray(new int[]{1,2,3,4,5,6,7}), 8));
//        printList(swapNodes(getLLFromArray(new int[]{1,2,3,4,5,6,7}), 9));
//        printList(swapNodes(getLLFromArray(new int[]{1,2,3,4,5,6,7}), 10));

        // LRU Cache
        LRUCache();

    }

}
