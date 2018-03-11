package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(7);
        list.add(1);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(8);
        list.add(9);
        assertThat(convert.toArray(list, 3), is(new int[][] {{2, 7, 1}, {5, 4, 3}, {8, 9, 0}}));
    }

    @Test
    public void whenGetArrayThenReturnList() {
        ConvertList convert = new ConvertList();
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(7);
        list.add(1);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(8);
        list.add(9);
        list.add(0);
        assertThat(convert.toList(new int[][] {{2, 7, 1}, {5, 4, 3}, {8, 9, 0}}), is(list));
    }
}
