package ru.job4j.worktracker;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 26.02.2018
 */
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    @Override
    public int ask(String question, int[] range) {
        int key = -1;
        boolean invalid = true;
        do {
            try {
                key = Integer.valueOf(this.input.ask(question, range));
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate count");
            } catch (MenuOutException e) {
                System.out.println("Enter invalid count, please enter valid count from menu");
            }
        } while (invalid);
        return key;
    }
}
