package com.example.learn.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.learn.dto.stream.prePairStream;
import com.example.learn.dto.stream.streamRequest;
import com.example.learn.entity.StreamSessions;
import com.example.learn.repository.streamRepository;

import jakarta.validation.Valid;

@Service
public class streamService {
    @Autowired
    private streamRepository streamRepository;
    private String BaseUrl = "http://localhost:8081/live/";
    private String rtmpBaseUrl = "rtmp://localhost:1935/stream/";

    private String genStreamKey(Long userId) {
        return "stream_" + System.currentTimeMillis() + "_" + userId;
    }

    private String genRtmpUrl(String streamKey) {
        return rtmpBaseUrl + streamKey;
    }

    private String genStreamUrl(String streamKey) {
        return BaseUrl + streamKey + "/index.m3u8";
    }

    public prePairStream createStreamSessions(@Valid streamRequest request, BindingResult result, Long userId,
            Long typeStreamId) throws Exception {

        if (result.hasErrors()) {
            throw new Exception(result.getFieldError().getDefaultMessage());
        }
        String streamKey = genStreamKey(userId);
        String rtmpUrl = genRtmpUrl(streamKey);
        String streamUrl = genStreamUrl(streamKey);
        StreamSessions streamSessions = new StreamSessions().builder()
                .streamKey(streamKey)
                .playbackUrl(streamUrl)
                .rtmpUrl(rtmpUrl)
                .title(request.getTitle())
                .description(request.getDescription())
                .thumbnailUrl(request.getThumbnailUrl())
                .streamTypeId(typeStreamId)
                .status("STARTED")
                .userId(userId)
                .build();
        StreamSessions savedSession = streamRepository.save(streamSessions);

        return prePairStream.builder()
                .streamKey(savedSession.getStreamKey())
                .rtmpUrl(rtmpBaseUrl)
                .title(savedSession.getTitle())
                .description(savedSession.getDescription())
                .thumbnailUrl(savedSession.getThumbnailUrl())
                .streamTypeId(typeStreamId)
                .build();

    }

}
