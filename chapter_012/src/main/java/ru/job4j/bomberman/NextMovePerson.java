package ru.job4j.bomberman;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 18.12.2018
 */
public class NextMovePerson {
    public static MovePerson nextMovie(MovePerson lastMovie) {
        MovePerson result;
        if (lastMovie.equals(MovePerson.DOWN)) {
            result = MovePerson.RIGHT;
        } else if (lastMovie.equals(MovePerson.UP)) {
            result = MovePerson.LEFT;
        } else if (lastMovie.equals(MovePerson.LEFT)) {
            result = MovePerson.DOWN;
        } else {
            result = MovePerson.UP;
        }
        return result;
    }
}
