package com.example.springboot_rabbitmq.helloworld;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/15 21:22
 */
@RabbitListener(queuesToDeclare = @Queue("hello"))
@Component
public class rabbit_customer {

    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("message = " + message);
    }


}
