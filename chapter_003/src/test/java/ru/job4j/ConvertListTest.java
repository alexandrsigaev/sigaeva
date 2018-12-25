package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 08.03.2018
 */
public class ConvertListTest {

    @Test
    public void whenGetListThenReturnArray() {
        ConvertList convert = new ConvertList();
        assertThat(convert.toArray(asList(2, 7, 1, 5, 4, 3, 8, 9), 3), is(new int[][] {{2, 7, 1}, {5, 4, 3}, {8, 9, 0}}));
    }

    @Test
    public void whenGetArrayThenReturnList() {
        ConvertList convert = new ConvertList();
        assertThat(convert.toList(new int[][] {{2, 7, 1}, {5, 4, 3}, {8, 9, 0}}), is(asList(2, 7, 1, 5, 4, 3, 8, 9, 0)));
    }

    @Test
    public void whenGetListArraysThenReturnListElem() {
        ConvertList convert = new ConvertList();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{1, 2, 12, 7, 28});
        assertThat(convert.convert(list), is(asList(1, 2, 1, 2, 12, 7, 28)));
    }
}
