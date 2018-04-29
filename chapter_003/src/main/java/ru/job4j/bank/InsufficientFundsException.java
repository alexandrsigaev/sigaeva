package ru.job4j.bank;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 29.04.2018
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
