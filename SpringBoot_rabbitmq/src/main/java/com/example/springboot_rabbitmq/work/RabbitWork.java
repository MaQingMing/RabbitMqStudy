package com.example.springboot_rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/15 21:34
 */
@RabbitListener(queuesToDeclare = @Queue("work"))
@Component
public class RabbitWork {
    @RabbitHandler
    public void receive(String message){
        System.out.println("消费者1" + message);
    }
}
