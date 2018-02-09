package ru.job4j.loop;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 27.01.2018
 */
public class Board {
    /**
     * Paint a chessboard size the length of the width.
     * @param width board width
     * @param height board height
     * @return chessboard
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        for (int column = 0; column < height; column++) {
            for (int line = 0; line < width; line++) {
                if ((column + line) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
