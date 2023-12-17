package com.example.demorestapi;

import com.example.demorestapi.entities.Person;
import com.example.demorestapi.model.UserDto;
import com.example.demorestapi.repositories.PersonRepository;
import com.example.demorestapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final UserService userService;

    @Override
    public void run(String... args) {
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

        UserDto user= UserDto.builder()
                .username("ironman")
                .password("password")
                .role("ADMIN")
                .build();
        UserDto user2= UserDto.builder()
                .username("spiderman")
                .password("password")
                .role("SPIDER")
                .build();
    userService.createUser(user);
    userService.createUser(user2);

    }
}
