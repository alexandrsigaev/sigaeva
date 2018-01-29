package ru.job4j.array;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class FindLoop {

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
