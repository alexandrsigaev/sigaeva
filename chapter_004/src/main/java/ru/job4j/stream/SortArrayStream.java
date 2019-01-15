package ru.job4j.stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 15.01.2019
 */
public class SortArrayStream {

    public static Integer sortArray(Integer[] arr) {
        Stream<Integer> str = Arrays.stream(arr);
        var result = str.filter(e -> e % 2 == 0).map(e -> (int) Math.pow(e, 2))
                .reduce((e1, e2) -> e1 + e2).orElse(0);
        return result;
    }

}
