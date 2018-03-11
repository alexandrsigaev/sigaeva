package ru.job4j.queue;

import java.util.LinkedList;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 08.03.2018
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * @param task задача
     */
    public void put(Task task) {
        if (!tasks.isEmpty()) {
            for (Task elem : tasks) {
                if (elem.getPriority() > task.getPriority()) {
                    this.tasks.add(this.tasks.indexOf(elem), task);
                    break;
                }
            }
        } else {
            this.tasks.add(task);
        }
    }

    public Task tacke() {
        return this.tasks.poll();
    }

}
