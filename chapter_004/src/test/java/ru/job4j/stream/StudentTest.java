package ru.job4j.stream;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 26.12.2018
 */
public class StudentTest {

    @Test
    public void whenLevelOfListStudents() {
        assertThat(new Student().levelOf(Stream.of(new Student("Alex", 4), null, new Student("Ben", 3),
                new Student("Olga", 2), null, null, new Student("Jon", 4), null,
                new Student("Bob", 5)).collect(Collectors.toList()), 3), is(List.of(
                        new Student("Bob", 5), new Student("Alex", 4),
                new Student("Jon", 4))));
    }

}