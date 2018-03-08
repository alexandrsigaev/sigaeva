package ru.job4j.phonebook;

/**
 * Class
 * @author sigaevaleksandr
 * @since 08.03.2018
 */
public class Person {
    String name;
    String surname;
    String number;
    String address;

    public Person(String name, String surname, String number, String address) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }
}
