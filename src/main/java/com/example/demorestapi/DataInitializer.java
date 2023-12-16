package com.example.demorestapi;

import com.example.demorestapi.entities.Person;
import com.example.demorestapi.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        Person p1 = Person.builder()
                .name("Tudor")
                .age(30)
                .build();
        Person p2 = Person.builder()
                .name("Tudor")
                .age(60)
                .build();
        Person p3 = Person.builder()
                .name("Alex")
                .age(30)
                .build();
        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);


    }
}
