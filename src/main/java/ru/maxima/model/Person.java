package ru.maxima.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private long id;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 symbols")
    private String name;
    @Min(value = 18, message = "Age must be greater than 18")
    private int age;
    @NotEmpty(message = "Mail can't be empty")
    @Email(message = "Invalid email")
    private String mail;
}
