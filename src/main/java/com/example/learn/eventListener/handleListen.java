package com.example.learn.eventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import com.example.learn.entity.StreamSessions;
import com.example.learn.repository.streamRepository;

@Component
public class handleListen {
    @Autowired
    private streamRepository streamRepository;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleViewStream(SessionSubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String destination = headerAccessor.getDestination();
        if (destination == null || !destination.startsWith("/stream/view/")) {
            return;
        }
        Long streamId = getStreamId(destination);
        if (streamId == null) {
            return;
        }
        StreamSessions streamSession = streamRepository.findById(streamId).orElse(null);
        if (streamSession == null) {
            return;
        }
        streamSession.setViewCount(streamSession.getViewCount() + 1);
        streamRepository.save(streamSession);
        messagingTemplate.convertAndSend("/stream/view/" + streamId, streamSession);
    }

    @EventListener
    public void handleUnsubscribe(SessionSubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String destination = headerAccessor.getDestination();
        if (destination == null || !destination.startsWith("/stream/view/")) {
            return;
        }
        Long streamId = getStreamId(destination);
        if (streamId == null) {
            return;
        }
        StreamSessions streamSession = streamRepository.findById(streamId).orElse(null);
        if (streamSession == null) {
            return;
        }
        streamSession.setViewCount(streamSession.getViewCount() - 1);
        streamRepository.save(streamSession);
        messagingTemplate.convertAndSend("/stream/view/" + streamId, streamSession);
    }

    private Long getStreamId(String destination) {
        String streamPath = destination.substring("/stream/view/".length());
        try {
            return Long.parseLong(streamPath);
        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid stream ID in subscription path: " + streamPath);
            return null;
        }
    }
}