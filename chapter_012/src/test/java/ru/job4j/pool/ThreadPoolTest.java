package ru.job4j.pool;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;



/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 23.11.2018
 */
public class ThreadPoolTest {

    @Test
    public void whenGetWork() {
        ThreadPool pool = new ThreadPool();
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 8; i++) {
            try {
                Thread.sleep(1000);
                pool.work(() -> {
                    list.add(1);
                    System.out.println(Thread.currentThread().getName());
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        Assert.assertThat(list, is(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1)));
    }
}