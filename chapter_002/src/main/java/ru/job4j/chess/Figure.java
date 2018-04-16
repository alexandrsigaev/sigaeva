package ru.job4j.chess;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 28.03.2018
 */
public abstract class Figure {

    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public boolean canJump() {
        return false;
    }

    public abstract Cell[] way(Cell sours, Cell dest) throws ImpossibleMoveException;

    public abstract Figure copy(Cell dest);
}
