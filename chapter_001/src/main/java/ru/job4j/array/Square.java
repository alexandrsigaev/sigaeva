package ru.job4j.array;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class Square {

    /**
     *Fills an array with elements from 1 to bound squared.
     * @param bound number of elements in the array.
     * @return result.
     */
    public int[] caculate(int bound) {
        int[] result = new int[bound];
        for (int count = 1; count <= bound; count++) {
          result[count - 1] = (int) Math.pow(count, 2);
        }
        return result;
    }
}
