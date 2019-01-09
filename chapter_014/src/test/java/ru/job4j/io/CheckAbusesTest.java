package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 09.01.2019
 */
public class CheckAbusesTest {


    @Test
    public void whenGetInputStreamWithAbusesThenReturnOutputStreamWithoutIt() {
        CheckAbuses checkAbuses = new CheckAbuses();
        String[] abuses = {"повседневная", "практика", "повседневной"};
        String strIn = "повседневная практика показывает, что \n"
                + " начало повседневной работы по формированию позиции требуют";
        String strOut = "показывает, что \n начало работы по формированию позиции требуют\n";
        InputStream in = new ByteArrayInputStream(strIn.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        checkAbuses.dropAbuses(in, out, abuses);
        assertThat(strOut, is(out.toString()));
    }


}