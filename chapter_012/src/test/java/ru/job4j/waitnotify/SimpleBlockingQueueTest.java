package ru.job4j.waitnotify;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 05.10.2018
 */
public class SimpleBlockingQueueTest {

    @Test
    public void whenProducerAddElemThenConsumer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        ArrayList<Integer> buffer = new ArrayList<>();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(() -> {
           while (!queue.isEmpty()) {
               try {
                   buffer.add(queue.pool());
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }
}