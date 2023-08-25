package LinkedList;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LinkedList {

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

        printList(head);

        int index = 0;
        Node temp = head, prev=null;
        while(index < start && head !=null) {
            prev = temp;
            temp = temp.next;
            index++;
        }
        int len = end-start;
        Node n1 = temp, n2 = temp.next;
        while(n2!=null &&  len>0) {
            Node n3 = n2.next;
            n2.next = n1;
            n1=n2;
            n2 = n3;
            len--;
        }

        if(prev!=null) {
            prev.next = n1;
        }
        else {
            head = n1;
        }

        temp.next = n2;
        return head;
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

            default:
                n = new Node(null);
                break;
        }

        return n;
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
        printList(reversePartList(getSampleLinkedList(1), 3,4));
    }

}
