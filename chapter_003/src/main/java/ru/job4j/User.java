package ru.job4j;

/**
 * Class
 * @author sigaevaleksandr
 * @since 12.03.2018
 */
public class User implements Comparable<User>{
    private Integer id;
    private Integer age;
    private String name;
    private String city;

    public User(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public User(Integer id, Integer age, String name, String city) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(User o) {
        return this.age.compareTo(o.age);
    }
}
