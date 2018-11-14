package ru.job4j.nonblock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.11.2018
 */
public class CacheTest {
    private Cache cache;
    private Cache.Base base;


    @Before
    public void setUp() {
        this.cache = new Cache();
        this.base = new Cache.Base(1, "qwerty");
        cache.add(base);
    }

    @Test
    public void whenUpdateTwoThreadsThenGetException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        cache.update(new Cache.Base(1, "uiop"));
                    } catch (RuntimeException e) {
                        ex.set(e);
                    }
                }

        );
        Thread thread1 = new Thread(
                () -> {
                    try {
                        cache.update(new Cache.Base(1, "asdfg"));
                    } catch (RuntimeException e) {
                        ex.set(e);
                    }
                }

        );
        thread.start();
        thread.join();
        thread1.start();
        thread1.join();
        Assert.assertThat(ex.get().getMessage(), is("Change denied"));
    }
}