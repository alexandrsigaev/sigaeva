package ru.job4j;

import java.util.Comparator;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 16.03.2018
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        char[] arrO1 = o1.toCharArray();
        char[] arrO2 = o2.toCharArray();
        int min = Math.min(arrO1.length, arrO2.length);
        for (int i = 0; i < min; i++) {
            char first = arrO1[i];
            char second = arrO2[i];
            if (first != second) {
                return first - second;
            }
        }
        return arrO1.length - arrO2.length;
    }
}
