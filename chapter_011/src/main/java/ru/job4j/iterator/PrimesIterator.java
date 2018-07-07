package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 28.06.2018
 */
public class PrimesIterator implements Iterator {

    private final int[] source;
    private int count = 0;

    public PrimesIterator(int[] source) {
        this.source = source;
    }


    @Override
    public boolean hasNext() {
        return findPrime()[0] != -1;
    }

    @Override
    public Object next() {
        int[] resilt = findPrime();
        if (resilt[1] == -1) {
            throw new NoSuchElementException();
        }
        count = resilt[0] + 1;
        return resilt[1];
    }


    private boolean isPrime(int num) {
        boolean result = true;
        if (num < 2) {
            result = false;
        } else {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private int[] findPrime() {
        int[] result = new int[] {-1, -1};

        for (int i = count; i < source.length; i++) {
            if (isPrime(source[i])) {
                result[0] = i;
                result[1] = source[i];
                break;
            }
        }

        return result;
    }
}
