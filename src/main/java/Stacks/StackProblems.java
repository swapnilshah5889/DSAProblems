package Stacks;

import java.net.Inet4Address;
import java.sql.Array;
import java.sql.Statement;
import java.util.*;
import java.util.Stack;

public class StackProblems {

    public static boolean balancedParanthesis(String s){
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            switch (c){
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                    if(stack.isEmpty())
                        return false;
                    else if(stack.pop().equals('('))
                        continue;
                    else
                        return false;
                case ']':
                    if(stack.isEmpty())
                        return false;
                    else if(stack.pop().equals('['))
                        continue;
                    else
                        return false;
                case '}':
                    if(stack.isEmpty())
                        return false;
                    else if(stack.pop().equals('{'))
                        continue;
                    else
                        return false;
                default:
                    return false;
            }
        }


        return stack.empty();
    }

    public static int evaluateExpression(List<String> A){

        Stack<Integer> dataStack = new Stack<>();
        dataStack.push(Integer.parseInt(A.get(0)));
        int ans, val1, val2;
        for(int i=1; i<A.size(); i++){
            String s = A.get(i);
            switch (s){
                case "+":
                    ans = dataStack.pop()+dataStack.pop();
                    dataStack.push(ans);
                    break;
                case "-":
                    val1 = dataStack.pop();
                    val2 = dataStack.pop();
                    ans = val2-val1;
                    dataStack.push(ans);
                    break;
                case "*":
                    ans = dataStack.pop()*dataStack.pop();
                    dataStack.push(ans);
                    break;
                case "/":
                    val1 = dataStack.pop();
                    val2 = dataStack.pop();
                    ans = val2/val1;
                    dataStack.push(ans);
                    break;
                default:
                    dataStack.push(Integer.parseInt(s));
                    break;
            }

        }

        return dataStack.pop();
    }

    public static boolean checkRedundantBraces(String s){

        Stack<Character> dataStack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == ')'){
                char temp = dataStack.pop();
                if(temp == '(')
                    return true;
                else{
                    boolean operationFound = false;
                    while(temp!='('){
                        if(temp=='+' || temp=='-'||temp=='*'||temp=='/')
                            operationFound=true;
                        temp = dataStack.pop();
                    }
                    if(!operationFound)
                        return true;
                }
            }
            else{
                dataStack.push(c);
            }
        }

        return false;
    }

    public static String doubleCharacterTrouble(String s){
        Stack<Character> dataStack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(!dataStack.isEmpty() && dataStack.peek().equals(c)){
                dataStack.pop();
            }
            else{
                dataStack.push(c);
            }
        }

        StringBuilder st = new StringBuilder();
        while(!dataStack.isEmpty()){
            st.insert(0,dataStack.pop());
        }
        return st.toString();
    }

    //Open all brackets in an equation
    public static String openExpressionBrackets(String s){
        Stack<Character> dataStack = new Stack<>();
        StringBuilder st = new StringBuilder();
        boolean isMinus = false, isBracket = true;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '-'){
                isMinus = true;
                if(!isBracket) {
                    if (dataStack.size() % 2 == 0)
                        st.append('-');
                    else
                        st.append('+');
                }

            }
            else if (c=='+'){
                isMinus = false;
                if(!isBracket) {
                    if (dataStack.size() % 2 == 0)
                        st.append('+');
                    else
                        st.append('-');
                }
            }
            else if(c == '('){
                isBracket = true;
                if(i>0 && s.charAt(i-1)=='-') {
                    dataStack.push('-');
                }
            }
            else if(c == ')'){
                if(!dataStack.isEmpty())
                    dataStack.pop();
            }
            //Characters
            else{
                if(st.length()==0 && isMinus)
                    st.append('-');
                isBracket = false;
                st.append(c);
            }

            /*System.out.println("isMinus "+isMinus+" | isBracket "+isBracket);
            System.out.println(dataStack);
            System.out.println(st.toString());*/
        }

        return st.toString();
    }

    //Check if 2 expressions are same or not
    public static boolean checkBracketExpression(String s1, String s2){

        if(openExpressionBrackets(s1).equals(openExpressionBrackets(s2)))
            return true;
        else
            return false;
    }

    //Find largest rectagle area possible in given histogram
    public static int largestRectangleArea(List<Integer> A) {

        if(A.size()==1)
            return A.get(0);
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();
        int[] leftNextMin = new int[A.size()];
        int[] rightNextMin = new int[A.size()];

        for(int i=0, j=A.size()-1; i<A.size(); i++,j--){
            if(!leftStack.isEmpty() && A.get(i)<=A.get(leftStack.peek())){
                while(!leftStack.isEmpty() && A.get(i)<=A.get(leftStack.peek())){
                    leftStack.pop();
                }
            }

            if(leftStack.isEmpty())
                leftNextMin[i] = -1;
            else
                leftNextMin[i] = leftStack.peek();
            leftStack.push(i);

            if(!rightStack.isEmpty() && A.get(j)<=A.get(rightStack.peek())){
                while(!rightStack.isEmpty() && A.get(j)<=A.get(rightStack.peek())){
                    rightStack.pop();
                }
            }

            if(rightStack.isEmpty())
                rightNextMin[j] = -1;
            else
                rightNextMin[j] = rightStack.peek();
            rightStack.push(j);
        }

        int max = Integer.MIN_VALUE;
        for(int i=0; i<A.size(); i++){
            int height = A.get(i);
            int width = 0;
            //Smallest Element
            if(leftNextMin[i]==-1 && rightNextMin[i]==-1){
                width = A.size();
            }
            //Smallest element to it's left has smaller towards right
            else if(leftNextMin[i]==-1 && rightNextMin[i]>-1){
                width = rightNextMin[i];
            }
            //Smallest element to it's right, has smaller towards left
            else if(leftNextMin[i]>-1 && rightNextMin[i]==-1){
                width = A.size() - leftNextMin[i] - 1;
            }
            //Has smaller element towards both directions
            else{
                width = rightNextMin[i]-leftNextMin[i]-1;
            }
            int area = height*width;
            if(area>max) {
                max = area;
            }
        }
        return max;

    }

    //Build nearest minimum array for all elements
    public static void buildNearestMinimumStack(List<Integer> A){
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();
        int[] leftNextMin = new int[A.size()];
        int[] rightNextMin = new int[A.size()];

        for(int i=0, j=A.size()-1; i<A.size(); i++,j--){
            if(!leftStack.isEmpty() && A.get(i)<=A.get(leftStack.peek())){
                while(!leftStack.isEmpty() && A.get(i)<=A.get(leftStack.peek())){
                    leftStack.pop();
                }
            }

            if(leftStack.isEmpty())
                leftNextMin[i] = -1;
            else
                leftNextMin[i] = leftStack.peek();
            leftStack.push(i);

            if(!rightStack.isEmpty() && A.get(j)<=A.get(rightStack.peek())){
                while(!rightStack.isEmpty() && A.get(j)<=A.get(rightStack.peek())){
                    rightStack.pop();
                }
            }

            if(rightStack.isEmpty())
                rightNextMin[j] = -1;
            else
                rightNextMin[j] = rightStack.peek();
            rightStack.push(j);
        }

        System.out.println(A);
        System.out.print("[");
        for(int i=0; i<leftNextMin.length; i++) {
            System.out.print(leftNextMin[i]);
            if(i<leftNextMin.length-1)
                System.out.print(", ");
        }
        System.out.println("]");
        System.out.print("[");
        for(int i=0; i<rightNextMin.length; i++) {
            System.out.print(rightNextMin[i]);
            if(i<rightNextMin.length-1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    //Build Nearest maximum array for all elements
    public static void buildNearestMaximumStack(List<Integer> A){
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();
        int[] leftNextMin = new int[A.size()];
        int[] rightNextMin = new int[A.size()];

        for(int i=0, j=A.size()-1; i<A.size(); i++,j--){
            if(!leftStack.isEmpty() && A.get(i)>A.get(leftStack.peek())){
                while(!leftStack.isEmpty() && A.get(i)>A.get(leftStack.peek())){
                    leftStack.pop();
                }
            }

            if(leftStack.isEmpty())
                leftNextMin[i] = -1;
            else
                leftNextMin[i] = leftStack.peek();
            leftStack.push(i);

            if(!rightStack.isEmpty() && A.get(j)>A.get(rightStack.peek())){
                while(!rightStack.isEmpty() && A.get(j)>A.get(rightStack.peek())){
                    rightStack.pop();
                }
            }

            if(rightStack.isEmpty())
                rightNextMin[j] = -1;
            else
                rightNextMin[j] = rightStack.peek();
            rightStack.push(j);
        }

        System.out.println(A);
        System.out.print("[");
        for(int i=0; i<leftNextMin.length; i++) {
            System.out.print(leftNextMin[i]);
            if(i<leftNextMin.length-1)
                System.out.print(", ");
        }
        System.out.println("]");
        System.out.print("[");
        for(int i=0; i<rightNextMin.length; i++) {
            System.out.print(rightNextMin[i]);
            if(i<rightNextMin.length-1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    //Return sum of (max - min) for all subarrays
    public static int maxAndMin(List<Integer> A) {
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();
        int[] leftNextMin = new int[A.size()];
        int[] rightNextMin = new int[A.size()];

        for(int i=0, j=A.size()-1; i<A.size(); i++,j--){
            if(!leftStack.isEmpty() && A.get(i)<=A.get(leftStack.peek())){
                while(!leftStack.isEmpty() && A.get(i)<=A.get(leftStack.peek())){
                    leftStack.pop();
                }
            }

            if(leftStack.isEmpty())
                leftNextMin[i] = -1;
            else
                leftNextMin[i] = leftStack.peek();
            leftStack.push(i);

            if(!rightStack.isEmpty() && A.get(j)<=A.get(rightStack.peek())){
                while(!rightStack.isEmpty() && A.get(j)<=A.get(rightStack.peek())){
                    rightStack.pop();
                }
            }

            if(rightStack.isEmpty())
                rightNextMin[j] = -1;
            else
                rightNextMin[j] = rightStack.peek();
            rightStack.push(j);
        }

        long minSum = 0;
        for(int i=0; i<A.size(); i++){
            int leftMin = leftNextMin[i];
            int rightMin = rightNextMin[i];
            long lElements;
            long rElements;
            long width;
            //Smallest Element
            if(leftMin==-1 && rightMin==-1){
                lElements = i+1;
                rElements = A.size()-i;
                width = lElements*rElements;
            }
            //Smallest in left, has smaller towards right
            else if(leftMin==-1 && rightMin>-1){
                lElements = i+1;
                rElements = rightMin-i;
                width = lElements*rElements;
            }
            //Smallest in right, has smaller towards left
            else if(leftMin>-1 && rightMin==-1){
                lElements = i-leftMin;
                rElements = A.size()-i;
                width = lElements*rElements;
            }
            //Has smaller element towards both sides
            else{
                lElements = i-leftMin;
                rElements = rightMin-i;
                width = lElements*rElements;
            }
            //System.out.println(A.get(i)+" X "+width);
            minSum+= (width*A.get(i));
        }

        leftStack = new Stack<>();
        rightStack = new Stack<>();
        leftNextMin = new int[A.size()];
        rightNextMin = new int[A.size()];

        for(int i=0, j=A.size()-1; i<A.size(); i++,j--){
            if(!leftStack.isEmpty() && A.get(i)>A.get(leftStack.peek())){
                while(!leftStack.isEmpty() && A.get(i)>A.get(leftStack.peek())){
                    leftStack.pop();
                }
            }

            if(leftStack.isEmpty())
                leftNextMin[i] = -1;
            else
                leftNextMin[i] = leftStack.peek();
            leftStack.push(i);

            if(!rightStack.isEmpty() && A.get(j)>A.get(rightStack.peek())){
                while(!rightStack.isEmpty() && A.get(j)>A.get(rightStack.peek())){
                    rightStack.pop();
                }
            }

            if(rightStack.isEmpty())
                rightNextMin[j] = -1;
            else
                rightNextMin[j] = rightStack.peek();
            rightStack.push(j);
        }

        long maxSum = 0;
        for(int i=0; i<A.size(); i++){
            int leftMin = leftNextMin[i];
            int rightMin = rightNextMin[i];
            long lElements;
            long rElements;
            long width;
            //Smallest Element
            if(leftMin==-1 && rightMin==-1){
                lElements = i+1;
                rElements = A.size()-i;
                width = lElements*rElements;
            }
            //Largest in left, has larger towards right
            else if(leftMin==-1 && rightMin>-1){
                lElements = i+1;
                rElements = rightMin-i;
                width = lElements*rElements;
            }
            //Largest in right, has larger towards left
            else if(leftMin>-1 && rightMin==-1){
                lElements = i-leftMin;
                rElements = A.size()-i;
                width = lElements*rElements;
            }
            //Has smaller element towards both sides
            else{
                lElements = i-leftMin;
                rElements = rightMin-i;
                width = lElements*rElements;
            }
            maxSum+= (width*A.get(i));
        }
        int mod = (int)(Math.pow(10,9))+7;
        //System.out.println(maxSum+" | "+minSum);
        return (int)((maxSum-minSum)%mod);

    }

    public static ArrayList<Integer> sortListUsingTwoStacks(List<Integer> a){

        Stack<Integer> stack1 = new Stack<>();
        for(Integer x : a){
            stack1.push(x);
        }
        Stack<Integer> stack2 = new Stack<>();

        while(!stack1.isEmpty()){
            int temp = stack1.pop();

            while(!stack2.isEmpty()&&stack2.peek()<temp){
                stack1.push(stack2.pop());
            }
            stack2.push(temp);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while(!stack2.isEmpty()){
            ans.add(stack2.pop());
        }
        return ans;
    }

    public static Character getBracketSign(Character sign, Character newSign) {
        if(sign == '+') {
            return newSign;
        }
        // Sign -
        else {
            //
            if(newSign == '-') {
                return '+';
            }
            else {
                return '-';
            }
        }
    }

    public static Character addToExpression(Stack<Character> stack, Character c) {

        if(!stack.isEmpty() ) {
            // Stack has -
            if(stack.peek() == '-') {
                if (c == '+') {
                    return '-';
                } else {
                    return '+';
                }
            }
            // Stack has +
            else {
                return c;
            }
        }
        else {
            return c;
        }
    }

    public static HashMap<Character, Character> openExpression(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        int i=0;
        while(i<s.length()) {
            char c = s.charAt(i);
            if(c == '-' || c == '+') {
                if(s.charAt(i+1) != '(') {

                    Character sign = addToExpression(stack, c);
                    sb.append(sign);
                    i++;
                    sb.append(s.charAt(i));
                    map.put(s.charAt(i), sign);
                    i++;
                }
                else {
                    if(stack.isEmpty())
                        stack.push(c);
                    else {
                        stack.push(getBracketSign(stack.peek(), c));
                    }
                    i+=2;
                }
            }
            else {
                if(c==')') {
                    stack.pop();
                }
                else if(c == '(') {
                    if(stack.isEmpty())
                        stack.push('+');
                    else {
                        stack.push(getBracketSign(stack.peek(), '+'));
                    }
                }
                else {
                    Character sign = addToExpression(stack, '+');
                    sb.append(sign);
                    sb.append(c);
                    map.put(c, sign);
                }
                i++;
            }
        }
//        return sb.toString();
        return map;
    }

    public static int compareExpressions(String A, String B) {
        HashMap<Character, Character> aMap = openExpression(A);
        HashMap<Character, Character> bMap = openExpression(B);
        // If unequal variables
        if(aMap.size() != bMap.size()){
            return 0;
        }
        // Equal variables
        Set<Character> aKeys = aMap.keySet();
        for(Character key : aKeys) {
            // Variable not present
            if(!bMap.containsKey(key)) {
                return 0;
            }
            // Variable sign not equal
            if(bMap.get(key) != aMap.get(key)) {
                return 0;
            }
        }

        return 1;
    }

    public static void main(String[] args) {
        //Build Stack
        /*Stacks.Stack myStack = new Stacks.Stack();
        myStack.push(5);
        myStack.push(4);
        myStack.push(7);
        myStack.push(8);
        myStack.push(3);
        myStack.push(2);
        myStack.push(10);
        myStack.push(11);
        System.out.println(myStack.toString());
        System.out.println(myStack.getMin());
        myStack.pop();
        myStack.pop();
        System.out.println(myStack.pop());
        System.out.println(myStack.getMin());*/

        //Balanced Paranthesis
        /*System.out.println(balancedParanthesis("{([])}}"));*/

        //Evaluate Expression
        /*System.out.println(evaluateExpression(Arrays.asList("2", "1", "+", "3", "*")));
        System.out.println(evaluateExpression(Arrays.asList("4", "13", "5", "/", "+")));*/

        //Double Trouble Characters
        /*System.out.println(doubleCharacterTrouble("abccbc"));*/

        //Redundant Braces
        /*System.out.println(checkRedundantBraces("((a+b))"));
        System.out.println(checkRedundantBraces("(a+(a+b))"));
        System.out.println(checkRedundantBraces("(a)"));*/

        //Check Bracket Expression
        /*System.out.println(openExpressionBrackets("-(a+((b-c)-(d+e)))"));
        System.out.println(openExpressionBrackets("-(a+b-c-d-e)"));
        System.out.println(openExpressionBrackets("+(a)-b-c"));
        System.out.println(checkBracketExpression("a-(b+c)", "+(a)-b-c"));*/

        //Largest Rectangle Area in Histogram
        /*System.out.println(largestRectangleArea(Arrays.asList(2, 1, 5, 6, 2, 3)));
        System.out.println(largestRectangleArea(Arrays.asList(47, 69, 67, 97, 86, 34, 98, 16, 65, 95, 66, 69, 18, 1, 99, 56, 35, 9, 48, 72, 49, 47, 1, 72, 87, 52, 13, 23, 95, 55, 21, 92, 36, 88, 48, 39, 84, 16, 15, 65, 7, 58, 2, 21, 54, 2, 71, 92, 96, 100, 28, 31, 24, 10, 94, 5, 81, 80, 43, 35, 67, 33, 39, 81, 69, 12, 66, 87, 86, 11, 49, 94, 38, 44, 72, 44, 18, 97, 23, 11, 30, 72, 51, 61, 56, 41, 30, 71, 12, 44, 81, 43, 43, 27  )));*/

        //Build Nearest Minimum Array
        /*buildNearestMinimumStack(Arrays.asList(4, 7, 3, 8, 9, 10, 1, 5 ,12, 6));*/

        //Build Nearest Maximum Array
        /*buildNearestMaximumStack(Arrays.asList(34, 35, 27, 42, 5, 28, 39, 20, 28));*/

        //Get sum of (max-min) for all subarrays
        /*buildNearestMinimumStack(Arrays.asList(4, 7, 3, 8));
        buildNearestMaximumStack(Arrays.asList(4, 7, 3, 8));
        System.out.println(maxAndMin(Arrays.asList(4, 7, 3, 8)));*/

        /*System.out.println(sortListUsingTwoStacks(Arrays.asList(5,2,4,1,7,1)));*/

        // Compare expression
//        System.out.println(compareExpressions("(a+b-(c+(-d+e-f)))", "a+b-c+d-e+f")); // +a+b-c+d-e+f
//        System.out.println(compareExpressions("-a-b-c", "-(a+b+c)"));
//        System.out.println(compareExpressions("a-b-(c-d)", "a-b-c-d"));
        System.out.println(compareExpressions("-(a-(-z-(b-(c+t)-x)+l)-q)", "-a+l-b-(z-(c+t)-x-q)"));
    }

}
