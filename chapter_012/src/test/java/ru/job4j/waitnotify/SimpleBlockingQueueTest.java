package ru.job4j.waitnotify;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

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
            IntStream.range(0, 10).forEach(
                    value -> {
                        try {
                            queue.offer(value);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

            );
        });

        Thread consumer = new Thread(() -> {
           while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
               try {
                   buffer.add(queue.pool());
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   Thread.currentThread().interrupt();
               }
           }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();

        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }
}