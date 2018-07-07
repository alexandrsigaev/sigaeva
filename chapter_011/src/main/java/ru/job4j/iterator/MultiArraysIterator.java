package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 27.06.2018
 */
public class MultiArraysIterator implements Iterator {

    private final int[][] source;
    private final int size;
    private int countIndex = 0;
    private int countLine = 0;
    private int countColumn = 0;

    public MultiArraysIterator(final int[][] source) {
        this.source = source;
        this.size = Stream.of(source).mapToInt(m -> m.length).sum();
    }

    @Override
    public boolean hasNext() {
        return size > countIndex;
    }

    @Override
    public Object next() {
        int result;
        if (countIndex == size) {
            throw new NoSuchElementException();
        } else if (countColumn == source[countLine].length - 1) {
            result = source[countLine++][countColumn];
            countColumn = 0;
        } else {
            result = source[countLine][countColumn++];
        }
        countIndex++;

        return result;
    }
}
