package ru.job4j.bomberman;

import org.junit.Test;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 19.12.2018
 */
public class HeroThreadTest {

    @Test
    public void whenHaveTwoHeroThread() throws InterruptedException {
        Board board = new Board(7, 12);
        Thread hero1 = new Thread(new HeroThread(new Cell(1, 2), board, MovePerson.RIGHT));
        Thread hero2 = new Thread(new HeroThread(new Cell(6, 6), board, MovePerson.UP));
        hero1.start();
        hero2.start();
        hero1.join();
    }

}