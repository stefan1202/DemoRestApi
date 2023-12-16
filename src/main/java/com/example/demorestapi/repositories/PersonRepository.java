package com.example.demorestapi.repositories;

import com.example.demorestapi.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findAllByNameIgnoreCase(String name);
    List<Person> findAllByNameContainingIgnoreCase(String name);

    @Query(value = "select P from Person P where P.age=:age",nativeQuery = false)
    List<Person> customFindingMethod(Integer age);

}
