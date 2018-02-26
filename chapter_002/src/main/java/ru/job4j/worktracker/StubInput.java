package ru.job4j.worktracker;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 22.02.2018
 */
public class StubInput implements Input {
    private final String[] value;
    private int position;

    public StubInput(String[] value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    @Override
    public int ask(String question, int[] range) {
        return Integer.valueOf(this.ask(question));
    }
}
