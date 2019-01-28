package ru.job4j.worktraker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.worktracker.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 26.02.2018
 */
public class ValidateInputTest {

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"-1", "t", "6"}));
        input.ask("Select:", new int[] {1, 2, 3, 4, 5, 6});
        assertThat(this.mem.toString(), is(
                new StringJoiner(System.lineSeparator())
                        .add("Enter invalid count, please enter valid count from menu")
                        .add("Please enter service count")
                        .add("")
                        .toString()
                )
        );
    }
}
