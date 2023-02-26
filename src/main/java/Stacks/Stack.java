package Stacks;

public class Stack {

    private class Node{

        int val;
        Node next;
        Node(int val){
            this.val = val;
            next = null;
        }
    }

    Node head;
    int currMin = Integer.MAX_VALUE;
    Stack(){
        head = new Node(-1);
    }

    protected void push(int val){
        Node temp = new Node(val);
        if(head.val==-1){
            currMin = val;
        }
        temp.next = head;
        head = temp;
        if(val<currMin){
            int encodedVal = 2*val - currMin;
            temp.val = encodedVal;
            currMin = val;
        }
    }

    protected int getMin(){
        if(head.val!=-1) {
            return currMin;
        }
        else
            return Integer.MIN_VALUE;
    }

    protected int pop(){
        if(head.val!=-1){
            Node temp = head;
            head = head.next;
            temp.next = null;
            if(temp.val<currMin){
                int popVal = currMin;
                currMin = 2*temp.val - currMin;
                return popVal;
            }
            else
                return temp.val;
        }
        return Integer.MIN_VALUE;
    }

    protected int peek(){
        if(head.val!=-1){
            return head.val;
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public String toString(){
        if(head.val!=-1){
            Node temp = head;
            StringBuilder st = new StringBuilder();
            while(temp.val!=-1){
                st.append(temp.val);
                st.append(" ");
                temp = temp.next;
            }
            return st.toString();
        }
        return null;
    }
}
