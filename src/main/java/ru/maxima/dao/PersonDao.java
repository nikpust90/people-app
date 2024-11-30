package ru.maxima.dao;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.maxima.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Component
public class PersonDao {

    private List<Person> allPeoples;

    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";
    private Connection connection;
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public PersonDao(List<Person> allPeoples) {
        this.allPeoples = allPeoples;
    }

    public List<Person> getAllPeoples() {

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setMail(resultSet.getString("mail"));
                allPeoples.add(person);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allPeoples;
    }


    public Person getPersonById(long id) {
        Person person = null;
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM person WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setMail(resultSet.getString("mail"));



            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;

    }

    public void savePerson(Person person) {

        Long max = getAllPeoples().stream()
                .map(Person::getId)
                .max(Long::compareTo)
                .orElse(null);
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO person (id, name, age, mail) VALUES (" +
                    ++max +
                    ",'" + person.getName() +
                    "'," + person.getAge() +
                    ",'" + person.getMail() + "')";
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePerson(Person personFromForm) {

        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE person SET " +
                    "name = '" + personFromForm.getName() + "', " +
                    "age = " + personFromForm.getAge() + ", " +
                    "mail = '" + personFromForm.getMail() + "' " +
                    "WHERE id = " + personFromForm.getId() + ";";
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deletePerson(long id) {

        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM person WHERE id = " + id + ";";
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
