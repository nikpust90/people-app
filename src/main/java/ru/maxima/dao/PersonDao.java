package ru.maxima.dao;

import ru.maxima.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    private List<Person> allPeoples;
    {
        allPeoples = new ArrayList<Person>();
        allPeoples.add(new Person(1, "Ivanov"));
        allPeoples.add(new Person(2, "Petrov"));
        allPeoples.add(new Person(3, "Pushkin"));
        allPeoples.add(new Person(4, "Atanasov"));
        allPeoples.add(new Person(5, "Rublev"));
    }
    public List<Person> getAllPeoples() {
        return allPeoples;
    }


}
