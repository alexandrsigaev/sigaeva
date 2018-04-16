package ru.job4j.chess;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 05.04.2018
 */
public class OccupiedWayException extends Exception {
    public OccupiedWayException(String message) {
        super(message);
    }
}
