package com.example.learn.dto.type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class typeRequest {
    @NotEmpty(message = "name must not be empty")
    private String name;
    @Size(max = 100, message = "code must be less than or equal to 100 characters")
    private String description;
}
