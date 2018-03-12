package ru.job4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class
 * @author sigaevaleksandr
 * @since 11.03.2018
 */
public class ConvertList {
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] outer: array) {
            for (int inner : outer) {
                result.add(inner);
            }
        }
        return result;
    }

    public int[][] toArray(List<Integer> list, int rows) {
        int[][] result = new int[rows][rows];
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (iterator.hasNext()) {
                    result[i][j] = iterator.next();
                } else {
                    break;
                }
            }
        }
        return result;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] arr : list) {
            for (Integer elem : arr) {
                result.add(elem);
            }
        }
        return result;
    }
}
