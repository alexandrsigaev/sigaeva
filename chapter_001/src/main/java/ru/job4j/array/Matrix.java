package ru.job4j.array;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class Matrix {
    /**
     * Creates a multiplication table with the specified size.
     * @param size matrix size
     * @return multiplication table
     */
    public int[][] multiple(int size) {
        int[][] result = new int[size][size];
        for (int column = 1; column <= size; column++) {
            for (int row = 1; row <= size; row++) {
                result[column - 1][row - 1] = column * row;
            }
        }
        return result;
    }
}
