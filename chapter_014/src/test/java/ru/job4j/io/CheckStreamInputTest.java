package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 09.01.2019
 */
public class CheckStreamInputTest {

    @Test
    public void whenGetInputStreamEvenNumber() {
        CheckStreamInput checkStreamInput = new CheckStreamInput();
        Integer numb = 10;
        InputStream in = new ByteArrayInputStream(new byte[]{numb.byteValue()});
        assertTrue(checkStreamInput.isNumber(in));
    }

    @Test
    public void whenGetInputSteamOddNumber() {
        CheckStreamInput checkStreamInput = new CheckStreamInput();
        Integer numb = 5;
        InputStream in = new ByteArrayInputStream(new byte[]{numb.byteValue()});
        assertFalse(checkStreamInput.isNumber(in));
    }

}