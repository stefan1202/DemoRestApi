package com.example.demorestapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String username;
    private String password;
    private String name;
    private String role;
}
