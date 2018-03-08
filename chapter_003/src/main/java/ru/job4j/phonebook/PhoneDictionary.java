package ru.job4j.phonebook;

import ru.job4j.array.Parser;
import java.util.ArrayList;
import java.util.List;

/**
 * Class
 * @author sigaevaleksandr
 * @since 08.03.2018
 */
public class PhoneDictionary {

    List<Person> phonebook = new ArrayList<Person>();

    public void add(Person person) {
        this.phonebook.add(person);
    }

    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        Parser search = new Parser();
        for (Person person : this.phonebook) {
            if (!phonebook.isEmpty()) {
                if (search.contains(String.format("%s, %s, %s, %s", person.name, person.surname, person.number, person.address), key)) {
                    result.add(person);
                }
            } else {
                break;
            }
        }
        return result;
    }
}
