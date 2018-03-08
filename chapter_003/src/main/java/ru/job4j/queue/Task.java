package ru.job4j.queue;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 08.03.2018
 */
public class Task {
    private String desk;
    private int priority;

    public Task(String desk, int priority) {
        this.desk = desk;
        this.priority = priority;
    }

    public String getDesk() {
        return desk;
    }

    public int getPriority() {
        return priority;
    }
}
