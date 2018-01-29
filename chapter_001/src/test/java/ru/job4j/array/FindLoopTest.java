package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class FindLoopTest {

    @Test
    public void whenFindInvalidElemThenGetMinusOne() {
        FindLoop find = new FindLoop();
        assertThat(find.indexOf(new int[] {1, 7, 9}, 5), is(-1));
    }

    @Test
    public void whenFindCorrectElemThenGetHisNumber() {
        FindLoop find = new FindLoop();
        assertThat(find.indexOf(new int[] {1, 7, 9, 12}, 9), is(2));
    }
}
