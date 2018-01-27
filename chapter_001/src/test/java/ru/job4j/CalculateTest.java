package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* class CalculateTest .
* @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
* @version 1.0
* @since 25.01.2018
*/

public class CalculateTest {
  /**
  *Test echo.
  */
  @Test
  public void whenTakeNameThenTreeEchoPlusName() {
      String input = "Sigaev Aleksandr";
      String expect = "Echo echo echo : Sigaev Aleksandr";
      Calculate calc = new Calculate();
      String result = calc.echo(input);
      assertThat(result, is(expect));
  }
}
