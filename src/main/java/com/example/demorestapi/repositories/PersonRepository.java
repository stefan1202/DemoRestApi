package com.example.demorestapi.repositories;

import com.example.demorestapi.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {
}
