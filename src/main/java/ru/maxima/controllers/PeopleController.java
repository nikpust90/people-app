package ru.maxima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maxima.dao.PersonDao;
import ru.maxima.model.Person;

import java.text.AttributedString;
import java.util.List;


@Controller
@RequestMapping("/people")
public class PeopleController {


    private PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String getAllPeople(Model model) {
        List<Person> allPeoples = personDao.getAllPeoples();


        model.addAttribute("keyAllPeoples", allPeoples);
        return "view-with-all-people";
    }

}

