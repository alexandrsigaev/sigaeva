package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Class CalculatorTest произодит проверку корректности произведенных арифметических операций.
 *@author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 *@version 1.0
 *@since 26.01.2018
 */
public class CalculatorTest {

    /**
     * Test add
     */
    @Test
    public void whenAddThenShouldSumIt () {
        final Calculator calc = new Calculator();
        calc.add(1, 1);
        final double result = calc.getResult();
        assertThat(result, is(2d));
    }

    /**
     * Test subtract
     */
    @Test
    public void whenSubtractThenShouldDifferenceIt () {
        final Calculator calc = new Calculator();
        calc.subtract(10, 1);
        final double result = calc.getResult();
        assertThat(result, is(9d));
    }

    /**
     * Test multiplication
     */
    @Test
    public void whenMultiplicationThenShouldCompositionIt () {
        final Calculator calc = new Calculator();
        calc.multiplication(10, 10);
        final  double result = calc.getResult();
        assertThat(result, is(100d));
    }

    /**
     * Test div
     */
    @Test
    public void whenDivThenShouldRatioIt () {
        final Calculator calc = new Calculator();
        calc.div(100, 10);
        final double result = calc.getResult();
        assertThat(result, is(10d));
    }
}
