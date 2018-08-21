package ru.job4j.set;

import ru.job4j.list.ArrayList;

import java.util.Iterator;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 21.08.2018
 */
public class SimpleSet<T> implements Iterable<T> {

    private final ArrayList<T> storage = new ArrayList<>(5);

    public void add(T value) {
        if (!this.storage.contains(value)) {
            this.storage.add(value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.storage.iterator();
    }
}
