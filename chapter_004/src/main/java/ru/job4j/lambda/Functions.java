package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 23.12.2018
 */
public class Functions {

    public List<Double> diapason(int start, int end, Function<Double, Double> function) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(function.apply((double) i));
        }
        return result;
    }

    public static double linear(double elem) {
        return elem;
    }

    public static double quadratic(double elem) {
        return Math.pow(elem, 2);
    }

    public static double logarithmetic(double elem) {
        return Math.log(elem);
    }

}
