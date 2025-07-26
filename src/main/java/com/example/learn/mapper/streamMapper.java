package com.example.learn.mapper;

import com.example.learn.dto.stream.streamResponse;
import com.example.learn.entity.StreamSessions;

public class streamMapper {
    public static streamResponse toResponse(StreamSessions streamSessions) {
        if (streamSessions == null) {
            return null;
        }
        return streamResponse.builder()
                .id(streamSessions.getId())
                .title(streamSessions.getTitle())
                .description(streamSessions.getDescription())
                .thumbnailUrl(streamSessions.getThumbnailUrl())
                .status(streamSessions.getStatus())
                .startTime(streamSessions.getStartTime())
                .nameOwner(streamSessions.getUser().getUsername())
                .playUrl(null == streamSessions.getPlaybackUrl() ? null : streamSessions.getPlaybackUrl())
                .build();
    }
}
