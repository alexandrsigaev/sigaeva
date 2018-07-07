package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 05.07.2018
 */
public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> its) {

        return new Iterator<Integer>() {

            Iterator<Integer> currentItr = its.next();

            @Override
            public boolean hasNext() {
                boolean result = true;
                if (!currentItr.hasNext()) {
                    result = false;
                    while (its.hasNext()) {
                        currentItr = its.next();
                        if (currentItr.hasNext()) {
                         result = true;
                         break;
                        }
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return currentItr.next();
            }
        };
    }
}
