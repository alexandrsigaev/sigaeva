package ru.job4j.loop;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 27.01.2018
 */
public class Factorial {
    /**
     * Calculates the factorial of a number n.
     * @param n
     * @return
     */
    public int calc(int n) {
        int factorial = 1;
        if (n != 0) {
            for (int i = 1; i <= n ; i++) {
                factorial *= i;
            }
        }
        return factorial;
    }
}
