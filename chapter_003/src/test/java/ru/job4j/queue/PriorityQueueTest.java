package ru.job4j.queue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 08.03.2018
 */
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesk(), is("urgent"));
    }

    @Test
    public void shouldReturnFromOneToFiveSequentially() {
        Task t1 = new Task("1", 1);
        Task t2 = new Task("2", 2);
        Task t3 = new Task("3", 3);
        Task t4 = new Task("4", 4);
        Task t5 = new Task("5", 5);
        PriorityQueue queue = new PriorityQueue();
        queue.put(t2);
        queue.put(t5);
        queue.put(t4);
        queue.put(t1);
        queue.put(t3);
        assertThat(queue.take(), is(t1));
        assertThat(queue.take(), is(t2));
        assertThat(queue.take(), is(t3));
        assertThat(queue.take(), is(t4));
        assertThat(queue.take(), is(t5));
    }
}
