package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 29.01.2018
 */
public class ArrayDuplicateTest {

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate remove = new ArrayDuplicate();
        assertThat(remove.remove(new String[] {"Привет", "Мир", "Привет", "Супер", "Мир"}),
                is(new String[] {"Привет", "Мир", "Супер"}));
    }
}

