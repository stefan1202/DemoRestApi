package com.example.demorestapi.services;

import com.example.demorestapi.entities.User;
import com.example.demorestapi.mappers.Mapper;
import com.example.demorestapi.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements Mapper<UserDto, User> {
    @Override
    public UserDto toDto(User entity) {

        return UserDto.builder()
                .username(entity.getUserName())
                .role(entity.getRole())
                .name(entity.getName())
                .password(entity.getUserPassword())
                .build();
    }

    @Override
    public User toEntity(UserDto dto) {
        return User.builder()
                .userName(dto.getUsername())
                .role(dto.getRole())
                .name(dto.getName())
                .userPassword(dto.getPassword())
                .build();
    }
}
