package ru.job4j.geometric;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 * @author sigaevaleksandr
 * @since 22.02.2018
 */
public class TriangleTest {
    @Test
    public void whenDrawSquare() {
        Triangle triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add("  +  ")
                                .add(" +++ ")
                                .add("+++++")
                                .toString()
                )
        );
    }
}
