package ru.job4j.bomberman;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 16.12.2018
 */
public enum MovePerson {
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP(0, 1),
    DOWN(0, -1);


    private int deltX;
    private int deltY;

    MovePerson(int deltX, int deltY) {
        this.deltX = deltX;
        this.deltY = deltY;
    }

    public int getDeltX() {
        return deltX;
    }

    public int getDeltY() {
        return deltY;
    }
}
