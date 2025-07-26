package com.example.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.learn.dto.type.typeRequest;
import com.example.learn.dto.type.typeResponse;
import com.example.learn.entity.TypeStream;
import com.example.learn.mapper.typeMapper;
import com.example.learn.repository.typeStreamRepository;

import jakarta.validation.Valid;

@Service
public class streamTypeService {
    @Autowired
    private typeStreamRepository typeStreamRepository;

    public typeResponse createType(@Valid typeRequest request, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new Exception(result.getFieldError().getDefaultMessage());
        }

        TypeStream newType = TypeStream.builder()
                .code("ssss")
                .name(request.getName())
                .description(request.getDescription())
                .build();
        TypeStream existingType = typeStreamRepository.save(newType);

        return typeMapper.toTypeResponse(existingType);
    }

    public List<typeResponse> getAllType() {
        List<TypeStream> types = typeStreamRepository.findAll();
        return types.stream().map(type -> typeMapper.toTypeResponse(type)).toList();
    }

    public typeResponse getTypeById(Long id) {
        TypeStream type = typeStreamRepository.findById(id).orElseThrow(() -> new RuntimeException("Type not found"));
        return typeMapper.toTypeResponse(type);
    }

}
