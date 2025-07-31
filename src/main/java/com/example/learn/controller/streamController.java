package com.example.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learn.dto.custom.ApiResponse;
import com.example.learn.dto.stream.prePairStream;
import com.example.learn.dto.stream.streamRequest;
import com.example.learn.dto.stream.streamResponse;
import com.example.learn.service.streamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/streams")
public class streamController {
    @Autowired
    private streamService streamService;

    @PostMapping("/create")
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

    @PostMapping("/start")
    public ResponseEntity<?> startStream(@RequestParam(name = "streamKey") String streamKey) {
        try {
            streamService.startStream(streamKey);
            ApiResponse successResponse = new ApiResponse();
            successResponse.setStatus("SUCCESS");
            successResponse.setMessage("Stream started successfully");
            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }

    @PostMapping("/end")
    public ResponseEntity<?> endStream(@RequestParam String streamKey) {
        try {
            streamService.endStream(streamKey);
            ApiResponse successResponse = new ApiResponse();
            successResponse.setStatus("SUCCESS");
            successResponse.setMessage("Stream has ended successfully");
            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
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
    public ResponseEntity<?> getAllStream() {
        try {
            List<streamResponse> streams = streamService.getAllStream();
            return ResponseEntity.status(HttpStatus.OK).body(streams);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse();
            errorResponse.setStatus("ERROR");
            errorResponse.setMessage(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStreamById(@PathVariable Long id) {
        try {
            streamResponse stream = streamService.getStreamById(id);
            return ResponseEntity.status(HttpStatus.OK).body(stream);
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
