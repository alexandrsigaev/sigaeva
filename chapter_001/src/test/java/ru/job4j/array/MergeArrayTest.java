package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 * @author sigaevaleksandr
 * @since 26.02.2018
 */
public class MergeArrayTest {

    @Test
    public void whenGetTwoSortedArrThenReturnMergeSortedArray() {
        MergeArrays mergeArr = new MergeArrays();
        int[] first = {1, 9, 12, 18, 25};
        int[] second = {7, 8, 9, 10, 17, 48, 52};
        assertThat(mergeArr.merge(first, second), is(new int[] {1, 7, 8, 9, 9, 10, 12, 17, 18, 25, 48, 52}));
    }
}
