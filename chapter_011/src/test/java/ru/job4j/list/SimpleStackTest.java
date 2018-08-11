package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 11.08.2018
 */
public class SimpleStackTest {

    private SimpleStack<Integer> stack;

    @Before
    public void setUp() {
        this.stack = new SimpleStack<>();
    }

    @Test
    public void whenGetElementsThenGetFirstInStack() {
        this.stack.push(1);
        this.stack.push(2);
        this.stack.push(3);

        assertThat(this.stack.poll(), is(3));
        assertThat(this.stack.poll(), is(2));
        assertThat(this.stack.poll(), is(1));

    }

}