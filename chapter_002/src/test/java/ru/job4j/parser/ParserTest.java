package ru.job4j.parser;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 24.05.2018
 */
public class ParserTest {
    @Test
    public void whenGetValidString() {
        String string = "fg{d[]3()4}[6]";
        Parser parser = new Parser(string, new char[] {'{', '}', '[', ']', '(', ')'});
        assertThat(parser.valid(), is(true));
    }

    @Test
    public void whenGetUnvalidString() {
        String string = "1[d{]5}4{(}4)";
        Parser parser = new Parser(string, new char[] {'{', '}', '[', ']', '(', ')'});
        assertThat(parser.valid(), is(false));
    }

    @Test
    public void whenParseValidString() {
        String string = "1{v{d[]3()4}[6]}";
        Parser parser = new Parser(string, new char[] {'[', ']', '(', ')', '{', '}'});
        assertThat(parser.parse(), is("5:[ 6:] 8:( 9:) 3:{ 11:} 12:[ 14:] 1:{ 15:} "));
    }
}
