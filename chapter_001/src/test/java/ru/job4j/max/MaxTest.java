package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *Class MaxTest
 *@author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 *@version 1.0
 *@since 26.01.2018
 */
public class MaxTest {

    @Test
    public void whenTakeArgsThenGetMax () {
        Max maximum = new Max();
        assertThat(maximum.max(25, 100), is(100));
    }
}
