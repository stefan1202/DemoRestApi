package com.example.demorestapi.services;

import com.example.demorestapi.entities.User;
import com.example.demorestapi.model.UserDto;
import com.example.demorestapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void createUser(UserDto userDto){
        Optional<User> userExists = userRepository.findByUserNameIgnoreCase(userDto.getUsername());
        if (userExists.isPresent()){
            throw new RuntimeException("User already exists!");
        }
        User user= new User();
        user.setName(userDto.getName());
        user.setRole(userDto.getRole());
        user.setUserName(userDto.getUsername());
        user.setUserPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }


    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }
}
