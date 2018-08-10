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
 * @since 10.08.2018
 */
public class LinkedListTest {

    private LinkedList<Integer> linkedList;

    @Before
    public void setUp() {
        this.linkedList = new LinkedList<>();
        this.linkedList.add(25);
        this.linkedList.add(5);
        this.linkedList.add(8);
    }

    @Test
    public void whenAddNewElementThenGetIt() {
        this.linkedList.add(48);
        assertThat(48, is(this.linkedList.get(3)));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeIteratorThenGetException() {
        Iterator<Integer> iterator = this.linkedList.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(25));
        this.linkedList.add(25);
        iterator.hasNext();
    }


}