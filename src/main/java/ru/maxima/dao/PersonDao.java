package ru.maxima.dao;

import org.springframework.stereotype.Component;
import ru.maxima.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private List<Person> allPeoples;
    private static Long NEXT_ID= 0L;
    {
        allPeoples = new ArrayList<Person>();
        allPeoples.add(new Person(++NEXT_ID, "Ivanov", 25, "1@mail.ru"));
        allPeoples.add(new Person(++NEXT_ID, "Petrov",45, "2@mail.ru"));
        allPeoples.add(new Person(++NEXT_ID, "Pushkin",21, "3@mail.ru"));
        allPeoples.add(new Person(++NEXT_ID, "Atanasov",80, "4@mail.ru"));
        allPeoples.add(new Person(++NEXT_ID, "Rublev",17, "5@mail.ru"));
    }
    public List<Person> getAllPeoples() {
        return allPeoples;
    }


    public Person getPersonById(long id) {
        return allPeoples.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void savePerson(Person person) {
        person.setId(++NEXT_ID);
        allPeoples.add(person);
    }
}
