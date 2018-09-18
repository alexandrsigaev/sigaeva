package ru.job4j.jmm;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 18.09.2018
 */
public class Ordering {

    private static int a = 0;
    private static int b = 0;
    private static int t1, t2;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {
                t1 = b;
                a = 1;
            }
        });

        Thread thread2= new Thread(new Runnable() {
            @Override
            public void run() {
                t2 = a;
                b = 1;
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(t1 + " " + t2);

    }

}
