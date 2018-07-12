package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 09.07.2018
 */
public class SimpleArray<T> implements Iterable<T> {

    private final Object[] source;
    private int count;

    public SimpleArray(int size) {
        this.source = new Object[size];
        this.count = 0;

    }

    public void add(T elem) {
        this.source[count++] = elem;
    }

    public void set(int index, T elem) {
        if (index < source.length) {
            this.source[index] = elem;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void delete(int index) {
        if (index > count) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(source, index + 1, source, index, count - index);
        this.count--;
    }

    public T get(int index) {
        T result;
        if (index < count) {
            result = (T) this.source[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

        return result;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < count;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) source[index++];
            }
        };
    }
}
