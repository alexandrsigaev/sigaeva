package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 28.01.2018
 */
public class Paint {
    /**
     * Builds a right-angled triangle.
     * @param height the height of the edge of the triangle
     * @return line when outputting to the console where a triangle
     */
    public String rightTrl(int height) {
        return this.loopBy(height, height, (row, column) -> row >= column);
    }
    public String leftTrl(int height) {
        return this.loopBy(height, height, (row, column) -> row >= height - column - 1);
    }
    /**
     * Builds a pyramid.
     * @param height the height of the pyramid
     * @return line when outputting to the console where a pyramid
     */
    public String pyramid(int height) {
        return this.loopBy(height, 2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column);
    }

    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
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
