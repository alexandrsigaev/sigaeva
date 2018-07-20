package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 20.07.2018
 */
public class ArrayListTest {

    private ArrayList<Integer> arrayList;

    @Before
    public void setUp() {
        this.arrayList = new ArrayList<>(3);
        this.arrayList.add(25);
        this.arrayList.add(5);
        this.arrayList.add(8);
    }

    @Test
    public void whenAddNewElementThenGetIt() {
        this.arrayList.add(48);
        assertThat(48, is(this.arrayList.get(3)));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeIteratorThenGetException() {
        Iterator<Integer> iterator = this.arrayList.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(25));
        this.arrayList.add(25);
        iterator.hasNext();
    }

}