package ru.job4j.loop;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 28.01.2018
 */
public class Paint {

    /**
     *
     * @param height
     * @return
     */
    public String rightTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int weihgt = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weihgt; column++) {
                if (row >= weihgt - column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    public String pyramid(int heigt) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * heigt - 1;
        for (int row = 0; row != heigt; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= heigt - column - 1 && row + heigt - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
