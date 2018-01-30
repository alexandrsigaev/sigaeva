package ru.job4j.array;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class Matrix {

    /**
     * Creates a multiplication table with the specified size.
     * @param size
     * @return
     */
    public int[][] multiple(int size) {
        int[][] result = new int[size][size];
        for (int column = 0; column < size; column++) {
            for (int row = 0; row < size; row++) {
                result[column][row] = column + row;
            }
        }
        for (int column = 1; column < size; column++) {
            for (int row = 1; row < size; row++) {
                result[column][row] = column * row;
            }
        }
        
        return result;
    }
}
