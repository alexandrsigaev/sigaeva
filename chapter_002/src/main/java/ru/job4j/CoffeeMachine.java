package ru.job4j;

import java.util.Arrays;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 19.05.2018
 */
public class CoffeeMachine {

    public int[] changes(int value, int price) {
        int[] result = new int[100];
        int count = 0;
        int change = value - price;
        int multipleOfTen = change / 10;
        for (int i = 0; i < multipleOfTen; i++) {
            result[count++] = 10;
        }
        change = change - multipleOfTen * 10;

        int multipleOfFive = change / 5;
        for (int i = 0; i < multipleOfFive; i++) {
            result[count++] = 5;
        }
        change = change - multipleOfFive * 5;

        int multipleOfTwo = change / 2;
        for (int i = 0; i < multipleOfTwo; i++) {
            result[count++] = 2;
        }
        change = change - multipleOfTwo * 2;

        for (int i = 0; i < change; i++) {
            result[count++] = 1;
        }

        return Arrays.copyOf(result, count);
    }
}
