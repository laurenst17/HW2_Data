package hw2_data;

public class PostfixCalculator {
    private Stack<String> stack;

    public PostfixCalculator() {
        stack = new ArrayStack<>();
    }

    public double evaluate(String postfixExpression) {
        for (String token : postfixExpression.split(" ")) {
            if (isOperand(token)) {
                stack.push(token);
            } else if (isOperator(token)) {
                double operand2 = Double.parseDouble(stack.pop());
                double operand1 = Double.parseDouble(stack.pop());
                double result = performOperation(token, operand1, operand2);
                stack.push(Double.toString(result));
            }
        }

        if (stack.size() == 1) {
            return Double.parseDouble(stack.pop());
        } else {
            throw new IllegalArgumentException("Invalid postfix expression");
        }
    }

    private double performOperation(String operator, double operand1, double operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private boolean isOperand(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return "+-*/".contains(token);
    }
}
