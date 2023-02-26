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

    public static void reversePartLL(int start, int end){

    }

    public static void main(String[] args) {

    }

}
