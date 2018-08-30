package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 30.08.2018
 */
public class SimpleHashMapTest {

    SimpleHashMap<Integer, String> simpleHashMap;

    @Before
    public void setUp() {
        simpleHashMap = new SimpleHashMap<>();
    }

    @Test
    public void whenAddPairThenContainsThatPairReturnTrue() {
        simpleHashMap.insert(1, "one");
        assertThat(simpleHashMap.get(1), is("one"));
    }

    @Test
    public void whenRemovePairThenGetReturnNull() {
        simpleHashMap.insert(1, "one");
        simpleHashMap.delete(1);
        assertNull(simpleHashMap.get(1));
    }

    @Test
    public void whenIterate() {
        simpleHashMap.insert(1, "one");
        simpleHashMap.insert(2, "two");
        Iterator<Integer> it = simpleHashMap.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenLogFactorOutBoundThenGrow() {
        simpleHashMap.insert(1, "one");
        simpleHashMap.insert(2, "two");
        simpleHashMap.insert(3, "three");
        simpleHashMap.insert(4, "four");
        simpleHashMap.insert(5, "five");
        simpleHashMap.insert(6, "six");
        simpleHashMap.insert(7, "seven");
        assertThat(simpleHashMap.size(), is(7));
    }

}