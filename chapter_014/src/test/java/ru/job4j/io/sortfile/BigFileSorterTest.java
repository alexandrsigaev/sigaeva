package ru.job4j.io.sortfile;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 16.01.2019
 */
public class BigFileSorterTest {
    @Test
    public void whenSortBigFile() throws IOException {
        BigFileSorter sorter = new BigFileSorter();
        sorter.sort(new File("source"), new File("dest"));
        assertThat(Files.readAllLines(Paths.get("dest")), is(Files.readAllLines(Paths.get("destTest"))));
    }
}