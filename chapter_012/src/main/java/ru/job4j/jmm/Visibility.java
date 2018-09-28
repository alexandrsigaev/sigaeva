package ru.job4j.jmm;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 17.09.2018
 */
public class Visibility {

    private static int count = 0;

    public static class TestRun implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10_000; i++) {
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new TestRun());
        Thread thread2 = new Thread(new TestRun());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(count);
    }
}
