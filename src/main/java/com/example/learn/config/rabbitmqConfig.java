package com.example.learn.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;

@Configuration
public class rabbitmqConfig {
    @Bean
    public Queue testQueue() {
        return new Queue("testQueue", true); // true for durable queue
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange("exchange-name");
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("routing-key")
                .noargs();
    }

}
