package ru.job4j.geometric;

import org.junit.Test;


import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Class
 * @author sigaevaleksandr
 * @since 22.02.2018
 */
public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(
                square.draw(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add("++++")
                                .add("+  +")
                                .add("+  +")
                                .add("++++")
                                .toString()
                )
        );
    }
}

