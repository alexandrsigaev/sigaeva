package ru.job4j.worktracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Class
 * @author sigaevaleksandr
 * @since 19.02.2018
 */
public class Tracker {
    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random();

    public Item add(Item item) {
        this.items[position++] = item;
        item.setId(generateId());
        return item;
    }

    public void replace(String id, Item item) {
        this.items[this.findItemCount(findById(id))] = item;
        item.setId(id);
    }

    public void delete(String id) {
        int positionOfItem = this.findItemCount(this.findById(id));
        System.arraycopy(this.items, positionOfItem + 1, this.items, positionOfItem, this.items.length - positionOfItem - 1);
        this.position--;
    }

    public Item[] findAll() {
        Item[] allElem = new Item[position];
        for (int elem = 0; elem < position; elem++) {
            if (this.items[elem] != null) {
                allElem[elem] = this.items[elem];
            } else {
                break;
            }
        }
        return allElem;
    }

    public Item[] findByName(String key) {
        Item[] itFind = new Item[position];
        int count = 0;
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                itFind[count++] = item;
            }
        }
        return Arrays.copyOfRange(itFind, 0, count);
    }

    public Item findById(String id) {
        Item itFindById = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                itFindById = item;
            }
        }
        return itFindById;
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    private int findItemCount(Item item) {
        int countOfItem = -1;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(item.getId())) {
                countOfItem = i;
                break;
            }
        }
        return countOfItem;
    }
}
