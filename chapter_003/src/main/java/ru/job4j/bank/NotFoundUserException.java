package ru.job4j.bank;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 29.04.2018
 */
public class NotFoundUserException extends Exception {

    public NotFoundUserException(String msg) {
        super(msg);
    }
}
