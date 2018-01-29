package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class MatrixTest {

    @Test
    public void whenTakeSiseThenGetMultiplicationTable() {
        Matrix matrix = new Matrix();
        assertThat(matrix.multiple(3), is(new int[][] {{0, 1, 2}, {1, 1, 2}, {2, 2, 4}}));
    }
}
