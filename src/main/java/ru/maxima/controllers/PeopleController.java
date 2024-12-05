package ru.maxima.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") long id, Model model) {

        Person personById = personDao.getPersonById(id);
        model.addAttribute("keyPersonById", personById);
        return "view-with-person-by-id";
    }

    @GetMapping("/create")
    public String giveToUserPageToCreateNewPerson(Model model) {

        model.addAttribute("keyOfNewPerson", new Person());

        return "view-to-create-new-person";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("keyOfNewPerson") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view-to-create-new-person";
        }
        personDao.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") long id, Model model) {
        Person personToBeEdited = personDao.getPersonById(id);
        model.addAttribute("keyOfPersonToBeEdited", personToBeEdited);
        return "view-to-edit-person";
    }

    @PostMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") long id,
                             @ModelAttribute("keyOfPersonToBeEdited") @Valid Person personFromForm,

                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view-to-edit-person";
        }
        personDao.updatePerson(personFromForm);
        return "redirect:/people";
    }

    @PostMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") long id) {
        personDao.deletePerson(id);
        return "redirect:/people";
    }



}
