package ru.job4j.loop;

/**
 * Counter
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 27.01.2018
 */
public class Counter {

    /**
     * The sum of even numbers in the range from start to finish.
     * @param start
     * @param finish
     * @return
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int counter = start; counter <= finish ; counter++) {
            if (counter % 2 == 0) {
                sum += counter;
            }
        }
        return sum;
    }
}
