package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.07.2018
 */
public class ArrayList<E> implements Iterable<E> {

    private Object[] source;
    private int count = 0;
    private int modCount = 0;

    public ArrayList() {
        this.source = new Object[10];
    }

    public ArrayList(int size) {
        this.source = new Object[size];
    }

    public void add(E value) {
        if (count == this.source.length) {
            this.createLargerArr();
        }
        this.source[count++] = value;
        this.modCount++;
    }

    public E get(int index) {
        return (E) this.source[index];
    }

    public boolean contains(E value) {
        boolean result = false;

        for (int i = 0; i < count; i++) {
            if (this.source[i].equals(value)) {
                result = true;
                break;
            }
        }

        return result;
    }

    private void createLargerArr() {
        Object[] tmp = new Object[source.length * 2];
        System.arraycopy(this.source, 0, tmp, 0, this.source.length);
        this.source = tmp;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedMpdCount = modCount;
            int iterCount = 0;
            @Override
            public boolean hasNext() {
                if (this.expectedMpdCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return this.iterCount < count;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) source[iterCount++];
            }
        };
    }
}
