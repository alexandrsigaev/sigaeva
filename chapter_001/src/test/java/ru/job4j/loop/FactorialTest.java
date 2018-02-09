package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 27.01.2018
 */
public class FactorialTest {
    @Test
    public void whenCalculateFactorialForFiveThenOneHundredTwenty() {
        Factorial five = new Factorial();
        assertThat(five.calc(5), is(120));
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        Factorial zero = new Factorial();
        assertThat(zero.calc(0), is(1));
    }
}
