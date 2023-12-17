package com.example.demorestapi.repositories;

import com.example.demorestapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameIgnoreCase(String userName);
}
