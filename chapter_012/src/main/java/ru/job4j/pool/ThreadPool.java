package ru.job4j.pool;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.11.2018
 */
@ThreadSafe
public class ThreadPool {
    @GuardedBy("this")
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        IntStream.range(0, size).forEach(value ->
                threads.add(new Thread(() -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            this.tasks.pool().run();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }

                }
                )));
        threads.forEach(Thread::start);
    }
    public synchronized void work(Runnable job) throws InterruptedException {
        this.tasks.offer(job);
    }

    public synchronized void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}

