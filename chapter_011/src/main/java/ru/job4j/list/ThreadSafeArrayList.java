package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 23.09.2018
 */
@ThreadSafe
public class ThreadSafeArrayList<E> extends Container<E> {
    @GuardedBy("this")
    private ArrayList<E> source = new ArrayList<>();


    @Override
    public synchronized void add(E value) {
        this.source.add(value);
    }

    @Override
    public  synchronized E get(int index) {
        return this.source.get(index);
    }

    @Override
    public synchronized boolean contains(E value) {
        return this.contains(value);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(this.source).iterator();
    }

    private synchronized ArrayList<E> copy(ArrayList<E> source) {
        ArrayList<E> result = new ArrayList<>();
        for (E elem : this.source) {
            result.add(elem);
        }
        return result;
    }


}
