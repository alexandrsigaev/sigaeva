package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 29.09.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue;
    @GuardedBy("this")
    private int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (this.queue.size() >= this.size) {
            this.wait();
        }
        this.queue.offer(value);
        this.notifyAll();
    }

    public synchronized T pool() throws InterruptedException {
        T result;
        while (this.queue.isEmpty()) {
            this.wait();
        }
        result = this.queue.poll();
        this.notifyAll();
        return result;
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }

}
