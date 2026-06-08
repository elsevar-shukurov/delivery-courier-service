package com.example.mscourier.consumer;

import com.example.mscourier.events.OrderAssignedEvent;
import com.example.mscourier.events.OrderDeliveredEvent;
import com.example.mscourier.service.CourierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import static com.example.mscourier.config.RabbitMQConfig.ORDER_ASSIGNED_COURIER_QUEUE;
import static com.example.mscourier.config.RabbitMQConfig.ORDER_DELIVERED_COURIER_QUEUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourierConsumer {

    private final CourierService courierService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = ORDER_ASSIGNED_COURIER_QUEUE)
    public void handleOrderAssigned(String message) {
        try{
            log.info("CourierConsumer.handleOrderAssigned.start message: {}", message);
            var event = objectMapper.readValue(message, OrderAssignedEvent.class);
            courierService.markCourierBusy(event.getCourierId());
            log.info("CourierConsumer.handleOrderAssigned.end");
        }
        catch (Exception e){
            log.error("CourierConsumer.handleOrderAssigned.fail", e);
        }
    }

    @RabbitListener(queues = ORDER_DELIVERED_COURIER_QUEUE)
    public void handleOrderDelivered(String message) {
        try {
            log.info("CourierConsumer.handleOrderDelivered.start message: {}", message);
            var event = objectMapper.readValue(message, OrderDeliveredEvent.class);
            courierService.markCourierFree(event.getCourierId());
            log.info("CourierConsumer.handleOrderDelivered.end");
        }
        catch (Exception e){
            log.error("CourierConsumer.handleOrderDelivered.fail", e);
        }
    }
}
