package ru.job4j.list;

import java.util.LinkedList;
import java.util.List;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 10.08.2018
 */
public class SimpleQueue<T> {

    private List<T> queue = new LinkedList<>();

    public T poll() {
        T result = queue.get(0);
        queue.remove(0);
        return result;
    }

    public void push(T value) {
        this.queue.add(value);
    }
}
