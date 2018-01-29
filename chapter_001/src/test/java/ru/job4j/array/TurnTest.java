package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class TurnTest {

    @Test
    public void whenTakeEvenArrayThatGetFlipped() {
        Turn mirror = new Turn();
        assertThat(mirror.back(new int[] {1, 5, 4, 7}), is(new int[] {7, 4, 5, 1}));
    }

    @Test
    public void whenTakeUnevenArrayThatGetFlipped() {
        Turn mirror = new Turn();
        assertThat(mirror.back(new int[] {1, 5, 4, 7, 9}), is(new int[] {9, 7, 4, 5, 1}));
    }
}
