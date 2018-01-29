package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class BubbleSortTest {

    @Test
    public void whenTakeRandomArrayThatGetSort() {
        BubbleSort bubble = new BubbleSort();
        assertThat(bubble.sort(new int[] {1, 5, 4, 2, 3, 1, 7, 8, 0, 5}),
                is(new int[] {0, 1, 1, 2, 3, 4, 5, 5, 7, 8}));
    }

}
