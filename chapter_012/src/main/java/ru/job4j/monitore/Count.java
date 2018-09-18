package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 18.09.2018
 */
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
}
