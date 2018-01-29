package ru.job4j.array;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class BubbleSort {

    /**
     * Sort array.
     * @param data source array
     * @return
     */
    public int[] sort(int[] data) {
        for (int barrier = 0; barrier < data.length; barrier++) {
            for (int index = barrier; index < data.length; index++) {
                if (data[barrier] > data[index]) {
                    int tmp = data[barrier];
                    data[barrier] = data[index];
                    data[index] = tmp;
                }
            }
        }
        return data;
    }
}
