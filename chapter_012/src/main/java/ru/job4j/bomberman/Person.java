package ru.job4j.bomberman;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 16.12.2018
 */
public class Person {
    private Cell position;

    public Person(Cell cell) {
        this.position = cell;
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }
}
