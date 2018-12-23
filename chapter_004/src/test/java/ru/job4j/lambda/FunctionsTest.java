package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 23.12.2018
 */
public class FunctionsTest {

    private Functions functions = new Functions();


    @Test
    public void whenLinearFunction() {
        assertThat(functions.diapason(0, 4, Functions::linear), is(Arrays.asList(0D, 1D, 2D, 3D, 4D)));
    }

    @Test
    public void whenQuadraticFunction() {
        assertThat(functions.diapason(0, 4, Functions::quadratic), is(Arrays.asList(0D, 1D, 4D, 9D, 16D)));
    }

    @Test
    public void whenLogarithmeticFunction() {
        assertThat(functions.diapason(0, 4, Functions::logarithmetic),
                is(Arrays.asList(Math.log(0), Math.log(1), Math.log(2), Math.log(3), Math.log(4))));
    }

}