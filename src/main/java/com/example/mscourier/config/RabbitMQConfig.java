package com.example.mscourier.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_ASSIGNED_COURIER_QUEUE = "order.assigned.courier.queue";
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_ASSIGNED_COURIER_KEY = "order.assigned.courier";

    public static final String ORDER_DELIVERED_COURIER_QUEUE = "order.delivered.courier.queue";
    public static final String ORDER_DELIVERED_COURIER_KEY = "order.delivered.courier";

    @Bean
    public Queue orderAssignedCourierQueue() {
        return QueueBuilder.durable(ORDER_ASSIGNED_COURIER_QUEUE).build();
    }

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Binding orderAssignedCourierBinding() {
        return BindingBuilder
                .bind(orderAssignedCourierQueue())
                .to(orderExchange())
                .with(ORDER_ASSIGNED_COURIER_KEY);
    }

    @Bean
    public Queue orderDeliveredCourierQueue() {
        return QueueBuilder.durable(ORDER_DELIVERED_COURIER_QUEUE).build();
    }

    @Bean
    public Binding orderDeliveredCourierBinding() {
        return BindingBuilder
                .bind(orderDeliveredCourierQueue())
                .to(orderExchange())
                .with(ORDER_DELIVERED_COURIER_KEY);
    }
}
