package ru.job4j.worktracker;

import java.text.SimpleDateFormat;

/**
 * Class
 * @author sigaevaleksandr
 * @since 19.02.2018
 */
public class Item {
    private String id;
    private String name;
    private String desc;
    private long created;
    private String[] comments;

    public Item() {
    }

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public Item(String id, String name, String desc, long created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public long getCreated() {
        return created;
    }

    public String[] getComments() {
        return comments;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd MMMM yyyy");
        return String.format("id: %s name: %s description: %s create: %s", this.id, this.name, this.desc, dateFormat.format(this.created));
    }
}
