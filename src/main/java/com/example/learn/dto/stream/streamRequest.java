package com.example.learn.dto.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class streamRequest {
    @NotBlank(message = "Title must not be blank")
    @Size(max = 100, message = "Title must be less than or equal to 100 characters")
    private String title;
    @Size(max = 500, message = "Description must be less than or equal to 500 characters")
    private String description;
    @NotBlank(message = "Thumbnail URL must not be blank")
    private String thumbnailUrl;
}
