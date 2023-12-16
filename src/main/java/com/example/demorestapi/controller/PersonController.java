package com.example.demorestapi.controller;

import com.example.demorestapi.entities.Person;
import com.example.demorestapi.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonRepository personRepository;

    @GetMapping
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    @GetMapping("{name}")
    public List<Person> getAllPersonsByName(@PathVariable String name){
        return personRepository.findAllByNameIgnoreCase(name);
    }

    @GetMapping("/contains/{name}")
    public List<Person> getAllPersonsByContainingName(@PathVariable String name){
        return personRepository.findAllByNameContainingIgnoreCase(name);
    }
    @GetMapping("/age/{age}")
    public List<Person> getAllPersonsByContainingName(@PathVariable int age){
        return  personRepository.customFindingMethod(age);
    }
}
