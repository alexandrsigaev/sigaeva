package ru.job4j.list;

import java.util.LinkedList;
import java.util.List;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 10.08.2018
 */
public class SimpleStack<T> {

    private final List<T> stack = new LinkedList<>();

    public void push(T value) {
        stack.add(value);
    }

    public T poll() {
        T result = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return result;
    }
}
