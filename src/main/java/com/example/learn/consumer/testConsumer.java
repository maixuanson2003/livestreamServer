package com.example.learn.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class testConsumer {
    @RabbitListener(queues = "testQueue")
    public void receiveMessage(String message) {

        System.out.println("consummer1 handle: " + message);
    }

    @RabbitListener(queues = "testQueue")
    public void receiveMessage2(String message) {

        System.out.println("consummer2 handle: " + message);
    }
}
