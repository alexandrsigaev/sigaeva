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

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillAction() {
        this.actions[0] = new AddItem();
        this.actions[1] = new ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindByName();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : actions) {
            if (action !=null){
                System.out.println(action.info());
            }
        }
    }

    private class AddItem implements UserAction {

        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Add new ticket--------------");
            Item create = createItem();
            tracker.add(create);
            System.out.println(String.format("------Ticket with name: %s and ID: %s create------", create.getName(), create.getId()));
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add new Item");
        }
    }

    private static class ShowItems implements UserAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------All tickets------");
            printInConsoleArrayOfItems(tracker.findAll());
            System.out.println("------Complete------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    private class EditItem implements UserAction {

        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String replace = input.ask("enter the ticket id you want to replace");
            System.out.println("------Replace------");
            tracker.replace(replace, createItem());
            System.out.println("------The ticket replace------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Edit item");
        }
    }

    private class FindItemById implements UserAction {

        @Override
        public int key() {
            return 4;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }

    private class FindByName implements UserAction {

        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("enter the name of ticket");
            System.out.println("------Found tickets------");
            printInConsoleArrayOfItems(tracker.findByName(name));
            System.out.println("------Complete------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
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

class DeleteItem implements UserAction {

    @Override
    public int key() {
        return 3;
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

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Delete item");
    }
}
