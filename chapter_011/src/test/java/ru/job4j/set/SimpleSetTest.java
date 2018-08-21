package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 21.08.2018
 */
public class SimpleSetTest {

    private SimpleSet<String> set;

    @Before
    public void setUp() {
        this.set = new SimpleSet<>();
    }

    @Test
    public void whenAddNewElemThenWithoutDuplicates() {
        this.set.add("first");
        this.set.add("second");
        this.set.add("third");
        this.set.add("first");
        this.set.add("third");
        this.set.add("fourth");

        Iterator<String> iter = set.iterator();
        assertThat(iter.next(), is("first"));
        assertThat(iter.next(), is("second"));
        assertThat(iter.next(), is("third"));
        assertThat(iter.next(), is("fourth"));

    }

}