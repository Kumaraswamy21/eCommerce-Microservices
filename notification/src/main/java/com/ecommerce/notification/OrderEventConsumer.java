package com.ecommerce.notification;

import com.ecommerce.notification.payload.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class OrderEventConsumer {

/*   @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleOrderEvent(OrderCreatedEvent orderEvent){
        System.out.println("Recieved Order Event: " + orderEvent);

        long orderId = orderEvent.getOrderId();
        OrderStatus orderStatus = orderEvent.getStatus();

        System.out.println("OrderId: "+ orderId);
        System.out.println("Status: " +orderStatus);
        //Update Database
        //Send Notification
        //Generate Invoice
        //send mail
    }*/

    @Bean
    public Consumer<OrderCreatedEvent> orderCreated(){
        return event -> {
            log.info("Recieved Order Created Event for Order: {}" , event.getOrderId());
            log.info("Recieved Order Created Event for User Id: {}" , event.getUserId());
        };
    }
}
