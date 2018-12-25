package ru.job4j.phonebook;

import ru.job4j.array.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        Parser search = new Parser();
        List<Person> result = phonebook.stream().filter((person) ->
                search.contains(String.format("%s, %s, %s, %s", person.name, person.surname, person.number, person.address), key))
                .collect(Collectors.toList());
        return result;
    }
}
