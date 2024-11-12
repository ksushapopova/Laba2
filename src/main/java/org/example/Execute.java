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
        String postfix = "";
        Stack<Character> stack = new Stack<>();
        int index = 0;
        while (index < chars.length) {
            char chr = chars[index];

            if (Character.isDigit(chr)) {
                int ind = index;
                while (ind != chars.length && Character.isDigit(chars[ind])) {
                    postfix += chars[ind];
                    ind += 1;
                }
                index += ind - index - 1;
                postfix += " ";
            } else if (chr == '(') {
                stack.push('(');
            } else if (chr == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    postfix += stack.pop();
                stack.pop();
            } else if (is_operator(chr)) {
                while (!stack.isEmpty() && PRIORITY.get(stack.peek()) >= PRIORITY.get(chr))
                    postfix += stack.pop();
                stack.push(chr);
            }

            index++;
        }

        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }

        return postfix.trim();
    }

    public boolean is_operator(char chr) {
        return PRIORITY.containsKey(chr);
    }

    public static void main(String[] args) {
        Execute executor = new Execute();
        String infix = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3";
        String postfix = executor.infix_to_postfix(infix);
        System.out.println("Результат: " + postfix);
    }
}
