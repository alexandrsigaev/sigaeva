package ru.job4j.worktracker;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 24.02.2018
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];
    private int position = 0;

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillAction() {
        this.actions[this.position] = new AddItem(this.position++, "Add new Item");
        this.actions[this.position] = new ShowItems(this.position++, "Show all items");
        this.actions[this.position] = new EditItem(this.position++, "Edit item");
        this.actions[this.position] = new DeleteItem(this.position++, "Delete item");
        this.actions[this.position] = new FindItemById(this.position++, "Find item by Id");
        this.actions[this.position] = new FindByName(this.position++, "Find items by name");
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    public int[] getRange() {
        int[] range = new int[actions.length + 1];
        for (int count = 0; count < actions.length + 1; count++) {
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
            System.out.println("----------Add new ticket--------------");
            Item create = createItem();
            tracker.add(create);
            System.out.println(String.format("------Ticket with name: %s and ID: %s create------", create.getName(), create.getId()));
        }
    }

    private static class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------All tickets------");
            printInConsoleArrayOfItems(tracker.findAll());
            System.out.println("------Complete------");
        }
    }

    private class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String replace = input.ask("enter the ticket id you want to replace");
            System.out.println("------Replace------");
            tracker.replace(replace, createItem());
            System.out.println("------The ticket replace------");
        }
    }

    private class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("enter the ID of ticket");
            System.out.println("------Found tickets------");
            Item find = tracker.findById(id);
            if (find != null) {
                System.out.println(String.format("id: %s name: %s description: %s create: %s", find.getId(), find.getName(), find.getDesc(), find.getCreated()));
            } else {
                System.out.println("ticket not found");
            }
            System.out.println("------Complete------");
        }
    }

    private class FindByName extends BaseAction {

        public FindByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("enter the name of ticket");
            System.out.println("------Found tickets------");
            printInConsoleArrayOfItems(tracker.findByName(name));
            System.out.println("------Complete------");
        }
    }

    private Item createItem() {
        String name = this.input.ask("Enter the name of the ticket");
        String description = this.input.ask("Enter the description of the ticket");
        return new Item(name, description, (int) (System.currentTimeMillis() / 2560));
    }

    private static void printInConsoleArrayOfItems(Item[] items) {
        if (items.length != 0) {
            for (Item item : items) {
                System.out.println(String.format("id: %s name: %s description: %s create: %s", item.getId(), item.getName(), item.getDesc(), item.getCreated()));
            }
        } else {
            System.out.println("tickets not found");
        }
    }
}

class DeleteItem extends BaseAction {

    public DeleteItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------Delete ticket------");
        String delete = input.ask("enter the ID of ticket");
        if (tracker.findAll().length != 0) {
            tracker.delete(delete);
        } else {
            System.out.println("------tickets are absent------");
        }
        System.out.println("------Complete------");
    }
}
