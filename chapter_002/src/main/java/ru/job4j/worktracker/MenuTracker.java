package ru.job4j.worktracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 24.02.2018
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();
    private final Consumer<String> output;

    public MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;

    }

    public void fillAction() {
        this.actions.add(new AddItem(this.actions.size(), "Add new Item"));
        this.actions.add(new ShowItems(this.actions.size(), "Show all items"));
        this.actions.add(new EditItem(this.actions.size(), "Edit item"));
        this.actions.add(new DeleteItem(this.actions.size(), "Delete item"));
        this.actions.add(new FindItemById(this.actions.size(), "Find item by Id"));
        this.actions.add(new FindByName(this.actions.size(), "Find items by name"));
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
    }

    public int[] getRange() {
        int[] range = new int[actions.size() + 1];
        for (int count = 0; count < actions.size() + 1; count++) {
            range[count] = count;
        }
        return range;
    }

    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("----------Add new ticket--------------");
            Item create = createItem();
            tracker.add(create);
            output.accept(String.format("------Ticket with name: %s and ID: %s create------", create.getName(), create.getId()));
        }
    }

    private class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------All tickets------");
            printInConsoleArrayOfItems(tracker.findAll());
            output.accept("------Complete------");
        }
    }

    private class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String replace = input.ask("enter the ticket id you want to replace");
            output.accept("------Replace------");
            tracker.replace(replace, createItem());
            output.accept("------The ticket replace------");
        }
    }

    private class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("enter the ID of ticket");
            output.accept("------Found tickets------");
            Item find = tracker.findById(id);
            if (find != null) {
                output.accept(find.toString());
            } else {
                output.accept("ticket not found");
            }
            output.accept("------Complete------");
        }
    }

    private class FindByName extends BaseAction {

        public FindByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("enter the name of ticket");
            output.accept("------Found tickets------");
            printInConsoleArrayOfItems(tracker.findByName(name));
            output.accept("------Complete------");
        }
    }

    private Item createItem() {
        String name = this.input.ask("Enter the name of the ticket");
        String description = this.input.ask("Enter the description of the ticket");
        return new Item(name, description, System.currentTimeMillis());
    }

    class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------Delete ticket------");
            String delete = input.ask("enter the ID of ticket");
            if (!tracker.findAll().isEmpty()) {
                tracker.delete(delete);
            } else {
                output.accept("------tickets are absent------");
            }
            output.accept("------Complete------");
        }
    }
    private void printInConsoleArrayOfItems(List<Item> items) {
        if (!items.isEmpty()) {
            for (Item item : items) {
                output.accept(item.toString());
            }
        } else {
            output.accept("tickets not found");
        }
    }

}
