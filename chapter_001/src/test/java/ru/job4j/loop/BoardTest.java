package ru.job4j.loop;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 28.01.2018
 */

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class BoardTest {

    @Test
    public void when3x3() {
        Board chessBoard = new Board();
        String rsl = chessBoard.paint(3, 3);
        String ln = System.lineSeparator();
        assertThat(rsl, is(String.format("X X%s X %sX X%s", ln, ln, ln)));
    }
}
