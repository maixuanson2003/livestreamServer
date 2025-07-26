package com.example.learn.mapper;

import com.example.learn.dto.type.typeResponse;
import com.example.learn.entity.TypeStream;

public class typeMapper {
    public static typeResponse toTypeResponse(TypeStream type) {
        return typeResponse.builder()
                .id(type.getId())
                .code(type.getCode())
                .name(type.getName())
                .description(type.getDescription())
                .build();
    }
}
