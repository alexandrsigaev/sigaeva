package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class ArrayDuplicate {

    public String[] remove(String[] array) {
        int lengthNewArray = array.length;
        for (int findElem = 0; findElem < lengthNewArray; findElem++) {
            for (int elem = findElem + 1; elem < lengthNewArray;) {
                if (array[findElem].equals(array[elem])) {
                    String tmp = array[elem];
                    array[elem] = array[lengthNewArray - 1];
                    array[lengthNewArray - 1] = tmp;
                    lengthNewArray--;
                } else {
                    elem++;
                }
            }
        }
        return Arrays.copyOf(array, lengthNewArray);
    }
}
