package ru.job4j.bank;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 29.04.2018
 */
public class NotFoundAccountException extends Exception {
    public NotFoundAccountException(String message) {
        super(message);
    }
}
