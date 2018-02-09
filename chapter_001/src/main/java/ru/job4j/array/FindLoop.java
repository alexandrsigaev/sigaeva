package ru.job4j.array;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class FindLoop {
    /**
     * Search argument in the array.
     * @param data source array
     * @param elem argument
     * @return found argument
     */
    public int indexOf(int[] data, int elem) {
        int result = -1;
        for (int numOfElem = 0; numOfElem < data.length; numOfElem++) {
            if (data[numOfElem] == elem) {
                result = numOfElem;
                break;
            }
        }
        return result;
    }
}
