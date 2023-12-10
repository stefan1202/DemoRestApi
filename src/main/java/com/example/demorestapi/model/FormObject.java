package com.example.demorestapi.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class FormObject {
    @NotBlank
    @Length(min = 3, max = 30,message = "Name must be between 3 and 30 characters")
    private String name;
    @Min(value = 18,message = "Must be at least 18 years old")
    private int age;
}
