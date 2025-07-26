package com.example.learn.mapper;

import org.springframework.security.access.method.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.learn.dto.user.userRequest;
import com.example.learn.dto.user.userResponse;
import com.example.learn.entity.User;

public class userMapper {
    public static userResponse toDto(User user) {
        if (user == null) {
            return null;
        }

        return userResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .phone(user.getPhone())
                .Role(null == user.getRole() ? null : user.getRole())
                .address(user.getAddress())
                .build();
    }

    public static User toEntity(userRequest userRequest, PasswordEncoder passwordEncoder) {
        return User.builder()
                .phone(userRequest.getPhone())
                .address(userRequest.getAddress())
                .Role(userRequest.getRole())
                .gender(userRequest.getGender())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();

    }
}
