package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 28.06.2018
 */
public class EvenNumbersIterator implements Iterator {

    private final int[] source;
    private int count = 0;

    public EvenNumbersIterator(final int[] source) {
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return findEvenElem()[1] != -1;
    }

    @Override
    public Object next() {
        int[] result = findEvenElem();
        if (result[1] == -1) {
            throw new NoSuchElementException();
        }
        count = result[0] + 1;
        return result[1];
    }

    private int[] findEvenElem() {
        int[] result = new int[] {-1, -1};

        for (int i = count; i < source.length; i++) {
            if (source[i] % 2 == 0) {
                result[0] = i;
                result[1] = source[i];
                break;
            }
        }

        return result;
    }
}
