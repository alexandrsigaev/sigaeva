package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 10.07.2018
 */
public class SimpleArrayTest {


    @Test
    public void whenGetObjectThenReturnIt() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(4);
        assertThat(arr.get(0), is(4));
    }

    @Test
    public void whenChangeObjectThenReturnTheNew() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.set(1, 75);
        assertThat(arr.get(1), is(75));
    }

    @Test
    public void whenDeleteAnObjectThenItAbsent() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(1);
        arr.add(2);
        arr.add(45);
        arr.add(3);
        arr.delete(2);
        assertThat(arr.get(2), is(3));

    }

    @Test(expected = NoSuchElementException.class)
    public void whenUseIterator() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        Iterator<Integer> it = arr.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

}