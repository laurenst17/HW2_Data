package hw2_data;

import java.util.*;


public class Converter {
    private String infix;
    private Stack<String> stack;

    public Converter(String infix) {
        this.infix = infix;
    }

    public String toPostFix() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String token : infix.split("")) {
            if (isOperator(token)) {
                while (!stack.isEmpty() && isHigherPrec(token, top())) {
                    stringBuilder.append(stack.pop()).append(' ');
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !top().equals("(")) {
                    stringBuilder.append(stack.pop()).append(' ');
                }
                if (!stack.isEmpty() && top().equals("(")) {
                    stack.pop(); // Pop the opening parenthesis
                }
            } else {
                stringBuilder.append(token).append(' ');
            }
        }

        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop()).append(' ');
        }

        return stringBuilder.toString().trim();
    }

    public String top() {
        if (!stack.isEmpty()) {
            return stack.top();
        }
        return null; // Stack is empty, no element to peek
    }

    private boolean isHigherPrec(String op, String sub) {
        int opPrec = getPrecedence(op);
        int subPrec = getPrecedence(sub);
        return opPrec <= subPrec;
    }

    private int getPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    private boolean isOperator(String token) {
        return "+-*/".contains(token);
    }
}
