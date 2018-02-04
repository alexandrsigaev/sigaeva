package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 04.02.2018
 */
public class ParserTest {
    @Test
    public void whenTakeOriginalStrAndSubStrThatGetAppearOriginal() {
        Parser pars = new Parser();
        assertThat(pars.contains("ПриветВсемКтоТут", "семКт"), is(true));
    }
}
