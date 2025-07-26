package com.example.learn.dto.user;

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
public class userResponse {
    private Long id;
    private String username;
    private String phone;
    private String gender;
    private String address;
    private String Role;
}
