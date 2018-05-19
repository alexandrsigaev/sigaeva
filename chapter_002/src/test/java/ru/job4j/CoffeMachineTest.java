package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 19.05.2018
 */
public class CoffeMachineTest {

    @Test
    public void whenGetValueAndBuyCoffeForePriceThenGetChange() {
        CoffeeMachine machine = new CoffeeMachine();
        assertThat(new int[] {10 , 10, 5, 2, 1}, is(machine.changes(50, 22)));
    }

}
