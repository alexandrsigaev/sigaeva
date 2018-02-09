package ru.job4j.loop;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 27.01.2018
 */
public class Factorial {
    /**
     * Calculates the factorial of a number n.
     * @param number number from which you need to calculate the factorial
     * @return factorial of a number
     */
    public int calc(int number) {
        int factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
