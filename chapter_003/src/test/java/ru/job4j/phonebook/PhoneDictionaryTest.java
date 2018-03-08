package ru.job4j.phonebook;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 * @author sigaevaleksandr
 * @since 08.03.2018
 */

public class PhoneDictionaryTest {

    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Aleksandr", "Sigaev", "52565488", "SaintPetersburg"));
        List<Person> persons = phones.find("Aleksandr");
        assertThat(persons.iterator().next().getSurname(), is("Sigaev"));
    }
}
