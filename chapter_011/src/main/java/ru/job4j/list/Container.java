package ru.job4j.list;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 23.09.2018
 */
public abstract class Container<E> implements Iterable<E> {

    abstract void add(E value);
    abstract E get(int index);
    abstract boolean contains(E value);


}
