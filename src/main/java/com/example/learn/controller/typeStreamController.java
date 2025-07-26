package com.example.learn.controller;

import com.example.learn.dto.type.typeRequest;
import com.example.learn.dto.type.typeResponse;
import com.example.learn.service.streamTypeService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-streams")
public class typeStreamController {

    @Autowired
    private streamTypeService streamTypeService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createType(@Valid @RequestBody typeRequest request, BindingResult result) {
        try {
            typeResponse response = streamTypeService.createType(request, result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<typeResponse>> getAllTypes() {
        List<typeResponse> types = streamTypeService.getAllType();
        return ResponseEntity.ok(types);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTypeById(@PathVariable Long id) {
        try {
            typeResponse response = streamTypeService.getTypeById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
