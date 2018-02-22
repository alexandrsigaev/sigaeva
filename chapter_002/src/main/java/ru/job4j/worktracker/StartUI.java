package ru.job4j.worktracker;

/**
 * Class
 * @author sigaevaleksandr
 * @since 21.02.2018
 */
public class StartUI {
    private static final String ADD = "0";
    private static final String SHOW_ALL = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FIND_BY_ID = "4";
    private static final String FIND_BY_NAME = "5";
    private static final String EXIT = "6";
    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Select:");
            if (ADD.equals(answer)) {
                this.addItem();
            } else if (SHOW_ALL.equals(answer)) {
                this.showItems();
            } else if (EDIT.equals(answer)) {
                this.editItems();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findItemById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findItemByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            } else {
                System.out.println("Input Error");
            }
        }
    }

    private void findItemByName() {
        String name = this.input.ask("enter the name of ticket");
        System.out.println("------Found tickets------");
        this.printInConsoleArrayOfItems(this.tracker.findByName(name));
        System.out.println("------Complete------");
    }

    private void findItemById() {
        String id = this.input.ask("enter the ID of ticket");
        System.out.println("------Found tickets------");
        Item find = this.tracker.findById(id);
        if (find != null) {
            System.out.println("id: " + find.getId() + " name: " + find.getName() + " description: " + find.getDesc() + " create " + find.getCreated());
        } else {
            System.out.println("ticket not found");
        }
        System.out.println("------Complete------");
    }

    private void deleteItem() {
        System.out.println("------Delete ticket------");
        String delete = this.input.ask("enter the ID of ticket");
        if (this.tracker.findAll().length != 0) {
            this.tracker.delete(delete);
        } else {
            System.out.println("------tickets are absent------");
        }
        System.out.println("------Complete------");
    }

    private void editItems() {
        String replace = this.input.ask("enter the ticket id you want to replace");
        System.out.println("------Replace------");
        this.tracker.replace(replace, this.createItem());
        System.out.println("------The ticket replace------");
    }

    private void showItems() {
        System.out.println("------All tickets------");
        this.printInConsoleArrayOfItems(this.tracker.findAll());
        System.out.println("------Complete------");
    }

    private void addItem() {
        System.out.println("----------Add new ticket--------------");
        Item create = this.createItem();
        this.tracker.add(create);
        System.out.println("------Ticket with name: " + create.getName() + " and ID: " + create.getId() + " create------");
    }

    private Item createItem() {
        String name = this.input.ask("Enter the name of the ticket");
        String description = this.input.ask("Enter the description of the ticket");
        return new Item(name, description, (int) (System.currentTimeMillis() / 2560));
    }

    private void showMenu() {
        System.out.println(
                "------MENU------\n"
                        + "0. Add new Item\n"
                        + "1. Show all items\n"
                        + "2. Edit item\n"
                        + "3. Delete item\n"
                        + "4. Find item by Id\n"
                        + "5. Find items by name\n"
                        + "6. Exit Program\n");
    }

    private void printInConsoleArrayOfItems(Item[] items) {
        if (items.length != 0) {
            for (Item item : items) {
                System.out.println("id: " + item.getId() + " 1name: " + item.getName() + " description: " + item.getDesc() + " create " + item.getCreated());
            }
        } else {
            System.out.println("tickets not found");
        }
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
