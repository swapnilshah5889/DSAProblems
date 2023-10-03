package CS114;

import java.util.Scanner;
import java.util.Stack;

public class temp1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] parts = input.split(" ");
        if (parts.length == 2) {
            String digits = parts[0];
            long targetSum = Long.parseLong(parts[1]);

            String result = findArithmeticExpression(digits, targetSum);

            if (result != null) {
                System.out.println(result);
            } else {
                System.out.println("false");
            }
        } else {
            System.out.println("Invalid input format.");
        }
    }

    public static String findArithmeticExpression(String digits, long targetSum) {
        Stack<String> stack = new Stack<>();
        return findExpression(digits, targetSum, 0L, stack);
    }

    private static String findExpression(String digits, long targetSum, long startIndex, Stack<String> stack) {
        System.out.println(stack);
        if (startIndex == digits.length()) {
            long sum = 0;
            for (String num : stack) {
                sum += Long.parseLong(num);
            }
            if (sum == targetSum) {
                return String.join( "+", stack.stream().toList());
            }
            return null;
        }

        String currentNum = "";
        String result = null;

        for (long i = startIndex; i < digits.length(); i++) {
            currentNum += digits.charAt((int) i);
            stack.push(currentNum);
            result = findExpression(digits, targetSum, i + 1, stack);
            if (result != null) {
                return result;
            }
            stack.pop();
            if (currentNum.equals("0")) {
                break;
            }
        }

        return result;
    }

}
