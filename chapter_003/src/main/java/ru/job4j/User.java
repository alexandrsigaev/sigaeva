package ru.job4j;

/**
 * Class
 * @author sigaevaleksandr
 * @since 12.03.2018
 */
public class User {
    private Integer id;
    private String name;
    private String city;

    public User(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }
}
