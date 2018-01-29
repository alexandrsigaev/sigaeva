package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class SquareTest {

    @Test
    public void whenTake4ThenGetArray() {
        Square element = new Square();
        assertThat(element.caculate(4), is(new int[] {1, 4, 9, 16}));
    }
}
