package com.example.learn.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class userRequest {
    private String username;
    private String password;
    private String phone;
    private String gender;
    private String address;
    private String Role;
}
