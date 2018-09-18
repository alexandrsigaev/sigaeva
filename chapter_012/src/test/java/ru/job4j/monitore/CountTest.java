package ru.job4j.monitore;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 18.09.2018
 */
public class CountTest {

    private class CountThread extends Thread {

        private final Count count;

        private CountThread(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();
        Thread thread1 = new CountThread(count);
        Thread thread2 = new CountThread(count);

        thread1.run();
        thread2.run();

        thread1.join();
        thread2.join();

        assertThat(count.get(), is(2));
    }

}