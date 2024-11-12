package org.example;

import java.util.HashMap;
import java.util.Stack;
/**
 * Hello world!
 *
 */
public class Execute
{
    private static final HashMap<Character, Integer> PRIORITY;

    static {
        PRIORITY = new HashMap<>();
        PRIORITY.put('(', 0);
        PRIORITY.put('+', 1);
        PRIORITY.put('-', 1);
        PRIORITY.put('*', 2);
        PRIORITY.put('/', 2);
        PRIORITY.put('^', 3);
    }

    public Execute() {
    }

    public String infix_to_postfix(String infix_str) {
        char[] chars = infix_str.toCharArray();
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int index = 0;

        while (index < chars.length) {
            char chr = chars[index];

            if (Character.isDigit(chr)) {
                int ind = index;
                while (ind < chars.length && Character.isDigit(chars[ind])) {
                    postfix.append(chars[ind]);
                    ind += 1;
                }
                index += ind - index - 1;
                postfix.append(" "); // добавляем пробел после числа
            } else if (chr == '(') {
                stack.push('(');
            } else if (chr == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                    postfix.append(" "); // добавляем пробел после оператора
                }
                stack.pop(); // Удаляем '(' из стека
            } else if (is_operator(chr)) {
                while (!stack.isEmpty() && PRIORITY.get(stack.peek()) >= PRIORITY.get(chr)) {
                    postfix.append(stack.pop());
                    postfix.append(" "); // добавляем пробел после оператора
                }
                stack.push(chr);
            }

            index++;
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
            postfix.append(" "); // добавляем пробел после оператора
        }

        return postfix.toString().trim(); // удаляем лишний пробел в конце
    }

    public double execute(String postfix_str) {
        Stack<Double> stack = new Stack<>();
        char[] chars = postfix_str.toCharArray();
        int index = 0;
        while (index < chars.length) {
            char chr = chars[index];

            if (Character.isDigit(chr)) {
                String number = "";
                int ind = index;
                while (ind != chars.length && Character.isDigit(chars[ind])) {
                    number += chars[ind];
                    ind += 1;
                }
                index += ind - index;
                stack.push(Double.parseDouble(number));
            } else if (PRIORITY.containsKey(chr)) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                switch (chr) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                    case '/':
                        stack.push(operand1 / operand2);
                        break;
                    case '^':
                        stack.push(Math.pow(operand1, operand2));
                        break;
                    default:
                        throw new IllegalArgumentException("Неверный оператор: " + chr);
                }
            }

            index++;
        }

        return stack.pop();
    }

    public boolean is_operator(char chr) {
        return PRIORITY.containsKey(chr);
    }

    public boolean is_digit(char chr) {
        return Character.isDigit(chr);
    }

}
