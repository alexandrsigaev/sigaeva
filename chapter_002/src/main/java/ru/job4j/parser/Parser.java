package ru.job4j.parser;

import java.util.Stack;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 20.05.2018
 */
public class Parser {
    private Brackets brackets;
    private Stack<Character> stack;
    private String strForParse;

    public Parser(String strForParse, char[] massBrackets) {
        this.strForParse = strForParse;
        this.stack = new Stack<>();
        this.brackets = new Brackets();
        this.brackets.add(massBrackets);
    }

    public boolean valid() {
        this.stack.clear();
        for (Character chr : strForParse.toCharArray()) {
            if (brackets.getBracketMap().containsKey(chr)) {
                stack.push(chr);
            } else if (!stack.empty() && chr.equals(brackets.getBracketMap().get(stack.peek()))) {
                stack.pop();
            }
        }
        return stack.empty();
    }

    public String parse() {
        stack.clear();
        Stack<Integer> tmp = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < strForParse.length(); i++) {
            if (brackets.getBracketMap().containsKey(strForParse.charAt(i))) {
                stack.push(strForParse.charAt(i));
                tmp.push(i);
            } else if (!stack.empty() && strForParse.charAt(i) == brackets.getBracketMap().get(stack.peek()).charValue()) {
                result.append(String.format("%s:%c %s:%c ", tmp.pop(), stack.pop(), i, strForParse.charAt(i)));
            }
        }

        return result.toString();
    }

}
