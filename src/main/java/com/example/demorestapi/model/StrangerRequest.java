package com.example.demorestapi.model;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;


public class StrangerRequest {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank (message = "Name must be filled")
    @Length(min = 3, max = 30,message = "Name must be between 3 and 30 characters")
    private String name;
}
