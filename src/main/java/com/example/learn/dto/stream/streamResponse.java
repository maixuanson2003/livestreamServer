package com.example.learn.dto.stream;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class streamResponse {
    private Long id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private String status;
    private LocalDateTime startTime;
    private String nameOwner;
    private String playUrl;
}
