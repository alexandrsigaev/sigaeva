package ru.job4j.array;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class Turn {

    /**
     *Flips the array
      * @param data source array
     * @return
     */
    public int[] back(int[] data) {
        for (int elem = 0; elem < data.length / 2; elem++) {
            int tmp = data[elem];
            data[elem] = data[data.length - 1 - elem];
            data[data.length - 1 - elem] = tmp;
        }
        return data;
    }
}
