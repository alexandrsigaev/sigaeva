package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *Class DummyBotTest.
 *@author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 *@version 1.0
 *@since 26.01.2018
 */
public class DummyBotTest {
    @Test
    public void whenGreeteBot() {
        DummyBot greetingBot = new DummyBot();
        assertThat(greetingBot.answer("Привет, Бот"), is("Привет, умник"));
    }

    @Test
    public void whetBuyBot() {
        DummyBot parting = new DummyBot();
        assertThat(parting.answer("Пока."), is("До скорой встречи."));
    }

    @Test
    public void whenUnknownBot() {
        DummyBot unsupport = new DummyBot();
        assertThat(unsupport.answer("Как дела?"), is("Это ставит меня в тупик"));
    }
}
