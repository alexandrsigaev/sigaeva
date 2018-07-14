package ru.job4j.generic;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 12.07.2018
 */
public abstract class Base {

    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
