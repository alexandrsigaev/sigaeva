package ru.job4j.stream;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 15.01.2019
 */
public class SortArrayStreamTest {

    @Test
    public void whenTransferArrIntegerThenGetSumOfEvenElem() {
        var tmp = SortArrayStream.sortArray(new Integer[] {1, 5, 2, 7, 8, 12, 15, 7, 5, 3, 2, 18});
        assertThat(tmp, is(540));
    }

}