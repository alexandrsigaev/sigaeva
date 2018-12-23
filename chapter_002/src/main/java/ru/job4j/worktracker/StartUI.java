package ru.job4j.worktracker;

import java.util.function.Consumer;

/**
 * Class
 * @author sigaevaleksandr
 * @since 21.02.2018
 */
public class StartUI {

    private final Input input;
    private final Tracker tracker;
    private final Consumer<String> output;

    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, output);
        menu.fillAction();
        int[] range = menu.getRange();
        do {
            System.out.println("------MENU------");
            menu.show();
            System.out.println("6. Exit Program");
            int key = input.ask("Select : ", range);
            if (key == 6) {
                break;
            }
            menu.select(key);
        } while (true);
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}
