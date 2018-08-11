package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 11.08.2018
 */
public class SimpleQueueTest {

    private SimpleQueue<Integer> queue;

    @Before
    public void setUp() {
        this.queue = new SimpleQueue<>();
    }

    @Test
    public void whenGetElemThenGetFirstInQueue() {
        this.queue.push(1);
        this.queue.push(2);
        this.queue.push(3);

        assertThat(this.queue.poll(), is(1));
        assertThat(this.queue.poll(), is(2));
        assertThat(this.queue.poll(), is(3));
    }

}