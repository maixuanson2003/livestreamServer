package com.example.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learn.dto.custom.ApiResponse;
import com.example.learn.dto.stream.prePairStream;
import com.example.learn.dto.stream.streamRequest;
import com.example.learn.service.streamService;

import jakarta.validation.Valid;

@RestController
public class streamController {
    @Autowired
    private streamService streamService;

    @PostMapping("/stream/create")
    public ResponseEntity<?> createStream(@RequestParam Long userId, @RequestParam Long typeStreamId,
            @Valid @RequestBody streamRequest streamRequest, BindingResult result) {
        try {
            prePairStream prePairStream = streamService.createStreamSessions(streamRequest, result, userId,
                    typeStreamId);
            return ResponseEntity.status(HttpStatus.CREATED).body(prePairStream);

        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }

    }
}
