package com.example.learn.controller;

import com.example.learn.dto.auth.loginRequest;
import com.example.learn.dto.custom.ApiResponse;
import com.example.learn.dto.user.userRequest;
import com.example.learn.service.userService;

import io.swagger.v3.oas.models.media.MediaType;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class userController {
    @Autowired
    private userService userServices;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody userRequest userRequest) {
        try {
            ApiResponse resp = userServices.userRegister(userRequest);
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            new ApiResponse();
            ApiResponse errorResponse = new ApiResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);

        }
    }

    @PostMapping(value = "/update", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateUser(@RequestPart("file") MultipartFile file) {
        try {
            ApiResponse errorResponse = new ApiResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage("sss");
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(errorResponse);

        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllUser() {
        try {
            return ResponseEntity.ok(userServices.getAllUser());
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody loginRequest userRequest) {
        try {
            return ResponseEntity.ok(userServices.login(userRequest));
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }

    @PostMapping("/testrabbit")
    public ResponseEntity<?> message() {
        try {
            userServices.testRabbit();
            ApiResponse errorResponse = new ApiResponse();
            errorResponse.setStatus("ok");
            errorResponse.setMessage("ok");
            return ResponseEntity.ok(errorResponse);
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
